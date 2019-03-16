import { BaseModel } from "./base.model";

/**
 * This class is a base class for configuration table 
 */
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

}