import { Component, ChangeDetectorRef, ElementRef, Input, ComponentFactoryResolver } from "@angular/core";
import { BaseComponent } from "../base.component";
import { TranslateService } from "@ngx-translate/core";



@Component({
    selector: 'rf-card',
    templateUrl: './card.component.html',
    styleUrls: ['./card.component.css'],
})
export class CardComponent extends BaseComponent {

    classCardHeader:string;
    classCardBody:string;

    @Input()
    label: string;

    constructor(changeDetectorRef: ChangeDetectorRef, el: ElementRef, componentFactoryResolver: ComponentFactoryResolver, translateService: TranslateService) {
        super(changeDetectorRef, el, componentFactoryResolver, translateService);
        this.classCardHeader = 'fas fa-caret-up';
        this.classCardBody = 'CardComponentBodyExpand';
    }

    toggleCard() {
        if(this.classCardBody.includes('CardComponentBodyExpand')){
            this.classCardBody = 'CardComponentBodyCollapse';
            this.classCardHeader = 'fas fa-caret-down';
        }else{
            this.classCardHeader = 'fas fa-caret-up';
            this.classCardBody = 'CardComponentBodyExpand';
        }
    }
}