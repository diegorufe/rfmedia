

/**
 * This class is a base class for extends all components
 */
export abstract class BasePropertiesComponent {
    changeDetectorRef;
    el;
    componentFactoryResolver;
    translateService;
    service;

    // Is a reference to tabView obejct when app works withs tabs
    tabView;
    // How to know loading 
    loading: boolean;

    // Index specially for modals
    index: number;
    zindex: number;

    styleClassModal: string;
    modalComponent;
    modal: boolean;

    baseComponent;
    baseIpuntButtonSelectData;

    // To know if component is await data, if false mehtod are async
    isAwait: boolean;

    constructor(){
        
    }
}