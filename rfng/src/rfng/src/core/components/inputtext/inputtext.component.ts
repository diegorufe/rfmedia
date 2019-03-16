import { BaseComponent } from "../base.component";
import { Component, OnInit, forwardRef, ComponentFactoryResolver, ElementRef, OnDestroy, Input, ChangeDetectorRef, OnChanges, SimpleChanges, SimpleChange } from "@angular/core";
import { ControlValueAccessor, NG_VALUE_ACCESSOR } from '@angular/forms';
import { TranslateService } from "@ngx-translate/core";
import { Filter } from "../../beans/filter";
import { EnumFields } from "../../constants/fields.constants";
import { EnumOperators } from "../../constants/operatos.constants";
import { CoreUtils } from "../../utils/core.utils";
import { Limit } from "../../beans/limit";

@Component({
    selector: 'rf-inputtext',
    templateUrl: './inputtext.component.html',
    styleUrls: ['./inputtext.component.css'],
    providers: [
        {
            provide: NG_VALUE_ACCESSOR,
            useExisting: forwardRef(() => InputTextComponent),
            multi: true
        }
    ]
})
export class InputTextComponent extends BaseComponent implements OnChanges, ControlValueAccessor, OnInit, OnDestroy {

    @Input()
    model;
    @Input()
    model2?;
    @Input()
    property;

    @Input()
    disabled?: boolean;

    @Input()
    rendered?: boolean;

    @Input()
    styleClass?: string;

    @Input()
    styleClassError?: string;

    @Input()
    label?: string;

    @Input()
    name?: string;

    @Input()
    required?: boolean;

    styleClassView: string;

    @Input()
    type?: string;

    errorMessage: string;

    @Input()
    size?: number;

    @Input()
    nameService?: string;

    service;

    @Input()
    min?: number;

    @Input()
    max?: number;


    constructor(changeDetectorRef: ChangeDetectorRef, el: ElementRef, componentFactoryResolver: ComponentFactoryResolver, translateService: TranslateService) {
        super(changeDetectorRef, el, componentFactoryResolver, translateService);
    }

    ngOnInit() {
        if (this.disabled == null || this.disabled == undefined) {
            this.disabled = false;
        }
        if (this.rendered == null || this.rendered == undefined) {
            this.rendered = true;
        }
        if (this.styleClass == null || this.styleClass == undefined) {
            this.styleClass = 'InputTextComponentCard';
        }
        if (this.styleClassError == null || this.styleClassError == undefined) {
            this.styleClassError = 'InputTextComponentCardError';
        }
        if (this.label == null || this.label == undefined) {
            this.label = '';
        }
        if (this.name == null || this.name == undefined) {
            this.name = this.property;
        }
        if (this.required == null || this.required == undefined) {
            this.required = true;
        }
        if (this.type == null || this.type == undefined) {
            this.type = 'text';
        }
        if (this.size == null || this.size == undefined) {
            this.size = 0;
        }
        this.errorMessage = '';
        this.styleClassView = this.styleClass;
        this.service = null;
        if (this.nameService != null && this.nameService != undefined) {
            this.service = CoreUtils.getServiceByName(this.nameService);
        }
        if (this.min == null || this.min == undefined) {
            this.min = 0;
        }
        if (this.max == null || this.max == undefined) {
            this.max = 0;
        }
    }

    writeValue(value: any) {
        if (this.model2 != null && this.model2 != undefined) {
            this.model[this.model2][this.property] = value;
        } else {
            this.model[this.property] = value;
        }
    }

    propagateChange = (_: any) => { };

    registerOnChange(fn) {
        this.propagateChange = fn;
    }

    registerOnTouched() { }

    ngOnDestroy() {

    }

    /**
     * Method to catch value change event 
     * @param event 
     */
    valuechangeEvent(event) {
        let value = null;
        if (this.model2 != null && this.model2 != undefined) {
            value = this.model[this.model2][this.property];
        } else {
            value = this.model[this.property];
        }
        // If not disabled check validators and converters
        if (this.disabled == false) {
            if (!this.valid()) {
                this.addMsgErrorClear(this.errorMessage, true, [this.label]);
                this.styleClassView = this.styleClassError;
                // Prevent default action for apply validators and converters
                this.setFocus();
                return false;
            } else {
                if (this.service != null && this.service != undefined) {
                    const filtersLoad: Filter[] = [];
                    const limit = new Limit(0, 1);
                    filtersLoad.push(new Filter(1,
                        1,
                        null,
                        this.property,
                        EnumOperators.EQUAL,
                        value));
                    this.service.find(null, filtersLoad, null, limit).toPromise().then(responseFind => {
                        if (responseFind.text() != null && responseFind.status == 200) {
                            const dataFind = JSON.parse(responseFind.text());
                            if (dataFind.data.length > 0) {
                                if (this.model2 != null && this.model2 != undefined) {
                                    this.model[this.model2] = dataFind.data[0];
                                } else {
                                    this.model = dataFind.data[0];
                                }
                            } else {
                                if (this.model2 != null && this.model2 != undefined) {
                                    this.model[this.model2] = CoreUtils.getInstanceModelByService(this.nameService);
                                } else {
                                    this.model = CoreUtils.getInstanceModelByService(this.nameService);
                                }
                            }
                        } else {
                            if (this.model2 != null && this.model2 != undefined) {
                                this.model[this.model2] = CoreUtils.getInstanceModelByService(this.nameService);
                            } else {
                                this.model = CoreUtils.getInstanceModelByService(this.nameService);
                            }
                        }
                    }).catch(err => { });
                } else {
                    this.styleClassView = this.styleClass;
                }
            }
        } else {
            this.styleClassView = this.styleClass;
        }
        // Prevent default action for apply validators and converters
        event.preventDefault();
    }

    setFocus() {
        let nativeInputElement = this.el.nativeElement.getElementsByTagName('INPUT')[0];
        if (nativeInputElement != null && nativeInputElement != undefined) {
            nativeInputElement.focus();
        }
    }

    /**
     * Method to catch is value is valid 
     */
    valid(): boolean {
        let valid = true;
        this.errorMessage = '';
        // If type is text check if required and value is not empty
        if (this.required == true && this.type === 'text') {
            if (this.model2 != null && this.model2 != undefined) {
                if (this.model[this.model2][this.property] == null || this.model[this.model2][this.property] == undefined || this.model[this.model2][this.property].trim() === '') {
                    valid = false;
                }
            } else if (this.model[this.property] == null || this.model[this.property] == undefined || this.model[this.property].trim() === '') {
                valid = false;
            }
            if (!valid) {
                this.errorMessage = 'i18nAppCore.i18nComponents.i18n_comunes_required';
            }
        }
        return valid;
    }

    ngOnChanges(changes: SimpleChanges) {
        this.styleClassView = this.styleClass;
    }
}