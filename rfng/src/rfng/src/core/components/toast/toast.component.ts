import { BaseComponent } from "../base.component";
import { Component, ElementRef, ChangeDetectorRef, ComponentFactoryResolver, Input, OnInit } from "@angular/core";
import { TranslateService } from "@ngx-translate/core";
import { ToastItem } from "./toastitem";


@Component({
    selector: 'rf-toast',
    templateUrl: './toast.component.html',
    styleUrls: ['./toast.component.css'],
})
export class ToastComponent extends BaseComponent implements OnInit {

    @Input()
    tabView;
    messages: ToastItem[];

    constructor(changeDetectorRef: ChangeDetectorRef, el: ElementRef, componentFactoryResolver: ComponentFactoryResolver, translateService: TranslateService) {
        super(changeDetectorRef, el, componentFactoryResolver, translateService);
        this.messages = [];
    }

    addMessage(message: string, level: number) {
        let toast = new ToastItem(message, level);
        toast.loadStyleClass();
        toast.index = this.messages.length;
        this.messages.push(toast);
    }

    closeMessage(msg) {
        let index = this.messages.indexOf(msg);
        this.messages.splice(index, 1);
    }

    closeAllMessages(){
        this.messages = [];
    }

    ngOnInit(){
        if(this.tabView != null && this.tabView != undefined){
            this.tabView.toastComponent = this;
        }
    }

}