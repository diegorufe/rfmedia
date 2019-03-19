import {
    ANALYZE_FOR_ENTRY_COMPONENTS,
    ModuleWithProviders,
    NgModule,
    NgModuleFactoryLoader,
    SystemJsNgModuleLoader,
    Type,
} from '@angular/core';
import { ROUTES } from '@angular/router';

import { DynamicLoaderComponent } from './dynamic.loader.component';
import { DYNAMIC_COMPONENT, DYNAMIC_COMPONENT_MANIFESTS, DynamicLoaderManifestComponent } from './dynamic.loader.manifest.component';

@NgModule()
export class DynamicComponentLoaderModule {
    static forRoot(manifests: DynamicLoaderManifestComponent[]): ModuleWithProviders {
        return {
            ngModule: DynamicComponentLoaderModule,
            providers: [
                DynamicLoaderComponent,
                { provide: NgModuleFactoryLoader, useClass: SystemJsNgModuleLoader },
                // provider for Angular CLI to analyze
                { provide: ROUTES, useValue: manifests, multi: true },
                // provider for DynamicComponentLoader to analyze
                { provide: DYNAMIC_COMPONENT_MANIFESTS, useValue: manifests },
            ],
        };
    }
    static forChild(component: Type<any>): ModuleWithProviders {
        return {
            ngModule: DynamicComponentLoaderModule,
            providers: [
                { provide: ANALYZE_FOR_ENTRY_COMPONENTS, useValue: component, multi: true },
                // provider for @angular/router to parse
                { provide: ROUTES, useValue: [], multi: true },
                // provider for DynamicComponentLoader to analyze
                { provide: DYNAMIC_COMPONENT, useValue: component },
            ],
        };
    }
}

export { DynamicLoaderManifestComponent } from './dynamic.loader.manifest.component';