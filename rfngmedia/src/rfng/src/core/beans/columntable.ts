
export class ColumnTable {

    header: string;
    field: string;
    size: number;
    sorttable: boolean;
    styleInColumn;
    configuracionBrowser;
    sortOrder: number;
    cover:boolean;
    id:number;

    constructor(header?: string, field?: string, size?: number, sorttable?: boolean, cover?:boolean) {
        this.header = header;
        this.field = field;
        this.size = size;
        if (sorttable == null || sorttable == undefined) {
            this.sorttable = true;
        } else {
            this.sorttable = sorttable;
        }
        if (cover == null || cover == undefined) {
            this.cover = false;
        } else {
            this.cover = cover;
        }
        this.refresStyle();
        this.sortOrder = 0;
        this.id = null;
    }

    refresStyle() {
        if (this.size != null && this.size != undefined) {
            this.styleInColumn = { 'width': this.size.toString() + '%' };
        }
    }

    /**
     * Mehtod to resolve sort order for a column
     */
    resolveSorOrder() {
        if (this.sortOrder == 0) {
            this.sortOrder = 1;
        } else if (this.sortOrder == 1) {
            this.sortOrder = -1;
        } else {
            this.sortOrder = 0;
        }
    }

}