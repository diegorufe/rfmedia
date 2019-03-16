import { HttpClient } from '@angular/common/http';
import { Injectable, Compiler, Inject, ReflectiveInjector, Injector, COMPILER_OPTIONS } from '@angular/core';
// Needed for the new modules
import * as AngularCore from '@angular/core';
import * as AngularCommon from '@angular/common';
import * as AngularRouter from '@angular/router';
// Not necessary  
//import * as AngularClarity from '@clr/angular';
import * as BrowserAnimations from '@angular/platform-browser/animations';
import { ModuleData } from './module.model';


declare var SystemJS: any;
/**
 * Class for load dynamic modules
 */
@Injectable()
export class ModuleService {

    source = `http://${window.location.host}/`;

    constructor(private compiler: Compiler, private httpClient: HttpClient) {
    }

    loadModules() {
        return this.httpClient.get("./assets/modules.json");
    }

    loadModule(moduleInfo: ModuleData){
        let url = this.source + moduleInfo.location;
        return this.httpClient.get(url)
            .toPromise().then(res => console.log(res));
    }

    loadModuleSystemJS(moduleInfo: ModuleData): Promise<any> {
        let url = this.source + moduleInfo.location;
        SystemJS.set('@angular/core', SystemJS.newModule(AngularCore));
        SystemJS.set('@angular/common', SystemJS.newModule(AngularCommon));
        SystemJS.set('@angular/router', SystemJS.newModule(AngularRouter));
        SystemJS.set('@angular/platform-browser/animations', SystemJS.newModule(BrowserAnimations));
        //SystemJS.set('@clr/angular', SystemJS.newModule(AngularClarity));
        // now, import the new module
        return SystemJS.import(`${url}`).then((module) => {
            console.log(module);
            return this.compiler.compileModuleAndAllComponentsAsync(module[`${moduleInfo.moduleName}`]).then(compiled => {
                console.log(compiled);
                return module;
            });
        });
    }
}