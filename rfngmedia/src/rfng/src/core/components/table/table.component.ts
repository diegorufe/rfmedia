import { BaseComponent } from "../base.component";
import { ElementRef, ComponentFactoryResolver, OnInit, Component, Input, AfterContentInit, ContentChildren, TemplateRef, QueryList, ChangeDetectorRef } from "@angular/core";
import { TranslateService } from "@ngx-translate/core";
import { TemplateDirective } from "../template/template.directive";
import { PageTable } from "../../beans/pagetable";
import { Order } from "../../beans/order";
import { Limit } from "../../beans/limit";
import { Filter } from "../../beans/filter";
import { Fetch } from "../../beans/fetch";
import { ColumnTable } from "../../beans/columntable";

@Component({
    selector: 'rf-table',
    templateUrl: './table.component.html',
    styleUrls: ['./table.component.css']
})
export class TableComponent extends BaseComponent implements OnInit, AfterContentInit {

    @Input()
    lazy?: boolean;

    @Input()
    columnsTable?: ColumnTable[];

    @Input()
    caption?: string;

    @Input()
    data?: any[];

    @Input()
    recordsPage?: number;
    @Input()
    pagesVisibles?: number;
    @Input()
    emptyMessage?: string;

    @Input()
    componentLazy?: any;

    @Input()
    hasActions?: boolean;

    @Input()
    renderedShow?: boolean;
    @Input()
    renderedEdit?: boolean;
    @Input()
    renderedDelete?: boolean;
    @Input()
    renderedSelect?: boolean;

    // Templates
    @ContentChildren(TemplateDirective) templates: QueryList<TemplateDirective>;
    headerTemplate: TemplateRef<any>;
    bodyTemplate: TemplateRef<any>;
    captionTemplate: TemplateRef<any>;

    @Input()
    styleAcctions?;
    page;
    dataView: any[];
    totalRecords: number;
    pages: number;
    pagesView: PageTable[];
    selectedValue;

    orders: Order[];
    limit: Limit;
    filters: Filter[];
    fetchs: Fetch[];
    loading: boolean;
    rowIndex: number;
    numbers: number[];


    constructor(changeDetectorRef: ChangeDetectorRef, el: ElementRef, componentFactoryResolver: ComponentFactoryResolver, translateService: TranslateService) {
        super(changeDetectorRef ,el, componentFactoryResolver, translateService);
    }

    ngOnInit() {
        if (this.lazy == null || this.lazy == undefined) {
            this.lazy = true;
        }
        if (this.data == null || this.data == undefined) {
            this.data = [];
        }
        if (this.recordsPage == null || this.recordsPage == undefined) {
            this.recordsPage = 50;
        }
        if (this.pagesVisibles == null || this.pagesVisibles == undefined) {
            this.pagesVisibles = 5;
        }
        if (this.hasActions == null || this.hasActions == undefined) {
            this.hasActions = true;
        }
        if (this.renderedShow == null || this.renderedShow == undefined) {
            this.renderedShow = true;
        }
        if (this.renderedEdit == null || this.renderedEdit == undefined) {
            this.renderedEdit = true;
        }
        if (this.renderedDelete == null || this.renderedDelete == undefined) {
            this.renderedDelete = true;
        }
        if (this.renderedSelect == null || this.renderedSelect == undefined) {
            this.renderedSelect = false;
        }
        this.resolveWidthActions();
        this.emptyMessage = this.emptyMessage || "i18nAppCore.i18nComponents.i18n_rf_table_empty_message";
        this.loading = false;
        this.filters = [];
        // If this component is lazy set filters, fetchs and this in the componentLazy
        if (this.lazy) {
            this.filters = this.componentLazy.filters;
            this.fetchs = this.componentLazy.fetchs;
            this.componentLazy.lazyTable = this;
        }
        this.numbers = [];
        for(let i = 0; i < this.recordsPage; i++){
            this.numbers.push(i);
        }
        this.resetTable();
    }

    /**
     * Method to resolve width actions column
     */
    resolveWidthActions() {
        if (this.styleAcctions == null || this.styleAcctions == undefined) {
            let start = 0;
            let columnFactor = 30;
            if (this.renderedShow) {
                start = start + columnFactor;
            }
            if (this.renderedEdit) {
                start = start + columnFactor;
            }
            if (this.renderedDelete) {
                start = start + columnFactor;
            }
            if (this.renderedSelect) {
                start = start + columnFactor;
            }
            if (start == 0) {
                start = 60;
            }
            this.styleAcctions = { "width": "" + start + 'px' }
        }
    }

