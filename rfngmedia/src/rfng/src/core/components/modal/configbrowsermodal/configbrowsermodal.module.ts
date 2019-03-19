import { CommonModule } from "@angular/common";
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from "@angular/core";
import { FormsModule } from "@angular/forms";
import { I18nTranslateModule } from "../../../i18n/i18ntranslate.module";
import { DynamicComponentLoaderModule } from "../../dynamic/dynamic.loader.module";
import { ConfigBrowserModalComponent } from "./configbrowsermodal.component";
import { PickListModule } from "../../picklist/picklist.module";
import { InputTextModule } from "../../inputtext/inputtext.module";



@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        I18nTranslateModule,
        DynamicComponentLoaderModule.forChild(ConfigBrowserModalComponent),
        PickListModule,
        InputTextModule
    ],
    declarations: [ConfigBrowserModalComponent],
    exports: [ConfigBrowserModalComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ConfigBrowserModalModule {

}