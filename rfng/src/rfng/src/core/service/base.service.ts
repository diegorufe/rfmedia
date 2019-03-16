import { RequestHeader } from "../beans/requestheader";
import { BaseModel } from "../model/base.model";
import { HttpClient, HttpHeaders } from '@angular/common/http';
/**
 * This class is base class from service
 */
export abstract class BaseService {
    http: HttpClient;
    baseHost: String = 'http://localhost:8888';
    urlRequest: String;
    /**
     * 
     * @param http module http to request
     */
    constructor(http: HttpClient, urlRequest?: String) {
        this.http = http;
        this.urlRequest = urlRequest;
    }

    /**
     * Method to count records
     * @param filters filters to apply
     */
    count(filters?) {
        const httpOptions = {
            headers: new HttpHeaders({
                'Content-Type': 'application/json'
            })
        };
        const requestHeader = new RequestHeader(null, filters);
        return this.http.post(this.baseHost + '' + this.urlRequest + '/count', requestHeader, httpOptions);
    }

    /**
     * Method to find
     * @param fetchs 
     * @param filters 
     * @param orders 
     * @param limit 
     */
    find(fetchs?, filters?, orders?, limit?) {
        const httpOptions = {
            headers: new HttpHeaders({
                'Content-Type': 'application/json'
            })
        };
        const requestHeader = new RequestHeader(fetchs, filters, orders, null, limit);
        return this.http.post(this.baseHost + '' + this.urlRequest + '/find', requestHeader, httpOptions);
    }

    /**
     * Method to update
     * @param data 
     */
    update(data: BaseModel) {
        const httpOptions = {
            headers: new HttpHeaders({
                'Content-Type': 'application/json'
            })
        };
        const requestHeader = new RequestHeader(null, null, null, data, null);
        return this.http.post(this.baseHost + '' + this.urlRequest + '/update', requestHeader, httpOptions);
    }

    /**
     * Method to insert
     * @param data 
     */
    insert(data: BaseModel) {
        const httpOptions = {
            headers: new HttpHeaders({
                'Content-Type': 'application/json'
            })
        };
        const requestHeader = new RequestHeader(null, null, null, data, null);
        return this.http.post(this.baseHost + '' + this.urlRequest + '/insert', requestHeader, httpOptions);
    }

    /**
     * Method to delete
     * @param data 
     */
    delete(data: BaseModel) {
        const httpOptions = {
            headers: new HttpHeaders({
                'Content-Type': 'application/json'
            })
        };
        const requestHeader = new RequestHeader(null, null, null, data, null);
        return this.http.post(this.baseHost + '' + this.urlRequest + '/delete', requestHeader, httpOptions);
    }
}