    ngAfterContentInit() {
        this.resolveTemplates();
    }

    /**
     * Method to resolve templates
     */
    resolveTemplates() {
        this.templates.forEach((item) => {
            switch (item.getType()) {
                case 'caption':
                    this.captionTemplate = item.template;
                    break;

                case 'header':
                    this.headerTemplate = item.template;
                    break;

                case 'body':
                    this.bodyTemplate = item.template;
                    break;
            }
        });
    }

    /**
     * Method to reset the table
     */
    resetTable() {
        this.rowIndex = -1;
        this.orders = [];
        this.limit = new Limit(0, this.recordsPage);
        this.pages = 0;
        this.pagesView = [];
        this.dataView = [];
        this.totalRecords = 0;
        this.page = 0;
        this.selectedValue = null;
        for(let i = 0; i < this.columnsTable.length; i++){
            this.columnsTable[i].sortOrder = 0;
        }
        this.loadData();
        if(this.componentLazy != null && this.componentLazy != undefined){
            this.componentLazy.loadTableConfigDb();
        }
    }

    /**
     * Method to load table
     */
    loadData() {
        if (this.lazy) {
            this.loading = true;
            this.dataView = [];
            this.limit = new Limit(this.page * this.recordsPage, this.recordsPage);
            // First count total recors
            this.componentLazy.countLazy(this.filters).toPromise().then(responseCount => {
                if (responseCount.text() != null && responseCount.status == 200) {
                    const dataCount = JSON.parse(responseCount.text());
                    this.totalRecords = dataCount.data;
                    this.loadPages();
                    // If has totalRecords find the data
                    if (this.totalRecords > 0) {
                        this.componentLazy.findLazy(this.fetchs, this.filters, this.orders, this.limit).toPromise().then(responseFind => {
                            if (responseFind.text() != null && responseFind.status == 200) {
                                const dataFind = JSON.parse(responseFind.text());
                                this.dataView = dataFind.data;
                                this.loading = false;
                            } else {
                                this.loading = false;
                            }
                        }).catch(err => { this.loadPages(); this.dataView = []; this.loading = false; });
                    } else {
                        this.loading = false;
                    }
                } else {
                    this.loading = false;
                }
            }).catch(err => { this.loadPages(); this.dataView = []; this.loading = false; });
        } else {
            this.dataView = [];
            this.totalRecords = this.data.length;
            // Only if total records higher than 0 
            if (this.totalRecords > 0) {
                this.loadPages();
                this.loadDataViewNoLazy();
            } else {
                this.loading = false;
            }
        }
    }

    /**
     * Method to load data view no lazy
     */
    loadDataViewNoLazy() {
        this.loading = true;
        let startSlice = this.page * this.recordsPage;
        let endSlice = this.recordsPage;
        this.totalRecords = this.data.length;
        if (this.totalRecords < this.recordsPage) {
            endSlice = this.totalRecords;
        }
        this.dataView = this.data.slice(startSlice, startSlice + endSlice);
        this.loading = false;
    }

    /**
     * Method to load pages
     */
    loadPages() {
        this.pages = 0;
        this.pagesView = [];
        let totalPages = this.totalRecords / this.recordsPage;
        totalPages = Math.round(totalPages) - 1;
        if (totalPages < 0) {
            totalPages = 0;
        }
        this.pages = totalPages;
        if (this.pages < this.page) {
            this.page = 0;
        }
        this.reloadPagesView();
    }

    /**
     * Method to reload pages
     */
    reloadPagesView() {
        let starEnd = this.calculatePageViews();
        for (let i = starEnd[0]; i <= starEnd[1]; i++) {
            this.pagesView.push(new PageTable(i + 1, i));
        }
    }

    /**
     * Method to calculate pages to show in view
     */
    calculatePageViews() {
        let numberOfPages = this.pages + 1;
        let visiblePages = Math.min(this.pagesVisibles, numberOfPages);

        //calculate range, keep current in middle if necessary
        let start = Math.max(0, Math.ceil((this.page + 1) - ((visiblePages) / 2))),
            end = Math.min(numberOfPages - 1, start + visiblePages - 1);

        //check when approaching to last page
        var delta = this.pagesVisibles - (end - start + 1);
        start = Math.max(0, start - delta);

        return [start, end];
    }

    /**
     * Method to go to page
     * @param pageTable 
     */
    goToPage(pageTable: PageTable) {
        this.loading = true;
        this.page = pageTable.page - 1;
        if (this.page < 0) {
            this.page = 0;
        }
        this.clickRow(null, -1);
        this.loadData();
    }

