import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from "@angular/core";
import { CommonModule } from "@angular/common";
import { FormsModule } from "@angular/forms";
import { InputTextModule } from "../inputtext/inputtext.module";
import { InputTextButtonComponent } from "./inputtextbutton.component";


@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        InputTextModule
    ],
    declarations: [InputTextButtonComponent],
    exports: [InputTextButtonComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InputTextButtonModule {

}