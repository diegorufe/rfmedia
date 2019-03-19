import { Filter } from "./filter";
import { Order } from "./order";
import { Limit } from "./limit";
import { Fetch } from "./fetch";
import { BaseModel } from "../model/base.model";

/** 
 * Clase encargada de enviarse en cada petición de la cabecera 
 * mediante el http
*/
export class RequestHeader {

    fetchs: Fetch[];
    filters: Filter[];
    orders: Order[];
    data: BaseModel;
    limit: Limit;

    constructor(fetchs?: Fetch[], filters?: Filter[], orders?: Order[], data?: BaseModel, limit?: Limit) {
        this.fetchs = fetchs;
        this.filters = filters;
        this.orders = orders;
        this.data = data;
        this.limit = limit;
    }
}