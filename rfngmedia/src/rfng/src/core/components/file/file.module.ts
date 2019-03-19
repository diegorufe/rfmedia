import { CommonModule } from "@angular/common";
import { FormsModule } from "@angular/forms";
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from "@angular/core";
import { FileComponent } from "./file.component";


@NgModule({
    imports: [
        CommonModule,
        FormsModule   
    ],
    declarations: [FileComponent],
    exports: [FileComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class FileModule {

}