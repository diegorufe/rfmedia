import { Component, ChangeDetectorRef, ElementRef, ViewChild, ViewContainerRef, ComponentFactoryResolver } from "@angular/core";
import { BaseComponent } from "../base.component";
import { TranslateService } from "@ngx-translate/core";
import { DynamicLoaderComponent } from "../dynamic/dynamic.loader.component";
import { TabView } from "../../beans/tabview";


@Component({
    selector: 'rf-tabViewBody',
    templateUrl: './tabviewbody.component.html',
    styleUrls: ['./tabviewbody.component.css']
})
export class TabViewBodyComponent extends BaseComponent {

    tabView:TabView;
    tabViewComponent;
    @ViewChild('tabViewBodyContent', { read: ViewContainerRef })
    tabViewBodyContent: ViewContainerRef;
    @ViewChild('tabViewModalsContent', { read: ViewContainerRef })
    tabViewModalsContent: ViewContainerRef;
    
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