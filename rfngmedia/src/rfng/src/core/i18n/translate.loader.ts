import { HttpClient } from "@angular/common/http";
import {TranslateHttpLoader} from '@ngx-translate/http-loader';

/**
 * Function to create default translate loader
 * @param httpClient 
 */
export function createTranslateLoader(httpClient: HttpClient) {
    return new TranslateHttpLoader(httpClient, './assets/i18n/', '.json');
}