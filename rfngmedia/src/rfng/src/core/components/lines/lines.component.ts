import { BaseComponent } from "../base.component";
import { OnInit, ComponentFactoryResolver, Input, Component, OnChanges, SimpleChanges, ChangeDetectorRef, ContentChild, TemplateRef, ElementRef } from "@angular/core";
import { Filter } from "../../beans/filter";
import { Fetch } from "../../beans/fetch";
import { TranslateService } from "@ngx-translate/core";
import { CoreUtils } from "../../utils/core.utils";
import { EnumFetchs } from "../../constants/fetchs.constants";

@Component({
    selector: 'rf-lines',
    templateUrl: './lines.component.html',
    styleUrls: ['./lines.component.css']
})
export class LinesComponent extends BaseComponent implements OnInit, OnChanges {

    @Input()
    baseComponent;
    @Input()
    baseItem;
    @Input()
    propertyBase;
    @Input()
    nameService;
    @Input()
    baseFetch?;
    @ContentChild(TemplateRef)
    templateVariable: TemplateRef<any>;

    items: any[] = [];
    itemsBis: any[] = [];
    service;

    constructor(changeDetectorRef: ChangeDetectorRef, el: ElementRef, componentFactoryResolver: ComponentFactoryResolver, translateService: TranslateService) {
        super(changeDetectorRef, el, componentFactoryResolver, translateService);
    }

    ngOnInit() {
        if (this.nameService != null && this.nameService != undefined) {
            this.service = CoreUtils.getServiceByName(this.nameService);
        }
        this.refresh();
    }

    ngOnChanges(changes: SimpleChanges) {
        let baseItemChague = changes['baseItem'];
        if (baseItemChague != null && baseItemChague != undefined) {
            let current = baseItemChague['currentValue'];
            let previus = baseItemChague['previousValue'];
            if (previus == undefined || current['id'] != previus['id']) {
                this.baseItem = current;
                this.refresh();
            }
        }
    }

    /**
     * Method to refresh before save
     */
    refreshSave() {
        this.baseComponent.addMsgSucces('i18nAppCore.i18nComponents.i18n_genericos_save_lines');
        this.refresh();
    }

    /**
     * Method to refresh
     */
    refresh() {
        if (this.baseItem != null && this.baseItem != undefined && this.service != null && this.service != undefined) {
            const fetchsEnv = [];
            if (this.baseFetch != null && this.baseFetch != undefined) {
                fetchsEnv.push(new Fetch(EnumFetchs.LEFT, this.baseFetch));
            }
            const filtersEnv = [];
            this.items = [];
            this.itemsBis = [];
            filtersEnv.push(new Filter(1, 1, ' AND ', this.propertyBase, ' = ', this.baseItem.id));
            this.service.find(fetchsEnv, filtersEnv, null, null).toPromise().then(responseFind => {
                if (responseFind.text() != null && responseFind.status == 200) {
                    const dataFind = JSON.parse(responseFind.text());
                    const itemsRes = Object.assign([], dataFind.data);
                    for (let i = 0; i < itemsRes.length; i++) {
                        this.items.push(Object.assign(this.constructorInstaceValue(), itemsRes[i]))
                    }
                    this.itemsBis = [];
                } else {
                    this.items = [];

                }
                this.changeDetectorRef.markForCheck();
                this.changeDetectorRef.detectChanges();
            }).catch(err => { console.log(err); this.baseComponent.goBrowser(); });
        } else {
            if (this.service != null && this.service != undefined) {
                this.items = [];
                this.itemsBis = [];
                this.changeDetectorRef.markForCheck();
                this.changeDetectorRef.detectChanges();
            }
        }
    }

    /**
     * Method to add
     */
    add() {
        let data = this.constructorInstaceValue();
        if (this.items == null) {
            this.items = [];
        }
        data.load();
        data[this.propertyBase.split('.')[0]] = this.baseItem;
        this.items.push(data);
        this.changeDetectorRef.markForCheck();
        this.changeDetectorRef.detectChanges();
    }

    /**
     * Method to save
     */
    save() {
        if (this.items.length > 0) {
            this.saveLine(0);
        } else if (this.itemsBis.length > 0) {
            this.removeLineBis(0);
        } else {
            this.refreshSave();
        }
    }

    /**
     * Method to save one line
     * @param index 
     */
    saveLine(index) {
        let update = this.items[index];
        let remove = false;
        if (this.baseFetch != null && this.baseFetch != undefined) {
            if (this.items[index][this.baseFetch] == null || this.items[index][this.baseFetch] == undefined ||
                this.items[index][this.baseFetch]['id'] == null || this.items[index][this.baseFetch]['id'] == undefined) {
                remove = true;
            }
        }
        if (this.items[index]['id'] != undefined && this.items[index]['id'] != null) {
            update = true;
        }
        if (!remove) {
            this.items[index][this.propertyBase.split('.')[0]] = this.baseItem;
            if (update) {
                this.service.update(this.items[index]).toPromise().then(responseDelete => {
                    if (responseDelete.status == 200) {
                        this.indexLineValid(index);
                    }
                }).catch(err => { });
            } else {
                this.service.insert(this.items[index]).toPromise().then(responseDelete => {
                    if (responseDelete.status == 201) {
                        this.indexLineValid(index);
                    }
                }).catch(err => { });
            }
        } else {
            this.indexLineValid(index);
        }
    }

    /**
     * Method to know line is valid
     * @param index 
     */
    indexLineValid(index) {
        if (index + 1 < this.items.length) {
            this.saveLine(index + 1);
        } else {
            if (this.itemsBis.length > 0) {
                this.removeLineBis(0);
            } else {
                this.refreshSave();
            }
        }
    }

    /**
     * Method to remove lines
     * @param index 
     */
    removeLineBis(index) {
        if (this.itemsBis[index].id != null) {
            this.service.delete(this.itemsBis[index]).toPromise().then(responseDelete => {
                if (responseDelete.status == 200) {
                    this.indexLineValidBis(index);
                }
            }).catch(err => { });
        }
    }

    /**
     * Method to valide line bis
     * @param index 
     */
    indexLineValidBis(index) {
        if (index + 1 < this.itemsBis.length) {
            this.removeLineBis(index + 1);
        } else {
            this.refreshSave();
        }
    }

    /**
     * Method to remove line
     * @param index 
     */
    removeLine(index) {
        if (index !== -1) {
            if (this.itemsBis == null) {
                this.itemsBis = [];
            }
            this.itemsBis.push(this.items[index]);
            this.items.splice(index, 1);
        }
    }

    constructorInstaceValue() {
        return CoreUtils.getInstanceModelByService(this.nameService);
    }

    getItem(index) {
        return this.items[index];
    }
}