import { BaseModel } from "../../../public_api";

export class ConfiguracionBrowser extends BaseModel {

    id: number;
    sortTable: boolean;
    columna: string;
    tabla: string;
    size: number;
    orden: number;

    constructor() {
        super();
    }

    ngOnInit() {

    }
}