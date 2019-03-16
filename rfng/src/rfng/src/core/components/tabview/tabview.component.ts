import { BaseComponent } from "../base.component";
import { Component, ElementRef, ComponentFactoryResolver, ViewChild, ViewContainerRef, ChangeDetectorRef } from "@angular/core";
import { TranslateService } from "@ngx-translate/core";
import { DynamicLoaderComponent } from "../dynamic/dynamic.loader.component";
import { TabView } from "../../beans/tabview";
import { CoreUtils } from "../../utils/core.utils";

@Component({
    selector: 'rf-tabView',
    templateUrl: './tabview.component.html',
    styleUrls: ['./tabview.component.css']
})
export class TabViewComponent extends BaseComponent {

    @ViewChild('tabViewHeaders', { read: ViewContainerRef })
    tabViewHeaders: ViewContainerRef;
    @ViewChild('tabViewBody', { read: ViewContainerRef })
    tabViewBody: ViewContainerRef;

    dynamicComponentLoader: DynamicLoaderComponent;
    tabsView: TabView[];

    refTabViewHeader: string;
    refTabViewBody: string;

    constructor(changeDetectorRef: ChangeDetectorRef, el: ElementRef, componentFactoryResolver: ComponentFactoryResolver, translateService: TranslateService, dynamicComponentLoader: DynamicLoaderComponent) {
        super(changeDetectorRef, el, componentFactoryResolver, translateService);
        this.tabsView = [];
        this.refTabViewHeader = 'tabviewheader';
        this.refTabViewBody = 'tabviewbody';
        this.dynamicComponentLoader = dynamicComponentLoader;
        if (CoreUtils.haveAppConfig()) {
            CoreUtils.APP_CONFIG.tabViewComponent = this;
        }
    }

    /**
     * Method to addTab in tabView
     * @param component 
     * @param title 
     */
    addTab(component, title) {
        this.unSelectTabs();
        let tabView = new TabView();
        tabView.active = true;
        tabView.label = title;
        tabView.index = this.tabsView.length;
        tabView.tabViewComponent = this;
        this.tabsView.push(tabView);
        if (CoreUtils.haveAppConfig()) {
            this.dynamicComponentLoader = CoreUtils.APP_CONFIG.dashBoardComponent.dynamicComponentLoader;
        }
        // First create header
        this.dynamicComponentLoader
            .getComponentFactory<any>(this.refTabViewHeader)
            .subscribe(componentFactory => {
                const ref: any = this.tabViewHeaders.createComponent(componentFactory);
                this.tabViewHeaders.element.nativeElement.appendChild(ref.location.nativeElement);
                ref._component.tabView = tabView;
                ref._component.tabViewComponent = this;
                ref.changeDetectorRef.detectChanges();
                tabView.tabViewHeader = ref._component;
                // Second create body
                this.dynamicComponentLoader
                    .getComponentFactory<any>(this.refTabViewBody)
                    .subscribe(componentFactory => {
                        const ref: any = this.tabViewBody.createComponent(componentFactory);
                        this.tabViewBody.element.nativeElement.appendChild(ref.location.nativeElement);
                        ref._component.tabView = tabView;
                        ref._component.tabViewComponent = this;
                        ref.changeDetectorRef.detectChanges();
                        tabView.tabViewBody = ref._component;
                        // Last step create component
                        this.dynamicComponentLoader
                            .getComponentFactory<any>(component)
                            .subscribe(componentFactory => {
                                const ref: any = tabView.tabViewBody.tabViewBodyContent.createComponent(componentFactory);
                                tabView.tabViewBody.tabViewBodyContent.element.nativeElement.appendChild(ref.location.nativeElement);
                                ref._component.tabView = tabView;
                                this.changeDetectorRef.markForCheck();
                                this.changeDetectorRef.detectChanges();
                            }, error => {
                                console.warn(error);
                            });
                    }, error => {
                        console.warn(error);
                    });
            }, error => {
                console.warn(error);
            });
    }

    /**
     * Method to close all tabs 
     */
    closeTabs() {
        let totalTabs = this.tabsView.length;
        while(totalTabs > 0){
            this.closeTab(this.tabsView[0], true);
            totalTabs = this.tabsView.length;
        }
        this.tabsView = [];
    }

