import { TranslateService } from "@ngx-translate/core";
import { CoreUtils } from "../utils/core.utils";
import { ServiceResolver } from "../utils/service.resolver";
import { ModelResolver } from "../utils/model.resolver";
import { ComponentResolver } from "../utils/component.resolver";


export class AppConfig {
    // Is the name for app 
    appName;
    // This property is necesary to detect changes in view
    appComponent;
    // List of messages to list in aplication
    messages: any[];
    // Translate service to i18n messages
    translateService;
    // Property to indicate to application is app for tabsViews 
    isTabsView: boolean;
    // Locale
    locale: string;
    // TabViewComponent to app tabsViews dynamic
    tabViewComponent;
    // Dashboard component is a component to load aplicatión secces login 
    dashBoardComponent;
    // Resolver service app
    serviceResolver: ServiceResolver
    // Resolver models app
    modelREsolver: ModelResolver;
    // Resolver components app
    componentResolver: ComponentResolver;

    constructor() {
        // Get the translate service
        this.translateService = CoreUtils.getService(TranslateService);
        this.isTabsView = true;
        this.locale = 'es';
    }

}