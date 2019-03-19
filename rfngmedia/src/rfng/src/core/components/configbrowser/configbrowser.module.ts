import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from "@angular/core";
import { CommonModule } from "@angular/common";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { ConfigBrowserComponent } from "./configbrowser.component";
import { ConfiguracionBrowserService } from "./configuracionbrowser.service";


@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        ConfigBrowserComponent
    ],
    providers: [ConfiguracionBrowserService],
    declarations: [ConfigBrowserComponent],
    exports: [ConfigBrowserComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ConfigBrowserModule {

}