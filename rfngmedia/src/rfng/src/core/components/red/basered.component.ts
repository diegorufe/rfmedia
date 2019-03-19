import { BaseComponent } from "../base.component";
import { ChangeDetectorRef, ElementRef, ComponentFactoryResolver } from "@angular/core";
import { TranslateService } from "@ngx-translate/core";
import { BaseService } from "../../service/base.service";
import { Filter } from "../../beans/filter";
import { Fetch } from "../../beans/fetch";
import { Order } from "../../beans/order";
import { EnumStates } from "../../constants/states.constants";
import { EnumFields } from "../../constants/fields.constants";
import { EnumOperators } from "../../constants/operatos.constants";
import { FormGroup, ValidationErrors } from "@angular/forms";
import { Limit } from "../../beans/limit";
import { CoreUtils } from "../../utils/core.utils";


export abstract class BaseRedComponent extends BaseComponent {

    filters: Filter[];
    fetchs: Fetch[];
    orders: Order[];
    browserId: string;
    state: string;
    element: any;
    rowData: any;
    rowIndex: number;
    totalReg: number;
    modal: boolean;

    constructor(changeDetectorRef: ChangeDetectorRef, el: ElementRef, componentFactoryResolver: ComponentFactoryResolver, translateService: TranslateService, service?: BaseService) {
        super(changeDetectorRef, el, componentFactoryResolver, translateService, service);
        this.state = 'S';
        this.rowIndex = 0;
        this.totalReg = 0;
        this.rowData = null;
        this.modal = false;
        this.fetchs = [];
        this.styleClassModal = 'BrowseComponentModal';
    }

    /**
     * Method to change state
     * @param state 
     */
    changeState(state) {
        this.setLoadingTab(true);
        switch (state) {
            case EnumStates.SHEARCH:
            case EnumStates.EDIT:
                this.state = state;
                this.loadElement();
                break;
            case EnumStates.REGISTER:
                this.state = state;
                this.loadStartElement();
                break;
            case EnumStates.DELETE:
                this.state = 'S';
                this.deleteModal();
                break;
        }
    }

    /**
     * Method to start element
     */
    loadStartElement() {
        this.element = this.newInstance();
        this.defaultInstance();
        this.element.load();
        this.setLoadingTab(false);
    }

    /**
     * Method to load element
     */
    loadElement() {
        if (this.rowData != null && this.rowData != undefined) {
            this.element = Object.assign(this.newInstance(), this.rowData);
            this.rowData = null;
        }
        this.element = Object.assign(this.newInstance(), this.element);
        this.element.load();
        const filtersLoad: Filter[] = [];
        filtersLoad.push(new Filter(1,
            1,
            null,
            EnumFields.ID,
            EnumOperators.EQUAL,
            this.element.id));
        this.service.find(this.fetchs, filtersLoad).toPromise().then(responseFind => {
            if (responseFind.text() != null && responseFind.status == 200) {
                const dataFind = JSON.parse(responseFind.text());
                if (dataFind.data[0] == null || dataFind.data[0] == undefined) {
                    this.first();
                } else {
                    this.element = Object.assign(this.newInstance(), dataFind.data[0]);
                    this.element.load();
                    this.setLoadingTab(false);
                }
            }
        }).catch(err => {this.setLoadingTab(false); });
    }

    /**
     * Method to save form 
     * @param form 
     */
    save(form: FormGroup) {
        // Validate form is valid
        this.setLoadingTab(true);
        if (form.invalid != null && form.invalid != undefined && form.invalid == true) {
            // Sino es válido mostramos errores y nos posicionamos en el primer control 
            this.getFormValidationErrors(form);
            this.setLoadingTab(false);
        } else {
            switch (this.state) {
                case EnumStates.REGISTER:
                    if (this.valideteRegister()) {
                        const defaulInstance = this.newInstance();
                        this.element = Object.assign(defaulInstance, this.element);
                        this.element.load();
                        this.service.insert(this.element).toPromise().then(responseUpdate => {
                            if (responseUpdate.text() != null && responseUpdate.status == 201) {
                                const dataFind = JSON.parse(responseUpdate.text());
                                this.element = dataFind.data;
                                this.element = Object.assign(this.newInstance(), this.element);
                                this.element.load();
                                this.addMsgSucces('i18nAppCore.i18nComponents.i18n_genericos_creado_ok');
                                this.changeState('S');
                                this.setLoadingTab(false);
                            }
                        }).catch(err => { this.setLoadingTab(false); });
                    }
                    break;
                case EnumStates.EDIT:
                    if (this.valideteEdit()) {
                        const defaulInstance = this.newInstance();
                        this.element = Object.assign(defaulInstance, this.element);
                        this.element.load();
                        this.service.update(this.element).toPromise().then(responseUpdate => {
                            if (responseUpdate.text() != null && responseUpdate.status == 200) {
                                const dataFind = JSON.parse(responseUpdate.text());
                                this.element = dataFind.data;
                                this.element = Object.assign(this.newInstance(), this.element);
                                this.element.load();
                                this.addMsgSucces('i18nAppCore.i18nComponents.i18n_genericos_modificado_ok');
                                this.changeState('S');
                                this.setLoadingTab(false);
                            }
                        }).catch(err => { this.setLoadingTab(false); });
                    }
                    break;
            }
        }
    }

