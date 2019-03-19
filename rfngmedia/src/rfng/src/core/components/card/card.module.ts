import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from "@angular/core";
import { CommonModule } from "@angular/common";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { CardComponent } from "./card.component";


@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        ReactiveFormsModule
    ],
    declarations: [CardComponent],
    exports: [CardComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CardModule {

}