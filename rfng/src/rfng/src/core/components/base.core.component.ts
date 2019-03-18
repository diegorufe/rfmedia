import { ViewContainerRef } from '@angular/core';
import { DynamicLoaderComponent } from './dynamic/dynamic.loader.component';
import { DynamicUtils } from '../utils/dynamic.utils';
import { BasePropertiesComponent } from './base.properties.component';


/**
 * This class is a base class for extends all components
 */
export abstract class BaseCoreComponent extends BasePropertiesComponent {

    constructor(){
        super();
    }

    /**
     * Method to add component dynamic by id
     * @param viewChild 
     * @param component 
     * @param removeAll 
     * @param dynamicComponentLoader 
     */
    addComponentDynamic(viewChild: ViewContainerRef, component: string, removeAll: boolean, dynamicComponentLoader: DynamicLoaderComponent) {
        DynamicUtils.addComponentDynamic(viewChild, component, removeAll, dynamicComponentLoader);
    }

    /**
     * Method to generate uniqueId
     */
    generateUniqueId() {
        return this.chr4() + this.chr4() +
            '-' + this.chr4() +
            '-' + this.chr4() +
            '-' + this.chr4() +
            '-' + this.chr4() + this.chr4() + this.chr4();
    }

    chr4() {
        return Math.random().toString(16).slice(-4);
    }

     /**
     * Generic method lo load components
     */
    load() {

    }

}