import { ChangeDetectorRef, ElementRef, Input, ComponentFactoryResolver, Component } from "@angular/core";
import { BaseComponent } from "../base.component";
import { TranslateService } from "@ngx-translate/core";


@Component({
    selector: 'rf-red',
    templateUrl: './red.component.html',
    styleUrls: ['./red.component.css']
})
export class RedComponent extends BaseComponent {

    @Input()
    baseRedComponent;


    constructor(changeDetectorRef: ChangeDetectorRef, el: ElementRef, componentFactoryResolver: ComponentFactoryResolver, translateService: TranslateService) {
        super(changeDetectorRef, el, componentFactoryResolver, translateService);
    }


}