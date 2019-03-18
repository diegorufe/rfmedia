import { Component, ElementRef, ChangeDetectorRef, Input, ComponentFactoryResolver } from "@angular/core";
import { TranslateService } from "@ngx-translate/core";
import { BaseComponent } from 'src/rfng/src/public_api';


@Component({
    selector: 'rf-header',
    templateUrl: './header.component.html',
    styleUrls: ['./header.component.css']
})
export class HeaderComponent extends BaseComponent {

    @Input()
    dashBoardComponent;

    constructor(changeDetectorRef: ChangeDetectorRef, el: ElementRef, componentFactoryResolver: ComponentFactoryResolver, translateService: TranslateService) {
        super(changeDetectorRef, el, componentFactoryResolver, translateService);
    }


}