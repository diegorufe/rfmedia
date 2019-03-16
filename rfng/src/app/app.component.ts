import { Component } from '@angular/core';
import { ModuleService } from 'src/rfng/src/core/module/module.service';
import { ModuleData } from 'src/rfng/src/core/module/module.model';
import { CoreUtils } from 'src/rfng/src/core/utils/core.utils';
import { AppConfig } from 'src/rfng/src/core/beans/appconfig';

declare var SystemJS: any;

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'rfng';
  moduleService;

  constructor(moduleService: ModuleService) {
    this.moduleService = moduleService;
    // This is for create module from external proyect in same host
    // CoreUtils.APP_CONFIG = new AppConfig();
    // CoreUtils.APP_CONFIG.appName = "rfgn";
    // this.moduleService.loadModules().toPromise().then(res => {
    //   let moduleData  = new ModuleData(); 
    //   moduleData.path = res[0].path;
    //   moduleData.location = res[0].location;
    //   moduleData.moduleName = res[0].moduleName;
    //   moduleData.description = res[0].description;
    //   moduleData.registered = res[0].registered;
    //   this.moduleService.loadModuleSystemJS(moduleData);
    // }
    // );
  }
}
