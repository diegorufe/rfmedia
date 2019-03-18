import { ElementRef, ComponentFactoryResolver, ChangeDetectorRef } from "@angular/core";
import { TranslateService } from '@ngx-translate/core';
import { BaseService } from "../service/base.service";
import { CoreUtils } from "../utils/core.utils";
import { BaseFormComponent } from './base.form.component';

/**
 * This class is a base class for extends all components
 */
export abstract class BaseComponent extends BaseFormComponent {

    constructor(changeDetectorRef: ChangeDetectorRef, el: ElementRef, componentFactoryResolver: ComponentFactoryResolver, translateService: TranslateService, service?: BaseService) {
        super();
        this.changeDetectorRef = changeDetectorRef;
        this.el = el;
        this.componentFactoryResolver = componentFactoryResolver;
        this.translateService = translateService;
        if (this.translateService != null && this.translateService != undefined) {
            let locale = 'es';
            if (CoreUtils.haveAppConfig()) {
                locale = CoreUtils.APP_CONFIG.locale;
            }
            // this language will be used as a fallback when a translation isn't found in the current language
            translateService.setDefaultLang(locale);
            // the lang to use, if the lang isn't available, it will use the current loader to get them
            translateService.use(locale);
            // To fix rest of i18n to be required
            //let i18n = require('../../../../assets/i18n/'+locale+'.json');
            //translateService.setTranslation(locale, i18n, true);
        }
        this.service = service;
        this.loading = false;
        this.index = 0;
        this.zindex = 0;
        if (CoreUtils.haveAppConfig() && CoreUtils.APP_CONFIG.tabViewComponent != null && CoreUtils.APP_CONFIG.tabViewComponent != undefined) {
            this.tabView = CoreUtils.APP_CONFIG.tabViewComponent.selectedTab();
        }
        this.styleClassModal = '';
        this.modal = false;
        this.getUpdatedZIndex();
        this.isAwait = true;
    }
    
}