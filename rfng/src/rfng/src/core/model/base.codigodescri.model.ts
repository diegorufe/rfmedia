import { BaseModel } from "./base.model";

/**
 * Clase básica código descripción
 */
export abstract class BaseCodigoDescriModel extends BaseModel {

    id:number;
    codigo:string;
    descripcion:string;

    constructor() {
        super();
    }

    ngOnInit() {
        
    }
}