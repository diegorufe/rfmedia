import { BaseComponent } from "../base.component";
import { Component, OnInit, forwardRef, ComponentFactoryResolver, ElementRef, OnDestroy, Input, ChangeDetectorRef, OnChanges, SimpleChanges, SimpleChange } from "@angular/core";
import { ControlValueAccessor, NG_VALUE_ACCESSOR } from '@angular/forms';
import { TranslateService } from "@ngx-translate/core";
import { CoreUtils } from "../../utils/core.utils";

@Component({
    selector: 'rf-inputtextbutton',
    templateUrl: './inputtextbutton.component.html',
    styleUrls: ['./inputtextbutton.component.css']
})
export class InputTextButtonComponent extends BaseComponent {

    @Input()
    model: any;
    @Input()
    model2?: any;
    @Input()
    property: any;

    @Input()
    propertyDescri: any;

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

    @Input()
    type?: string;

    @Input()
    size?: number;

    @Input()
    sizeDescri?: number;

    @Input()
    labelDescri?: string;

    @Input()
    nameService?: string;

    @Input()
    componentId?: string;

    @Input()
    actionSelModal?: any;

    @Input()
    actionSelModalParams?: any;

    constructor(changeDetectorRef: ChangeDetectorRef, el: ElementRef, componentFactoryResolver: ComponentFactoryResolver, translateService: TranslateService) {
        super(changeDetectorRef, el, componentFactoryResolver, translateService);
    }

    openSearchModal() {
        let dataInformation = CoreUtils.getComponentInformationById(this.componentId);
        let mapParams = new Map();
        mapParams.set('baseIpuntButtonSelectData', this);
        this.openModal(dataInformation[0], this.componentId, mapParams);
    }

    /**
     * Mhetod to select data from a table
     * @param rowData 
     * @param component 
     */
    selectData(rowData, component) {
        this.setLoadingTab(true);
        if (this.model2 != null && this.model2 != undefined) {
            this.model[this.model2] = rowData;
        } else {
            this.model = rowData;
        }

        if (this.actionSelModal != null && this.actionSelModal != undefined) {
            if (this.actionSelModalParams != null && this.actionSelModalParams != undefined) {
                this.actionSelModal(rowData, this.actionSelModalParams);
            } else {
                this.actionSelModal(rowData);
            }

        }
        this.setLoadingTab(false);
        component.modalComponent.closeModal();
    }

}