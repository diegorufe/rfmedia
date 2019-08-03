import _ from "lodash";
import locales from "./locales";

class I18nTranslate {

    constructor(translations) {
        let finalLocales = locales;
        
        if (translations !== null && translations !== undefined && translations.length > 0) {
            let translation = null;
            let locale = null;
            for (let i = 0; i < translations.length; i++) {
                translation = translations[i];
                Object.keys(translation).forEach(function (key) {
                    locale = translation[key];
                    Object.keys(locale).forEach(function (keyLocale) {
                        finalLocales[key][keyLocale] = locale[keyLocale];
                    });
                });
            }
        }
        this.translations = finalLocales;
        // Set default language
        this.language = 'en';
    }

    __(text) {
        if (_.isNil(this.language)) return text;
        if (_.isNil(this.translations[this.language])) return text;
        //const translation = this.translations[this.language];
        // For use multi modolue translation in json
        return eval('!_.isNil( this.translations[this.language].'+text+') ?  this.translations[this.language].'+text+' : '+text+';');
        //return !_.isNil(translation[text]) ? translation[text] : text;
    }

    setLocale(language) {
        this.language = language;
    }
}

export default I18nTranslate;