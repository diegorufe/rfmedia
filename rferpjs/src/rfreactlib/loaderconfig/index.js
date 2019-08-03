
import I18nTranslate from '../i18n';
import { RFContext } from '../context';
import AccessResolver from '../desktop/resolvers/access';
import Dom from '../dom';

/**
 * Method to generate configuration for application
 * @param {*} locales is a array for locale files from application 
 * @param {*} accessDesktop is a array for acess desktop
 */
export default function (locales, accessDesktop) {

    // Load locales 
    RFContext.i18n = new I18nTranslate(locales);
    // Load Dom
    RFContext.dom = new Dom();
    // Load access 
    if (accessDesktop != null && accessDesktop != undefined) {
        let accesResolver = new AccessResolver();
        accesResolver.quickaccess = accessDesktop;
        RFContext.desktopAccessResolver = accesResolver;
        RFContext.i18n = new I18nTranslate(null);
    }

}