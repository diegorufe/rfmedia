import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from "@angular/core";
import { InputTextComponent } from "./inputtext.component";
import { InputTextDirectiveModule } from "./inputtext.directive.module";
import { CommonModule } from "@angular/common";
import { FormsModule } from "@angular/forms";


@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        InputTextDirectiveModule
    ],
    declarations: [InputTextComponent],
    exports: [InputTextComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InputTextModule {

}