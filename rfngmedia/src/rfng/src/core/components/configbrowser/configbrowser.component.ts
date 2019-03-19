import { ChangeDetectorRef, ElementRef, ComponentFactoryResolver, Component, Inject } from "@angular/core";
import { TranslateService } from "@ngx-translate/core";
import { BaseComponent } from "../base.component";
import { ColumnTable } from "../../beans/columntable";
import { ConfiguracionBrowserService } from "./configuracionbrowser.service";


@Component({
    selector: 'rf-configbrowser',
    templateUrl: './configbrowser.component.html',
    styleUrls: ['./configbrowser.component.css']
})
export class ConfigBrowserComponent extends BaseComponent {

    baseComponent;
    messageConfirmation: string;
    acctionYes: string;
    acctionNo: string;
    source: ColumnTable[];
    target: ColumnTable[];
    defaultColumns: ColumnTable[];
    columns: ColumnTable[];
    allColumns: ColumnTable[];
    tableName: string;
    oldTarget: ColumnTable[];

    constructor(changeDetectorRef: ChangeDetectorRef, el: ElementRef, componentFactoryResolver: ComponentFactoryResolver, translateService: TranslateService, @Inject(ConfiguracionBrowserService) service) {
        super(changeDetectorRef, el, componentFactoryResolver, translateService, service);
        this.styleClassModal = 'ConfigBrowserComponentModal';
        this.source = [];
        this.target = [];
        this.oldTarget = [];
    }

    loadData() {
        if (this.tableName != null && this.tableName != undefined) {

        }
    }

}