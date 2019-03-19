import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from "@angular/core";
import { CommonModule } from "@angular/common";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { TabViewBodyComponent } from "./tabviewbody.component";
import { DynamicComponentLoaderModule } from "../dynamic/dynamic.loader.module";
import { ToastModule } from "../toast/toast.module";


@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        ToastModule,
        DynamicComponentLoaderModule.forChild(TabViewBodyComponent)
    ],
    declarations: [TabViewBodyComponent],
    exports: [TabViewBodyComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TabViewBodyModule {

}