import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from "@angular/core";
import { CommonModule } from "@angular/common";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { BrowserComponent } from "./browse.component";
import { TableModule } from "../table/table.module";
import { ConfiguracionBrowserService } from "../configbrowser/configuracionbrowser.service";


@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        TableModule
    ],
    providers: [ConfiguracionBrowserService],
    declarations: [BrowserComponent],
    exports: [BrowserComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class BrowseModule {

}