import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from "@angular/core";
import { CommonModule } from "@angular/common";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { HeaderComponent } from "./header.component";
import { I18nTranslateModule } from 'src/rfng/src/core/i18n/i18ntranslate.module';


@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    I18nTranslateModule,
  ],
  declarations: [HeaderComponent, HeaderComponent],
  exports: [HeaderComponent, HeaderComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HeaderModule {

}