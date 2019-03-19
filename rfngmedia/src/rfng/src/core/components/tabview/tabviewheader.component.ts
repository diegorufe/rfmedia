import { Component, ChangeDetectorRef, ElementRef, ComponentFactoryResolver } from "@angular/core";
import { BaseComponent } from "../base.component";
import { TranslateService } from "@ngx-translate/core";
import { DynamicLoaderComponent } from "../dynamic/dynamic.loader.component";
import { TabView } from "../../beans/tabview";


@Component({
    selector: 'rf-tabViewHeader',
    templateUrl: './tabviewheader.component.html',
    styleUrls: ['./tabviewheader.component.css']
})
export class TabViewHeaderComponent extends BaseComponent {

    tabView:TabView;
    tabViewComponent;
    
    constructor(changeDetectorRef: ChangeDetectorRef, el: ElementRef, componentFactoryResolver: ComponentFactoryResolver, translateService: TranslateService, dynamicComponentLoader: DynamicLoaderComponent) {
        super(changeDetectorRef, el, componentFactoryResolver, translateService);
    }

    /**
     * Method to close tab
     */
    closeTab(){
        this.tabViewComponent.closeTab(this.tabView);
    }

    /**
     * Method to select tab
     */
    selectTab(){
        this.tabViewComponent.selectTab(this.tabView);
    }

}