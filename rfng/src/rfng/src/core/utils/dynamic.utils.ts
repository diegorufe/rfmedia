import { CoreUtils } from "./core.utils";
import { ViewContainerRef } from "@angular/core";
import { DynamicLoaderComponent } from "../components/dynamic/dynamic.loader.component";

/**
 * This class is a class for changes data in view 
 */
export class DynamicUtils {

    /**
     * Method to refresh app view
     */
    static refreshAppView() {
        if (CoreUtils.haveAppConfig()) {
            let appConfig = CoreUtils.APP_CONFIG;
            appConfig.appComponent.changeDetectorRef.markForCheck();
            appConfig.appComponent.changeDetectorRef.detectChanges();
        }
    }
    

    /**
     *  Method to add component by id
     * @param viewChild 
     * @param component 
     * @param removeAll 
     * @param dynamicComponentLoader 
     */
    static addComponentDynamic(viewChild: ViewContainerRef, component: string, removeAll: boolean, dynamicComponentLoader: DynamicLoaderComponent) {
        if (removeAll) {
            viewChild.clear();
        }
        dynamicComponentLoader
            .getComponentFactory<any>(component)
            .subscribe(componentFactory => {
                const ref = viewChild.createComponent(componentFactory);
                viewChild.element.nativeElement.appendChild(ref.location.nativeElement);
                ref.changeDetectorRef.detectChanges();
            }, error => {
                console.warn(error);
            });
    }
}