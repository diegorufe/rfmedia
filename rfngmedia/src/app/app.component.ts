import { Component, AfterViewInit, ChangeDetectorRef, ElementRef, ComponentFactoryResolver, ViewChild, ViewContainerRef } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { BaseComponent } from 'src/rfng/src/core/components/base.component';
import { DynamicLoaderComponent } from 'src/rfng/src/core/components/dynamic/dynamic.loader.component';
import { CoreUtils } from 'src/rfng/src/core/utils/core.utils';
import { AppConfig } from 'src/rfng/src/core/beans/appconfig';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent extends BaseComponent implements AfterViewInit {

  title = 'rfngmedia';
  dynamicComponentLoader: DynamicLoaderComponent;
  @ViewChild('mainDiv', { read: ViewContainerRef })
  mainDiv: ViewContainerRef;

  constructor(changeDetectorRef: ChangeDetectorRef, el: ElementRef, componentFactoryResolver: ComponentFactoryResolver, translateService: TranslateService, dynamicComponentLoader: DynamicLoaderComponent) {
    super(changeDetectorRef, el, componentFactoryResolver, translateService);
    this.dynamicComponentLoader = dynamicComponentLoader;
    CoreUtils.APP_TRANSLATE = this.translateService;
    CoreUtils.APP_CONFIG = new AppConfig();
    CoreUtils.APP_CONFIG.appComponent = this;
    // CoreUtils.APP_CONFIG.serviceResolver = new AppServiceResolver();
    // CoreUtils.APP_CONFIG.modelResolver = new AppModelResolver();
    // CoreUtils.APP_CONFIG.componentResolver = new AppComponentResolver();
  }

  ngAfterViewInit() {
    this.loadComponent('rf_dashboard');
  }

  /**
   * Method to load components by id
   * @param id 
   */
  loadComponent(id) {
    this.addComponentDynamic(this.mainDiv, id, true, this.dynamicComponentLoader);
  }
}
