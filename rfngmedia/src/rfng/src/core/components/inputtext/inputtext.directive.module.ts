
import { NgModule } from "@angular/core";
import { InputTextDirective } from "./inputtext.directive";
import { CommonModule } from "@angular/common";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { BrowserModule } from "@angular/platform-browser";


@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        ReactiveFormsModule
    ],
    exports: [InputTextDirective],
    declarations: [InputTextDirective]
})
export class InputTextDirectiveModule { }