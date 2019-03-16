import { ChangeDetectorRef, ElementRef, Input, ComponentFactoryResolver, Component } from "@angular/core";
import { BaseComponent } from "../base.component";
import { TranslateService } from "@ngx-translate/core";


@Component({
    selector: 'rf-browse',
    templateUrl: './browse.component.html',
    styleUrls: ['./browse.component.css']
})
export class BrowserComponent extends BaseComponent {

    @Input()
    baseBrowserComponent;

    constructor(changeDetectorRef: ChangeDetectorRef, el: ElementRef, componentFactoryResolver: ComponentFactoryResolver, translateService: TranslateService) {
        super(changeDetectorRef, el, componentFactoryResolver, translateService);
    }

}