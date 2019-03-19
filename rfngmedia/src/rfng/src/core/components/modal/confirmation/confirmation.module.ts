import { CommonModule } from "@angular/common";
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from "@angular/core";
import { FormsModule } from "@angular/forms";
import { ConfirmationComponent } from "./confirmation.component";
import { I18nTranslateModule } from "../../../i18n/i18ntranslate.module";
import { DynamicComponentLoaderModule } from "../../dynamic/dynamic.loader.module";



@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        I18nTranslateModule,
        DynamicComponentLoaderModule.forChild(ConfirmationComponent)
    ],
    declarations: [ConfirmationComponent],
    exports: [ConfirmationComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ConfirmationModule {

}