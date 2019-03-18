import { BaseService } from '../base.service';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { RequestHeader } from '../../beans/requestheader';

@Injectable()
export class LoginService extends BaseService {
    constructor(http: HttpClient) {
        super(http, "/token");
    }

    login(loginModel){
        const httpOptions = {
            headers: new HttpHeaders({
                'Content-Type': 'application/json'
            })
        };
        const requestHeader = new RequestHeader(null, null, null, loginModel);
        return this.http.post(this.baseHost + '' + this.urlRequest + '/generateToken', requestHeader, httpOptions);
    }
}