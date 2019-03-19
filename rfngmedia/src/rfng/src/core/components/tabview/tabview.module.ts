import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from "@angular/core";
import { CommonModule } from "@angular/common";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { TabViewComponent } from "./tabview.component";
import { DynamicComponentLoaderModule } from "../dynamic/dynamic.loader.module";


@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        DynamicComponentLoaderModule.forChild(TabViewComponent)
    ],
    declarations: [TabViewComponent],
    exports: [TabViewComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TabViewModule {

}