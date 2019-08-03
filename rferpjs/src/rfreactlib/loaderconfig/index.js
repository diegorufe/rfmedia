
import I18nTranslate from '../i18n';
import { RFContext } from '../context';
import AccessResolver from '../resolvers/access';
import Dom from '../dom';

/**
 * Method to generate configuration for application
 * @param {*} locales is a array for locale files from application 
 * @param {*} access is a array for acess 
 */
export default function (locales, access) {

    // Load locales 
    RFContext.i18n = new I18nTranslate(locales);
    // Load Dom
    RFContext.dom = new Dom();
    // Load access 
    if (access != null && access != undefined) {
        let accesResolver = new AccessResolver();
        accesResolver.quickaccess = access;
        RFContext.desktopAccessResolver = accesResolver;
        RFContext.i18n = new I18nTranslate(null);
    }

}