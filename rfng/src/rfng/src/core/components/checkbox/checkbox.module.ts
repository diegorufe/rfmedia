import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from "@angular/core";
import { CommonModule } from "@angular/common";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { CheckBoxComponent } from "./checkbox.component";


@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        ReactiveFormsModule
    ],
    declarations: [CheckBoxComponent],
    exports: [CheckBoxComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CheckBoxModule {

}