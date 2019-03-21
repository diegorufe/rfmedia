import { BaseModel } from './base.model';

/**
 * Class for save user principal app
 */
export class Principal extends BaseModel {

    token: String;
    user: String;

    constructor() {
        super();
    }
}