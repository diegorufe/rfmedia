import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from "@angular/core";
import { I18nTranslatePipe } from "./i18ntranslate.pipe";

@NgModule({
    imports: [
    ],
    declarations: [I18nTranslatePipe],
    exports: [I18nTranslatePipe],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
  })
  export class I18nTranslateModule {
  
  }