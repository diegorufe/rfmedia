
import I18nTranslate from '../i18n';
import { RFContext } from '../context';
import AccessResolver from '../resolvers/access';
import Dom from '../dom';

/**
 * Method to generate configuration for application
 * @param {*} locales is a array for locale files from application 
 * @param {*} permisionsAll is a array of permision for all users
 */
export default function (locales, permisionsAll) {
    // Load locales 
    RFContext.i18n = new I18nTranslate(locales);
    // Load Dom
    RFContext.dom = new Dom();
    // Load access
    RFContext.access = new AccessResolver();
    RFContext.access.permisionsAll = permisionsAll;
}