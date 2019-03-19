import { Component, ChangeDetectorRef, ElementRef, ComponentFactoryResolver, AfterViewInit, ViewChild, ViewContainerRef } from "@angular/core";
import { TranslateService } from "@ngx-translate/core";
import { CoreUtils } from 'src/rfng/src/core/utils/core.utils';
import { BaseComponent } from 'src/rfng/src/core/components/base.component';
import { DynamicLoaderComponent } from 'src/rfng/src/core/components/dynamic/dynamic.loader.component';
import { DynamicUtils } from 'src/rfng/src/core/utils/dynamic.utils';


@Component({
    selector: 'rf-dashboard',
    templateUrl: './dashboard.component.html',
    styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent extends BaseComponent implements AfterViewInit {

    @ViewChild('dashboardComponentBody', { read: ViewContainerRef })
    dashboardComponentBody: ViewContainerRef;
    dynamicComponentLoader;

    constructor(changeDetectorRef: ChangeDetectorRef, el: ElementRef, componentFactoryResolver: ComponentFactoryResolver, translateService: TranslateService, dynamicComponentLoader: DynamicLoaderComponent) {
        super(changeDetectorRef, el, componentFactoryResolver, translateService);
        this.dynamicComponentLoader = dynamicComponentLoader;
        if (CoreUtils.haveAppConfig()) {
            CoreUtils.APP_CONFIG.dashBoardComponent = this;
        }
    }

    ngAfterViewInit() {
        if (this.isLogged()) {
            this.addComponentDynamic(this.dashboardComponentBody, 'tabview', true, this.dynamicComponentLoader);
        }
    }

    closeTabs() {
        if (CoreUtils.haveAppConfig()) {
            CoreUtils.APP_CONFIG.tabViewComponent.closeTabs();
        }
    }

    /**
     * Method to close session 
     */
    closeSession(){
        localStorage.removeItem("principal");
        DynamicUtils.refreshAppView();
    }

}