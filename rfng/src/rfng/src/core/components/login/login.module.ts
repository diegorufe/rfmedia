import { CommonModule } from "@angular/common";
import { FormsModule } from "@angular/forms";
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from "@angular/core";
import { I18nTranslateModule } from "../../i18n/i18ntranslate.module";
import { LoginComponent } from './login.component';
import { LoginService } from '../../service/login/login.service';


@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        I18nTranslateModule
    ],
    declarations: [LoginComponent],
    exports: [LoginComponent],
    providers: [LoginService],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class LoginModule {

}