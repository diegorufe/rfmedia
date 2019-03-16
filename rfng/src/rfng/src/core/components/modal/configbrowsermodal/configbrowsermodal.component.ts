import { BaseComponent } from "../../base.component";
import { ChangeDetectorRef, ElementRef, ComponentFactoryResolver, Component } from "@angular/core";
import { TranslateService } from "@ngx-translate/core";
import { ConfiguracionBrowser } from "../../../model/configuracionbrowser.model";


@Component({
    selector: 'rf-configbrowsermodal',
    templateUrl: './configbrowsermodal.component.html',
    styleUrls: ['./configbrowsermodal.component.css']
})
export class ConfigBrowserModalComponent extends BaseComponent {

    baseBrowserComponent;
    loadedConfig: boolean;

    constructor(changeDetectorRef: ChangeDetectorRef, el: ElementRef, componentFactoryResolver: ComponentFactoryResolver, translateService: TranslateService) {
        super(changeDetectorRef, el, componentFactoryResolver, translateService);
        this.styleClassModal = 'ConfigBrowserModalComponentModal';
        this.loadedConfig = false;
    }

    /**
     * Method to save
     */
    save() {
        this.loadedConfig = false;
        this.saveColumn(0, true);
    }

    /**
     * Method to save column
     * @param index 
     * @param isSource 
     */
    saveColumn(index, isSource) {
        let configBrowserService = this.baseBrowserComponent.configBrowserService;
        if (isSource) {
            if (index < this.baseBrowserComponent.columnsTableConfig.length) {
                if (this.baseBrowserComponent.columnsTableConfig[index]['configuracionBrowser'] != null) {
                    configBrowserService.delete(this.baseBrowserComponent.columnsTableConfig[index]['configuracionBrowser']).toPromise().then(responseFind => {
                        if (responseFind.text() != null && responseFind.status == 200) {
                            this.saveColumn(index + 1, true);
                        } else {
                            this.saveColumn(index + 1, true);
                        }
                    }).catch(this.saveColumn(index + 1, true));
                } else {
                    this.saveColumn(index + 1, true);
                }
            } else {
                this.saveColumn(0, false);
            }
        } else {
            if (index < this.baseBrowserComponent.columnsDefaultTableConfig.length) {
                let configBrowser: ConfiguracionBrowser = new ConfiguracionBrowser();
                configBrowser.orden = index;
                configBrowser.columna = this.baseBrowserComponent.columnsDefaultTableConfig[index]['field'];
                configBrowser.sortTable = this.baseBrowserComponent.columnsDefaultTableConfig[index]['sorttable'];
                configBrowser.size = this.baseBrowserComponent.columnsDefaultTableConfig[index]['size'];
                configBrowser.tabla = this.baseBrowserComponent.tableName;
                if (this.baseBrowserComponent.columnsDefaultTableConfig[index]['configuracionBrowser'] != null) {
                    configBrowser.id = this.baseBrowserComponent.columnsDefaultTableConfig[index]['configuracionBrowser']['id'];
                }

                if (configBrowser.id != null && configBrowser.id != undefined) {
                    configBrowserService.update(configBrowser).toPromise().then(responseFind => {
                        if (responseFind.text() != null && responseFind.status == 200) {
                            const dataFind = JSON.parse(responseFind.text()).data;
                            this.baseBrowserComponent.columnsDefaultTableConfig[index]['configuracionBrowser'] = dataFind;
                            this.baseBrowserComponent.columnsDefaultTableConfig[index].id = dataFind.id;
                            this.saveColumn(index + 1, false);
                        } else {
                            this.closeConfig(false);
                        }
                    }).catch(this.closeConfig(false));
                } else {
                    configBrowserService.insert(configBrowser).toPromise().then(responseFind => {
                        if (responseFind.text() != null && responseFind.status == 201) {
                            const dataFind = JSON.parse(responseFind.text()).data;
                            this.baseBrowserComponent.columnsDefaultTableConfig[index]['configuracionBrowser'] = dataFind;
                            this.baseBrowserComponent.columnsDefaultTableConfig[index].id = dataFind.id;
                            this.saveColumn(index + 1, false);
                        } else {
                            this.closeConfig(false);
                        }
                    }).catch(this.closeConfig(false));
                }
            } else {
                this.closeConfig(false);
            }
        }
    }

    /**
     * Method to reset table
     */
    reset() {
        this.loadedConfig = false;
        this.deleteColumn(0, true);
    }

    /**
     * Method to delete column
     * @param index 
     * @param isSource 
     */
    deleteColumn(index, isSource) {
        let configBrowserService = this.baseBrowserComponent.configBrowserService;
        if (isSource) {
            if (index < this.baseBrowserComponent.columnsTableConfig.length) {
                if (this.baseBrowserComponent.columnsTableConfig[index]['configuracionBrowser'] != null) {
                    configBrowserService.delete(this.baseBrowserComponent.columnsTableConfig[index]['configuracionBrowser']).toPromise().then(responseFind => {
                        if (responseFind.text() != null && responseFind.status == 200) {
                            this.deleteColumn(index + 1, true);
                        } else {
                            this.deleteColumn(index + 1, true);
                        }
                    }).catch(this.deleteColumn(index + 1, true));
                } else {
                    this.deleteColumn(index + 1, true);
                }
            } else {
                this.deleteColumn(0, false);
            }
        } else {
            if (index < this.baseBrowserComponent.columnsDefaultTableConfig.length) {
                if (this.baseBrowserComponent.columnsDefaultTableConfig[index]['configuracionBrowser'] != null) {
                    configBrowserService.delete(this.baseBrowserComponent.columnsDefaultTableConfig[index]['configuracionBrowser']).toPromise().then(responseFind => {
                        if (responseFind.text() != null && responseFind.status == 200) {
                            this.deleteColumn(index + 1, false);
                        } else {
                            this.closeConfig(true);
                        }
                    }).catch(this.closeConfig(true));
                } else {
                    this.closeConfig(true);
                }
            } else {
                this.closeConfig(true);
            }
        }
    }

    /**
     * Method to close modal 
     * @param isReset 
     */
    closeConfig(isReset) {
        this.loadedConfig = true;
        this.baseBrowserComponent.lazyTable.resetTable();
        this.modalComponent.closeModal();
    }



}