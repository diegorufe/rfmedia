import { BaseComponent } from "../base.component";
import { Component, OnInit, forwardRef, ComponentFactoryResolver, ElementRef, OnDestroy, Input, ChangeDetectorRef } from "@angular/core";
import { ControlValueAccessor, NG_VALUE_ACCESSOR } from '@angular/forms';
import { TranslateService } from "@ngx-translate/core";

@Component({
    selector: 'rf-checkbox',
    templateUrl: './checkbox.component.html',
    styleUrls: ['./checkbox.component.css'],
    providers: [
        {
            provide: NG_VALUE_ACCESSOR,
            useExisting: forwardRef(() => CheckBoxComponent),
            multi: true
        }
    ]
})
export class CheckBoxComponent extends BaseComponent implements ControlValueAccessor, OnInit, OnDestroy {

    @Input()
    model;
    @Input()
    model2?;
    @Input()
    property:string;

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

    errorMessage: string;

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
            this.styleClass = 'CheckBoxComponentCard';
        }
        if (this.styleClassError == null || this.styleClassError == undefined) {
            this.styleClassError = 'CheckBoxComponentCardError';
        }
        if (this.label == null || this.label == undefined) {
            this.label = '';
        }
        if (this.name == null || this.name == undefined) {
            this.name = this.property;
        }
        if (this.required == null || this.required == undefined) {
            this.required = false;
        }
        this.errorMessage = '';
        this.styleClassView = this.styleClass;
    }

    writeValue(value: any) {
        this.model[this.property] = value;
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
        // If not disabled check validators and converters
        if (this.disabled == false) {
            this.setChekedValue();
            if (!this.valid()) {
                this.styleClassView = this.styleClassError;
                this.setFocus();
            } else {
                this.styleClassView = this.styleClass;
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
            nativeInputElement.scrollIntoView();
        }
    }

    /**
     * Method to setChekedValue in model
     */
    setChekedValue() {
        let nativeInputElement = this.el.nativeElement.getElementsByTagName('INPUT')[0];
        if (nativeInputElement != null && nativeInputElement != undefined) {
            if (this.model2 != null && this.model2 != undefined) {
                this.model[this.model2][this.property] = nativeInputElement.checked;
            } else {
                this.model[this.property] = nativeInputElement.checked;
            }
        }
    }

    /**
     * Method to catch is value is valid 
     */
    valid(): boolean {
        let valid = true;
        this.errorMessage = '';
        // If type is text check if required and value is not empty
        if (this.required == true) {
            if (this.model2 != null && this.model2 != undefined) {
                if (this.model[this.model2][this.property] == null || this.model[this.model2][this.property] == undefined || this.model[this.model2][this.property] == 0) {
                    valid = false;
                }
            } else if (this.model[this.property] == null || this.model[this.property] == undefined || this.model[this.property].trim() == 0) {
                valid = false;
            }
            if (!valid) {
                this.errorMessage = 'i18nAppCore.i18nComponents.i18n_comunes_required';
            }
        }
        return valid;
    }
}