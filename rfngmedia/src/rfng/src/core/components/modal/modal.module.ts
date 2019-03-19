import { CommonModule } from "@angular/common";
import { FormsModule } from "@angular/forms";
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from "@angular/core";
import { ModalComponent } from "./modal.component";
import { DynamicComponentLoaderModule } from "../dynamic/dynamic.loader.module";
import { I18nTranslateModule } from "../../i18n/i18ntranslate.module";


@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        I18nTranslateModule,
        DynamicComponentLoaderModule.forChild(ModalComponent)
    ],
    declarations: [ModalComponent],
    exports: [ModalComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ModalModule {

}