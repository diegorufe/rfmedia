import { DashboardComponent } from "./dashboard.component";
import { CommonModule } from "@angular/common";
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from "@angular/core";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { MenuModule } from "../menu/menu.module";
import { HeaderModule } from "../header/header.module";
import { I18nTranslateModule } from 'src/rfng/src/core/i18n/i18ntranslate.module';
import { DynamicComponentLoaderModule, DynamicLoaderManifestComponent } from 'src/rfng/src/public_api';
import { LoginModule } from 'src/rfng/src/core/components/login/login.module';


// This array defines which "componentId" maps to which lazy-loaded module.
let mainefestRoutes: DynamicLoaderManifestComponent[] = [
  {
    componentId: 'tabview',
    path: 'tabview', // some globally-unique identifier, used internally by the router
    loadChildren: '../../../rfng/src/core/components/tabview/tabview.module#TabViewModule',
  },
  {
    componentId: 'tabviewheader',
    path: 'tabviewheader', // some globally-unique identifier, used internally by the router
    loadChildren: '../../../rfng/src/core/components/tabview/tabviewheader.module#TabViewHeaderModule',
  },
  {
    componentId: 'tabviewbody',
    path: 'tabviewbody', // some globally-unique identifier, used internally by the router
    loadChildren: '../../../rfng/src/core/components/tabview/tabviewbody.module#TabViewBodyModule',
  },
  {
    componentId: 'confirmation',
    path: 'confirmation', // some globally-unique identifier, used internally by the router
    loadChildren: '../../../rfng/src/core/components/modal/confirmation/confirmation.module#ConfirmationModule',
  },
  {
    componentId: 'modal',
    path: 'modal', // some globally-unique identifier, used internally by the router
    loadChildren: '../../../rfng/src/core/components/modal/modal.module#ModalModule',
  },
  {
    componentId: 'configBrowserModal',
    path: 'configBrowserModal', // some globally-unique identifier, used internally by the router
    loadChildren: '../../../rfng/src/core/components/modal/configbrowsermodal/configbrowsermodal.module#ConfigBrowserModalModule',
  },
];

//RouterModule.forRoot(routesModule)
@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    MenuModule,
    HeaderModule,
    I18nTranslateModule,
    DynamicComponentLoaderModule.forChild(DashboardComponent),
    DynamicComponentLoaderModule.forRoot(mainefestRoutes),
    LoginModule
  ],
  declarations: [DashboardComponent],
  exports: [DashboardComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DashboardModule {

}