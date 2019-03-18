import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from "@angular/core";
import { CommonModule } from "@angular/common";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { MenuComponent } from "./menu.component";
import { MenuItemComponent } from "./menuitem.component";
import { I18nTranslateModule } from 'src/rfng/src/core/i18n/i18ntranslate.module';


@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    I18nTranslateModule
  ],
  declarations: [MenuComponent, MenuItemComponent],
  exports: [MenuComponent, MenuItemComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MenuModule {

}