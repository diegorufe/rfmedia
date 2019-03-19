import { CommonModule } from "@angular/common";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { CoverComponent } from "./cover/cover.component";
import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from "@angular/core";


@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        ReactiveFormsModule     
    ],
    declarations: [CoverComponent],
    exports: [CoverComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ImagesModule {

}