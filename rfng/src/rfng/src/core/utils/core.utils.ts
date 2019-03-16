import { Injector } from "@angular/core";

/**
 * This class is a class for utils core aplications
 */
export class CoreUtils {

    static INYECTOR: Injector = null;
    static APP_CONFIG: any = null;
    static APP_TRANSLATE: any = null;
    static MODAL_Z_INDEX = 1000;

    /**
     * Method to get a service by class service
     * @param seriveClass 
     */
    static getService(seriveClass): any {
        let service = null;
        if (CoreUtils.INYECTOR != null && CoreUtils.INYECTOR != undefined) {
            service = CoreUtils.INYECTOR.get(seriveClass);
        }
        return service;
    }

    /**
     * Mehtod to get service by name 
     * @param serviceName 
     */
    static getServiceByName(serviceName: string) {
        let service = null;
        if (CoreUtils.haveAppConfig()) {
            service = CoreUtils.APP_CONFIG.serviceResolver.resolveService(serviceName);
            service = CoreUtils.getService(service);
        }
        return service;
    }

    /**
     * Method to get instance model by service name
     * @param serviceName 
     */
    static getInstanceModelByService(serviceName: string){
        let model = null;
        if (CoreUtils.haveAppConfig()) {
            model = CoreUtils.APP_CONFIG.modelResolver.resolveModelByServiceName(serviceName);
        }
        return model;
    }

    /**
     * Method to get component informatión by id
     * @param componentId 
     */
    static getComponentInformationById(componentId){
        let dataInformation = null;
        if (CoreUtils.haveAppConfig()) {
            dataInformation = CoreUtils.APP_CONFIG.componentResolver.resolveComponentByIdModal(componentId);
        }
        return dataInformation;
    }

    /**
     * Method to know if there is APP_CONFIG
     */
    static haveAppConfig() {
        return CoreUtils.APP_CONFIG != null && CoreUtils.APP_CONFIG != undefined;
    }

    /**
     * Method to know if there is APP_TRANSLATE
     */
    static haveAppTranslate() {
        return CoreUtils.APP_TRANSLATE != null && CoreUtils.APP_TRANSLATE != undefined;
    }

}