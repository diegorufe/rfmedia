import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from "@angular/core";
import { CommonModule } from "@angular/common";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { TableComponent } from "./table.component";
import { I18nTranslateModule } from "../../i18n/i18ntranslate.module";
import { ImagesModule } from "../images/images.module";


@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        I18nTranslateModule,
        ImagesModule
    ],
    declarations: [TableComponent],
    exports: [TableComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TableModule {

}