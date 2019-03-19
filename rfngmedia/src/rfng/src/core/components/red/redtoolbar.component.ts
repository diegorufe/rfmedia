import { Component, Input, ChangeDetectorRef, ElementRef, ComponentFactoryResolver } from "@angular/core";
import { BaseComponent } from "../base.component";
import { TranslateService } from "@ngx-translate/core";


@Component({
    selector: 'rf-red-toolbar',
    templateUrl: './redtoolbar.component.html',
    styleUrls: ['./redtoolbar.component.css']
})
export class RedToolBarComponent extends BaseComponent {

    @Input()
    baseRedComponent;


    constructor(changeDetectorRef: ChangeDetectorRef, el: ElementRef, componentFactoryResolver: ComponentFactoryResolver, translateService: TranslateService) {
        super(changeDetectorRef, el, componentFactoryResolver, translateService);
    }


}