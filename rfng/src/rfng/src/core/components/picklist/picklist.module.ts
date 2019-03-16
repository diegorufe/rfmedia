import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from "@angular/core";
import { CommonModule } from "@angular/common";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { I18nTranslateModule } from "../../i18n/i18ntranslate.module";
import { ImagesModule } from "../images/images.module";
import { PickListComponent } from "./picklist.component";


@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        I18nTranslateModule
    ],
    declarations: [PickListComponent],
    exports: [PickListComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PickListModule {

}