    /**
     * Method to close tab
     * @param tabView 
     */
    closeTab(tabView, notRenumerate?: boolean) {
        let index = tabView.index;
        let active = tabView.active
        this.tabViewHeaders.remove(tabView.index);
        this.tabViewBody.remove(tabView.index);
        this.tabsView.splice(tabView.index, 1);
        if ((notRenumerate == null && notRenumerate == undefined) || notRenumerate) {
            this.renumerateTabs();
        }
        if (index > this.tabsView.length - 1) {
            index = this.tabsView.length - 1;
        }
        if (index < 0) {
            index = 0;
        }
        if (this.tabsView.length > 0 && active) {
            this.unSelectTabs();
            this.tabsView[index].active = true;
        }
        this.changeDetectorRef.markForCheck();
        this.changeDetectorRef.detectChanges();
    }

    /**
     * Method to select tab
     * @param tabView 
     */
    selectTab(tabView) {
        this.unSelectTabs();
        let index = this.tabsView.indexOf(tabView);
        this.tabsView[index].active = true;
        tabView.active = true;
    }

    /**
     * Method to renumerate tabs
     */
    renumerateTabs() {
        for (let i = 0; i < this.tabsView.length; i++) {
            this.tabsView[i].index = i;
        }
    }

    /**
     * Method to unselect tabs
     */
    unSelectTabs() {
        for (let i = 0; i < this.tabsView.length; i++) {
            this.tabsView[i].active = false;
        }
    }

    /**
     * Method to know is visible or not
     */
    visible() {
        return this.tabsView.length > 0;
    }

    /**
     * Method to get selected tabView
     */
    selectedTab() {
        let tabView = null;
        for (let i = 0; i < this.tabsView.length; i++) {
            if (this.tabsView[i].active) {
                tabView = this.tabsView[i];
                break;
            }
        }
        return tabView;
    }

    /**
     * Method to change body tab active tab
     * @param component 
     * @param paramsMap 
     */
    changueBodyTab(component, paramsMap) {
        let tabView = this.selectedTab();
        if (tabView != null && tabView != undefined) {
            tabView.tabViewBody.tabViewBodyContent.clear();
            this.dynamicComponentLoader
                .getComponentFactory<any>(component)
                .subscribe(componentFactory => {
                    const ref: any = tabView.tabViewBody.tabViewBodyContent.createComponent(componentFactory);
                    tabView.tabViewBody.tabViewBodyContent.element.nativeElement.appendChild(ref.location.nativeElement);
                    ref._component.tabView = tabView;
                    if (paramsMap != null && paramsMap != undefined) {
                        let keys: string[] = Array.from(paramsMap.keys());
                        for (let key of keys) {
                            if (key.trim() !== 'bodyTabAction') {
                                ref._component[key] = paramsMap.get(key);
                            }
                        }
                        if (paramsMap.has('bodyTabAction')) {
                            let acctions = paramsMap.get('bodyTabAction')
                            if (acctions != null && acctions != undefined) {
                                for (let i = 0; i < acctions.length; i++) {
                                    ref._component[acctions[i]]();
                                }
                            }
                        }
                    }
                    this.changeDetectorRef.markForCheck();
                    this.changeDetectorRef.detectChanges();
                }, error => {
                    console.warn(error);
                });
        }
    }

    /**
     * Method to change body for modal
     * @param modal 
     * @param component 
     * @param paramsMap 
     */
    changueBodyModalTab(modalComponent, component, paramsMap) {
        let tabView = this.selectedTab();
        if (tabView != null && tabView != undefined) {
            modalComponent.modalViewBody.element.nativeElement.removeChild(modalComponent.modalViewBody.element.nativeElement.childNodes[0]);   
            this.dynamicComponentLoader
                .getComponentFactory<any>(component)
                .subscribe(componentFactory => {
                    const ref: any = modalComponent.modalViewBody.createComponent(componentFactory);
                    ref._component.modalComponent = modalComponent;
                    modalComponent.baseComponent = ref._component;
                    modalComponent.modalViewBody.element.nativeElement.appendChild(ref.location.nativeElement);
                    if (paramsMap != null && paramsMap != undefined) {
                        let keys: string[] = Array.from(paramsMap.keys());
                        for (let key of keys) {
                            if (key.trim() !== 'bodyTabAction') {
                                ref._component[key] = paramsMap.get(key);
                            }
                        }
                        if (paramsMap.has('bodyTabAction')) {
                            let acctions = paramsMap.get('bodyTabAction')
                            if (acctions != null && acctions != undefined) {
                                for (let i = 0; i < acctions.length; i++) {
                                    ref._component[acctions[i]]();
                                }
                            }
                        }
                    }
                    modalComponent.changeDetectorRef.markForCheck();
                    modalComponent.changeDetectorRef.detectChanges();
                    tabView.tabViewBody.changeDetectorRef.markForCheck();
                    tabView.tabViewBody.changeDetectorRef.detectChanges();
                }, error => {
                    console.warn(error);
                });
        }
    }



}