    /**
     * Method to go next page
     */
    goNextPage() {
        this.loading = true;
        this.page = this.page + 1;
        if (this.page > this.pages) {
            this.page = this.pages;
        }
        this.clickRow(null, -1);
        this.loadData();
    }

    /**
     * Method to go last page
     */
    goLastPage() {
        this.loading = true;
        this.page = this.pages;
        this.clickRow(null, -1);
        this.loadData();
    }

    /**
     * Method to go first page
     */
    goFirstPage() {
        this.loading = true;
        this.page = 0;
        this.clickRow(null, -1);
        this.loadData();
    }

    /**
     * Method to go prev page
     */
    goPrevPage() {
        this.loading = true;
        this.page = this.page + -1;
        if (this.page < 0) {
            this.page = 0;
        }
        this.clickRow(null, -1);
        this.loadData();
    }

    /**
     * Method to sort the table
     */
    sort(column: ColumnTable) {
        this.loading = true;
        this.clickRow(null, -1);
        column.resolveSorOrder();
        if (this.lazy) {
            // If unsorted delete from sort orders
            if (column.sortOrder == 0) {
                let indexDelete = -1;
                for (let i = 0; i < this.orders.length; i++) {
                    if (this.orders[i].campo.trim() === column.field.trim()) {
                        indexDelete = i;
                        break;
                    }
                }
                if (indexDelete != -1) {
                    this.orders.splice(indexDelete, 1);
                }
            } else {
                // Find column 
                let indexColumn = -1;
                for (let i = 0; i < this.orders.length; i++) {
                    if (this.orders[i].campo.trim() === column.field.trim()) {
                        indexColumn = i;
                        break;
                    }
                }
                // If column not exist insert otherwise changue tipo 
                if (indexColumn != -1) {
                    this.orders[indexColumn].tipo = (column.sortOrder === -1 ? 'DESC' : 'ASC');
                } else {
                    this.orders.push(new Order(column.field, column.sortOrder === -1 ? 'DESC' : 'ASC'));
                }
            }
            this.loadData();
        } else {
            // In no lazy sort order not permit unsorted
            let sortOrder = column.sortOrder;
            for (let i = 0; i < this.columnsTable.length; i++) {
                this.columnsTable[i].sortOrder = 0;
            }
            column.sortOrder = sortOrder;
            if (sortOrder == 0) {
                sortOrder = 1;
                column.sortOrder = 1;
            }
            this.data.sort(function (a, b) {
                if (a[column.field] < b[column.field]) {
                    return -1 * sortOrder;
                }
                else if (a[column.field] > b[column.field]) {
                    return 1 * sortOrder;
                }
                else {
                    return 0 * sortOrder;
                }
            });
            this.loadDataViewNoLazy();
        }
    }

    /**
     * Method to reset orders
     */
    resetOrders() {
        this.loading = true;
        this.orders = [];
        for(let i = 0; i < this.columnsTable.length; i++){
            this.columnsTable[i].sortOrder = 0;
        }
        this.loadData();
    }

    /**
     * Method to reset filters 
     */
    resetFilters() {
        this.loading = true;
        this.filters = [];
        this.componentLazy.loadFilters();
        this.loadData();
    }

    /**
     * Method to catch doble click row
     * @param rowData 
     */
    dblClickRow(rowData, rowIndex) {
        this.loading = true;
        // If is lazy doble click row in lazy component 
        if (this.lazy) {
            this.clickRow(rowData, rowIndex);
            this.componentLazy.goActionsLazy('S', rowData, rowIndex, true);
        }
    }

    /**
     * Method to catch  click row
     * @param rowData 
     */
    clickRow(rowData, rowIndex) {
        this.selectedValue = rowData;
        this.rowIndex = rowIndex;
    }

    /**
     * Method to go shwo
     */
    goActions(type, rowData, rowIndex) {
        this.loading = false;
        if (this.rowIndex == -1) {
            this.rowIndex = 0;
        }
        let start = this.page * this.recordsPage;
        if (this.lazy) {
            this.componentLazy.goActionsLazy(type, rowData, start + rowIndex);
        }
    }

    /**
     * Method to refresh table 
     */
    refresh(){
        this.loadData();
    }

    /**
     * Method to apply filters 
     */
    applyFilters(){
        if (this.lazy) {
            this.filters = this.componentLazy.filters;
        }
        this.loadData();
    }

}