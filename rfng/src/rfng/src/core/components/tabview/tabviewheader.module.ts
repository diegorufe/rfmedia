import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from "@angular/core";
import { CommonModule } from "@angular/common";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { TabViewHeaderComponent } from "./tabviewheader.component";
import { DynamicComponentLoaderModule } from "../dynamic/dynamic.loader.module";


@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        DynamicComponentLoaderModule.forChild(TabViewHeaderComponent)
    ],
    declarations: [TabViewHeaderComponent],
    exports: [TabViewHeaderComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TabViewHeaderModule {

}