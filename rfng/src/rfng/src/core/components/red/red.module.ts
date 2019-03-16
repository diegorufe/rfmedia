import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from "@angular/core";
import { CommonModule } from "@angular/common";
import { FormsModule } from "@angular/forms";
import { TableModule } from "../table/table.module";
import { RedComponent } from "./red.component";
import { TemplateDirectiveModule } from "../template/template.directive.module";
import { RedToolBarComponent } from "./redtoolbar.component";


@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        TableModule,
        TemplateDirectiveModule
    ],
    declarations: [RedComponent, RedToolBarComponent],
    exports: [RedComponent, RedToolBarComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class RedModule {

}