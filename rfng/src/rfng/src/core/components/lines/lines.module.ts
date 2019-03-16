import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { FormsModule } from "@angular/forms";
import { LinesComponent } from "./lines.component";
import { I18nTranslateModule } from "../../i18n/i18ntranslate.module";

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        I18nTranslateModule
    ],
    declarations: [LinesComponent],
    exports: [LinesComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class LinesModule {

}