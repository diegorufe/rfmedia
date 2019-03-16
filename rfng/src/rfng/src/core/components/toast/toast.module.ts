import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from "@angular/core";
import { CommonModule } from "@angular/common";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { ToastComponent } from "./toast.component";


@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        ReactiveFormsModule
    ],
    declarations: [ToastComponent],
    exports: [ToastComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ToastModule {

}