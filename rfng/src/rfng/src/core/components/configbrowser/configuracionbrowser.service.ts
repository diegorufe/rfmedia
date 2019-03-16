import { Injectable } from "@angular/core";
import { BaseService } from "../../../public_api";
import { HttpClient } from '@angular/common/http';

@Injectable()
export class ConfiguracionBrowserService extends BaseService{

    constructor(http: HttpClient) {
        super(http,"/configuracion_browser");
    }
}