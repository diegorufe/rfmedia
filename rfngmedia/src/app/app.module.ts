import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { LoginModule } from 'src/rfng/src/core/components/login/login.module';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { TranslateModule, TranslateLoader } from '@ngx-translate/core';
import { createTranslateLoader, DynamicComponentLoaderModule, DynamicLoaderManifestComponent } from 'src/rfng/src/public_api';
import {MultiTranslateHttpLoader} from "ngx-translate-multi-http-loader";

/**
 * Method to load i18n for multiple folders 
 * @param http 
 */
export function HttpLoaderFactory(http: HttpClient) {
  return new MultiTranslateHttpLoader(http, [
      {prefix: "./assets/i18n/core/", suffix: ".json"},
      {prefix: "./assets/i18n/app/", suffix: ".json"},
  ]);
}

let manifests: DynamicLoaderManifestComponent[] = [
  {
    componentId: 'rf_dashboard',
    path: 'rf_dashboard', // some globally-unique identifier, used internally by the router
    loadChildren: './core/dashboard/dashboard.module#DashboardModule',
  }
];

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    BrowserModule,
    HttpClientModule,
    LoginModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient]
      }
    }),
    DynamicComponentLoaderModule.forRoot(manifests),
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
