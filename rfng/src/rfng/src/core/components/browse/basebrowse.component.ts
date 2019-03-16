import { BaseComponent } from "../base.component";
import { ChangeDetectorRef, ElementRef, ComponentFactoryResolver, Inject } from "@angular/core";
import { TranslateService } from "@ngx-translate/core";
import { BaseService } from "../../service/base.service";
import { Filter } from "../../beans/filter";
import { Fetch } from "../../beans/fetch";
import { ColumnTable } from "../../beans/columntable";
import { CoreUtils } from "../../utils/core.utils";
import { ConfiguracionBrowserService } from "../configbrowser/configuracionbrowser.service";
import { EnumOperators } from "../../constants/operatos.constants";
import { Order } from "../../beans/order";



export abstract class BaseBrowserComponent extends BaseComponent {

    //  Is table to load data
    lazyTable: any;
    // Filters to lazy table 
    filters: Filter[];
    filtersLazy: Filter[];
    // Fetchs to lazy table
    fetchs: Fetch[];
    // Columns table 
    columnsTable: ColumnTable[];
    // Default columns table 
    defaultColumsTable: ColumnTable[];
    // Default columns table 
    columnsLazyTable: ColumnTable[];
    // Columns table config
    columnsTableConfig: ColumnTable[];
    // Columns table config
    columnsDefaultTableConfig: ColumnTable[];
    // Table name
    tableName: string;
    // Is modal 
    modal: boolean;

    // Id for registry, edit and delete
    redId;

    rowData;

    // ConfigBrowser service
    configBrowserService;

    // Text columns 
    textColumns: Map<string, string>;


    constructor(changeDetectorRef: ChangeDetectorRef, el: ElementRef, componentFactoryResolver: ComponentFactoryResolver, translateService: TranslateService, service?: BaseService) {
        super(changeDetectorRef, el, componentFactoryResolver, translateService, service);
        this.filters = [];
        this.fetchs = [];
        this.columnsTable = [];
        this.defaultColumsTable = [];
        this.modal = false;
        this.tableName = null;
        this.redId = null;
        this.configBrowserService = CoreUtils.getService(ConfiguracionBrowserService);
        this.textColumns = new Map();
        this.loadTableConfig();
        this.loadTableConfigDb();
        this.load();
        this.loadFilters();
        this.styleClassModal = 'BrowseComponentModal';
    }

    loadTableConfig() {

    }

    /**
     * Method to load table config database
     */
    loadTableConfigDb() {
        if (this.tableName != null && this.tableName) {
            const filtersLoad: Filter[] = [];
            filtersLoad.push(new Filter(1,
                1,
                null,
                'tabla',
                EnumOperators.EQUAL,
                this.tableName));
            const oders: Order[] = [];
            oders.push(new Order('orden', 'asc'));
            this.configBrowserService.find(null, filtersLoad, oders, null).toPromise().then(responseFind => {
                if (responseFind.text() != null && responseFind.status == 200) {
                    const dataFind = JSON.parse(responseFind.text()).data;
                    if (dataFind.length > 0) {
                        this.columnsLazyTable = [];
                        for (let i = 0; i < dataFind.length; i++) {
                            let sortable = dataFind[i].columna.trim().includes('caratula');
                            let columnTable = new ColumnTable(this.textColumns.get(dataFind[i].columna), dataFind[i].columna, dataFind[i].size, sortable ? false : dataFind[i].sortable, sortable);
                            columnTable.configuracionBrowser = dataFind[i];
                            columnTable.id = columnTable.configuracionBrowser.id;
                            this.columnsLazyTable.push(columnTable);
                        }
                    } else {
                        this.columnsLazyTable = Object.assign([], this.defaultColumsTable);
                    }
                } else {

                }
            }).catch();
        }
    }

    load() {

    }

    loadFilters() {

    }

    abstract newInstance();

    /**
     * Method to count lazy 
     * @param filters 
     */
    countLazy(filters?) {
        return this.service.count(filters);
    }

    /**
     * Method to find lazy
     * @param fetchs 
     * @param filters 
     * @param orders 
     * @param limit 
     */
    findLazy(fetchs?, filters?, orders?, limit?) {
        return this.service.find(fetchs, filters, orders, limit);
    }

    /**
     * Method to go acctions lazy table
     * @param type 
     * @param rowData 
     * @param rowIndex 
     */
    goActionsLazy(type, rowData, rowIndex, rowDobleClick: boolean) {
        let params = new Map();
        params.set('state', type);
        params.set('rowData', rowData);
        params.set('rowIndex', (this.lazyTable.page * this.lazyTable.recordsPage) + rowIndex);
        params.set('filters', this.filters);
        params.set('modal', this.modal);
        params.set('baseIpuntButtonSelectData', this.baseIpuntButtonSelectData);
        let redActions = false;

        // If doble click row is a select from modal datatable
        if (type === 'S' && rowDobleClick != null && rowDobleClick != undefined && rowDobleClick && this.modal) {
            type = 'T';
        }

        if (type === 'S' || type === 'E') {
            params.set('bodyTabAction', ['loadElement']);
            redActions = true;
        } else if (type === 'R') {
            params.set('bodyTabAction', ['loadStartElement']);
            redActions = true;
        } else if (type === 'D') {
            this.rowData = rowData;
            this.deleteModal();
        } else if (type === 'T') {
            this.baseIpuntButtonSelectData.selectData(rowData, this);
        }
        if (redActions) {
            // Changue the body for tab
            if (CoreUtils.haveAppConfig() && !this.modal) {
                CoreUtils.APP_CONFIG.tabViewComponent.changueBodyTab(this.redId, params);
            } else if (CoreUtils.haveAppConfig() && this.modal) {
                CoreUtils.APP_CONFIG.tabViewComponent.changueBodyModalTab(this.modalComponent, this.redId, params);
            }
        }

    }

    /**
     * Method to open modal to delete
     */
    deleteModal() {
        let mapParams = new Map();
        mapParams.set('baseComponent', this);
        mapParams.set('acctionYes', 'delete');
        mapParams.set('messageConfirmation', 'i18nAppCore.i18nComponents.i18n_genericos_seguro_borrar');
        this.openModal('i18nAppCore.i18nComponents.i18n_genericos_confirme', 'confirmation', mapParams);
    }

    /**
     * Method to delete a register 
     */
    delete() {
        this.setLoadingTab(true);
        const defaulInstance = this.newInstance();
        this.rowData = Object.assign(defaulInstance, this.rowData);
        this.rowData.load();
        this.service.delete(this.rowData).toPromise().then(responseUpdate => {
            if (responseUpdate.text() != null && responseUpdate.status == 200) {
                this.addMsgSucces('i18nAppCore.i18nComponents.i18n_genericos_borrado_ok');
                this.setLoadingTab(false);
                this.lazyTable.loadData();
            }
        }).catch(err => { this.setLoadingTab(false); });
    }

    /**
     * Method to go to config browser
     */
    goConfigBrowser() {
        let mapParams = new Map();
        this.columnsTableConfig = Object.assign([], this.columnsTable);
        this.columnsDefaultTableConfig = Object.assign([], this.columnsLazyTable);
        mapParams.set('baseBrowserComponent', this);
        mapParams.set('loadedConfig', this);
        this.openModal('i18nAppCore.i18nComponents.i18n_genericos_config_browser', 'configBrowserModal', mapParams);
    }

}