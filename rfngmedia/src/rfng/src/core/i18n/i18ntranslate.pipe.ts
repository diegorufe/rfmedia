import { ChangeDetectorRef, EventEmitter, Injectable, OnDestroy, Pipe, PipeTransform } from '@angular/core';
import { TranslationChangeEvent, LangChangeEvent, DefaultLangChangeEvent, TranslateService } from '@ngx-translate/core';
import { CoreUtils } from '../utils/core.utils';


@Injectable()
@Pipe({
    name: 'rftranslate',
    pure: false // required to update the value when the promise is resolved
})
export class I18nTranslatePipe implements PipeTransform, OnDestroy {
    value: string = '';
    lastKey: string;
    lastParams: any[];
    onTranslationChange: EventEmitter<TranslationChangeEvent>;
    onLangChange: EventEmitter<LangChangeEvent>;
    onDefaultLangChange: EventEmitter<DefaultLangChangeEvent>;
    translate;

    constructor(translate: TranslateService, private _ref: ChangeDetectorRef) {
        if(CoreUtils.haveAppTranslate()){
            this.translate = CoreUtils.APP_TRANSLATE;
        }else{
            this.translate = translate;
        }
    }

    updateValue(key: string, interpolateParams?: Object, translations?: any): void {
        let onTranslation = (res: string) => {
            this.value = res !== undefined ? res : key;
            this.lastKey = key;
            this._ref.markForCheck();
        };
        if (translations) {
            let res = this.translate.getParsedResult(translations, key, interpolateParams);
            if (typeof res.subscribe === 'function') {
                res.subscribe(onTranslation);
            } else {
                onTranslation(res);
            }
        }
        this.translate.get(key, interpolateParams).subscribe(onTranslation);
    }

    transform(query: string, ...args: any[]): any {
        if (!query || query.length === 0) {
            return query;
        }

        // if we ask another time for the same key, return the last value
        if (equals(query, this.lastKey) && equals(args, this.lastParams)) {
            return this.value;
        }

        let interpolateParams: Object;
        if (isDefined(args[0]) && args.length) {
            if (typeof args[0] === 'string' && args[0].length) {
                // we accept objects written in the template such as {n:1}, {'n':1}, {n:'v'}
                // which is why we might need to change it to real JSON objects such as {"n":1} or {"n":"v"}
                let validArgs: string = args[0]
                    .replace(/(\')?([a-zA-Z0-9_]+)(\')?(\s)?:/g, '"$2":')
                    .replace(/:(\s)?(\')(.*?)(\')/g, ':"$3"');
                try {
                    interpolateParams = JSON.parse(validArgs);
                } catch (e) {
                    throw new SyntaxError(`Wrong parameter in TranslatePipe. Expected a valid Object, received: ${args[0]}`);
                }
            } else if (typeof args[0] === 'object' && !Array.isArray(args[0])) {
                interpolateParams = args[0];
            }
        }

        // store the query, in case it changes
        this.lastKey = query;

        // store the params, in case they change
        this.lastParams = args;

        // set the value
        this.updateValue(query, interpolateParams);

        // if there is a subscription to onLangChange, clean it
        this._dispose();

        // subscribe to onTranslationChange event, in case the translations change
        if (!this.onTranslationChange) {
            this.onTranslationChange = this.translate.onTranslationChange.subscribe((event: TranslationChangeEvent) => {
                if (this.lastKey && event.lang === this.translate.currentLang) {
                    this.lastKey = null;
                    this.updateValue(query, interpolateParams, event.translations);
                }
            });
        }

        // subscribe to onLangChange event, in case the language changes
        if (!this.onLangChange) {
            this.onLangChange = this.translate.onLangChange.subscribe((event: LangChangeEvent) => {
                if (this.lastKey) {
                    this.lastKey = null; // we want to make sure it doesn't return the same value until it's been updated
                    this.updateValue(query, interpolateParams, event.translations);
                }
            });
        }

        // subscribe to onDefaultLangChange event, in case the default language changes
        if (!this.onDefaultLangChange) {
            this.onDefaultLangChange = this.translate.onDefaultLangChange.subscribe(() => {
                if (this.lastKey) {
                    this.lastKey = null; // we want to make sure it doesn't return the same value until it's been updated
                    this.updateValue(query, interpolateParams);
                }
            });
        }

        return this.value;
    }

    /**
     * Clean any existing subscription to change events
     */
    private _dispose(): void {
        if (typeof this.onTranslationChange !== 'undefined') {
            this.onTranslationChange.unsubscribe();
            this.onTranslationChange = undefined;
        }
        if (typeof this.onLangChange !== 'undefined') {
            this.onLangChange.unsubscribe();
            this.onLangChange = undefined;
        }
        if (typeof this.onDefaultLangChange !== 'undefined') {
            this.onDefaultLangChange.unsubscribe();
            this.onDefaultLangChange = undefined;
        }
    }

    ngOnDestroy(): void {
        this._dispose();
    }
}

/* tslint:disable */
/**
 * Determines if two objects or two values are equivalent.
 *
 * Two objects or values are considered equivalent if at least one of the following is true:
 *
 * * Both objects or values pass `===` comparison.
 * * Both objects or values are of the same type and all of their properties are equal by
 *   comparing them with `equals`.
 *
 * @param o1 Object or value to compare.
 * @param o2 Object or value to compare.
 * @returns true if arguments are equal.
 */
export function equals(o1: any, o2: any): boolean {
    if (o1 === o2) return true;
    if (o1 === null || o2 === null) return false;
    if (o1 !== o1 && o2 !== o2) return true; // NaN === NaN
    let t1 = typeof o1, t2 = typeof o2, length: number, key: any, keySet: any;
    if (t1 == t2 && t1 == 'object') {
        if (Array.isArray(o1)) {
            if (!Array.isArray(o2)) return false;
            if ((length = o1.length) == o2.length) {
                for (key = 0; key < length; key++) {
                    if (!equals(o1[key], o2[key])) return false;
                }
                return true;
            }
        } else {
            if (Array.isArray(o2)) {
                return false;
            }
            keySet = Object.create(null);
            for (key in o1) {
                if (!equals(o1[key], o2[key])) {
                    return false;
                }
                keySet[key] = true;
            }
            for (key in o2) {
                if (!(key in keySet) && typeof o2[key] !== 'undefined') {
                    return false;
                }
            }
            return true;
        }
    }
    return false;
}
/* tslint:enable */

export function isDefined(value: any): boolean {
    return typeof value !== 'undefined' && value !== null;
}

export function isObject(item: any): boolean {
    return (item && typeof item === 'object' && !Array.isArray(item));
}

export function mergeDeep(target: any, source: any): any {
    let output = Object.assign({}, target);
    if (isObject(target) && isObject(source)) {
        Object.keys(source).forEach((key: any) => {
            if (isObject(source[key])) {
                if (!(key in target)) {
                    Object.assign(output, { [key]: source[key] });
                } else {
                    output[key] = mergeDeep(target[key], source[key]);
                }
            } else {
                Object.assign(output, { [key]: source[key] });
            }
        });
    }
    return output;
}