    delete() {
        this.setLoadingTab(true);
        const defaulInstance = this.newInstance();
        this.element = Object.assign(defaulInstance, this.element);
        this.element.load();
        this.service.delete(this.element).toPromise().then(responseUpdate => {
            if (responseUpdate.text() != null && responseUpdate.status == 200) {
                this.addMsgSucces('i18nAppCore.i18nComponents.i18n_genericos_borrado_ok');
                this.changeState('S');
                this.prev();
                this.setLoadingTab(false);
            }
        }).catch(err => { this.setLoadingTab(false); });
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
     * Method to validate register
     */
    valideteRegister() {
        return true;
    }

    /**
     * Method to validate edit
     */
    valideteEdit() {
        return true;
    }

    getFormValidationErrors(form: FormGroup) {
        let controlFocus = null;
        let label = null;
        // Recorremos los controles del formulario y miramos cuales tiene error
        Object.keys(form.controls).forEach(key => {
            const controlErrors: ValidationErrors = form.get(key).errors;
            if (controlErrors != null) {
                Object.keys(controlErrors).forEach(keyError => {
                    label = null;
                    if (controlFocus == null) {
                        controlFocus = key;
                    }
                    label = document.getElementsByName(key)[0].getAttribute('ng-reflect-label');
                    if (label != null && label != undefined) {
                        this.addMsgErrorClear('i18nAppCore.i18nComponents.i18n_comunes_required', false, [label]);
                    }
                    //console.log('Key control: ' + key + ', keyError: ' + keyError + ', err value: ', controlErrors[keyError]);
                    // Si hay errores los vamos añadiendo a los mensajes de error del mantenimiento
                });
            }
        });
        // SI ha habido errores y hay un componente para fijar el foco lo fijamos
        if (controlFocus != null) {
            controlFocus = document.getElementsByName(controlFocus)[1];
            //console.log(controlFocus);
            if (controlFocus != null && controlFocus != undefined) {
                controlFocus.focus();
            }
        }
    }

    /**
    * Method to go next register
    */
    next() {
        this.setLoadingTab(true);
        this.service.count(this.filters).toPromise().then(responseCount => {
            if (responseCount.text() != null && responseCount.status == 200) {
                const dataCount = JSON.parse(responseCount.text());
                this.totalReg = dataCount.data;
                if (this.totalReg > 0) {
                    this.rowIndex = this.rowIndex + 1;
                    if (this.rowIndex > this.totalReg - 1) {
                        this.rowIndex = this.totalReg - 1;
                    }
                    let limit: Limit = new Limit(this.rowIndex, 1);
                    this.service.find(this.fetchs, this.filters, this.orders, limit).toPromise().then(responseFind => {
                        if (responseFind.text() != null && responseFind.status == 200) {
                            const dataFind = JSON.parse(responseFind.text());
                            this.element = Object.assign(this.newInstance(), dataFind.data[0]);
                            this.element.load();
                            this.setLoadingTab(false);
                        }else{
                            this.setLoadingTab(false);
                        }
                    }).catch(err => { this.goBrowser(); });
                } else {
                    this.setLoadingTab(false);
                    this.goBrowser();
                }
            }
        }).catch(err => { this.setLoadingTab(true); this.goBrowser(); });
    }

    /**
     * Method to go last register
     */
    last() {
        this.setLoadingTab(true);
        this.service.count(this.filters).toPromise().then(responseCount => {
            if (responseCount.text() != null && responseCount.status == 200) {
                const dataCount = JSON.parse(responseCount.text());
                this.totalReg = dataCount.data;
                if (this.totalReg > 0) {
                    this.rowIndex = this.totalReg - 1;
                    let limit: Limit = new Limit(this.rowIndex, 1);
                    this.service.find(this.fetchs, this.filters, this.orders, limit).toPromise().then(responseFind => {
                        if (responseFind.text() != null && responseFind.status == 200) {
                            const dataFind = JSON.parse(responseFind.text());
                            this.element = Object.assign(this.newInstance(), dataFind.data[0]);
                            this.element.load();
                            this.setLoadingTab(false);
                        }else{
                            this.setLoadingTab(false);
                        }
                    }).catch(err => { this.setLoadingTab(false); this.goBrowser(); });
                } else {
                    this.setLoadingTab(false);
                    this.goBrowser();
                }
            }
        }).catch(err => { this.setLoadingTab(false); this.goBrowser(); });
    }

    /**
    * Method to go previuos register
    */
    prev() {
        this.setLoadingTab(true);
        this.service.count(this.filters).toPromise().then(responseCount => {
            if (responseCount.text() != null && responseCount.status == 200) {
                const dataCount = JSON.parse(responseCount.text());
                this.totalReg = dataCount.data;
                if (this.totalReg > 0) {
                    this.rowIndex = this.rowIndex - 1;
                    if (this.rowIndex < 0) {
                        this.rowIndex = 0;
                    }
                    let limit: Limit = new Limit(this.rowIndex, this.rowIndex + 1);
                    this.service.find(this.fetchs, this.filters, this.orders, limit).toPromise().then(responseFind => {
                        if (responseFind.text() != null && responseFind.status == 200) {
                            const dataFind = JSON.parse(responseFind.text());
                            this.element = Object.assign(this.newInstance(), dataFind.data[0]);
                            this.element.load();
                            this.setLoadingTab(false);
                        }else{
                            this.setLoadingTab(false);
                        }
                    }).catch(err => { this.setLoadingTab(false); this.goBrowser(); });
                } else {
                    this.setLoadingTab(false);
                    this.goBrowser();
                }
            }
        }).catch(err => {this.setLoadingTab(false); this.goBrowser(); });
    }

    /**
     * Method to go first register
     */
    first() {
        this.setLoadingTab(true);
        this.service.count(this.filters).toPromise().then(responseCount => {
            if (responseCount.text() != null && responseCount.status == 200) {
                const dataCount = JSON.parse(responseCount.text());
                this.totalReg = dataCount.data;
                if (this.totalReg > 0) {
                    this.rowIndex = 0;
                    let limit: Limit = new Limit(this.rowIndex, this.rowIndex + 1);
                    this.service.find(this.fetchs, this.filters, this.orders, limit).toPromise().then(responseFind => {
                        if (responseFind.text() != null && responseFind.status == 200) {
                            const dataFind = JSON.parse(responseFind.text());
                            this.element = Object.assign(this.newInstance(), dataFind.data[0]);
                            this.element.load();
                            this.setLoadingTab(false);
                        }else{
                            this.setLoadingTab(false);
                        }
                    }).catch(err => { this.setLoadingTab(false); this.goBrowser(); });
                } else {
                    this.setLoadingTab(false);
                    this.goBrowser();
                }
            }
        }).catch(err => { this.setLoadingTab(false); this.goBrowser(); });
    }

    /**
     * Method to go browser
     */
    goBrowser() {
        let params = new Map();
        params.set('filters', this.filters);
        params.set('modal', this.modal);
        params.set('baseIpuntButtonSelectData', this.baseIpuntButtonSelectData);
        // Changue the body for tab
        if (CoreUtils.haveAppConfig() && !this.modal) {
            CoreUtils.APP_CONFIG.tabViewComponent.changueBodyTab(this.browserId, params);
        } else if (CoreUtils.haveAppConfig() && this.modal) {
            CoreUtils.APP_CONFIG.tabViewComponent.changueBodyModalTab(this.modalComponent, this.browserId, params);
        }
    }

    /**
     * Method to cancel acctions
     */
    cancel() {
        this.changeState('S');
    }


    abstract newInstance();

    abstract defaultInstance();

}