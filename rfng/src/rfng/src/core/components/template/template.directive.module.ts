import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { TemplateDirective } from "./template.directive";


@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        ReactiveFormsModule
    ],
    exports: [TemplateDirective],
    declarations: [TemplateDirective]
})
export class TemplateDirectiveModule { }