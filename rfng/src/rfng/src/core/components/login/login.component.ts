import { Component, ChangeDetectorRef, ElementRef, ComponentFactoryResolver, Inject, Input, OnInit } from '@angular/core';
import { BaseComponent } from '../base.component';
import { TranslateService } from '@ngx-translate/core';
import { Login } from '../../beans/login';
import { LoginService } from '../../service/login/login.service';


@Component({
    selector: 'rf-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})
export class LoginComponent extends BaseComponent implements OnInit {

    loginModel: Login;

    @Input()
    styleClass:string;

    constructor(changeDetectorRef: ChangeDetectorRef, el: ElementRef, componentFactoryResolver: ComponentFactoryResolver, translateService: TranslateService, @Inject(LoginService) service) {
        super(changeDetectorRef, el, componentFactoryResolver, translateService, service);
    }

    async login() {
        if (this.isAwait) {
            let loginResult = await this.service.login(this.loginModel);
            console.log(loginResult);
        } else {

        }
    }

    ngOnInit(){
        this.loginModel = new Login();
        this.styleClass = this.styleClass || "LoginComponent";
    }
}