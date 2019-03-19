import { BaseComponent } from "../../base.component";
import { ChangeDetectorRef, ElementRef, ComponentFactoryResolver, Component } from "@angular/core";
import { TranslateService } from "@ngx-translate/core";


@Component({
    selector: 'rf-confirmation',
    templateUrl: './confirmation.component.html',
    styleUrls: ['./confirmation.component.css']
})
export class ConfirmationComponent extends BaseComponent {

    baseComponent;
    messageConfirmation: string;
    acctionYes: string;
    acctionNo: string;

    constructor(changeDetectorRef: ChangeDetectorRef, el: ElementRef, componentFactoryResolver: ComponentFactoryResolver, translateService: TranslateService) {
        super(changeDetectorRef, el, componentFactoryResolver, translateService);
        this.styleClassModal = 'ConfirmationComponentModal';
    }

    /**
     * Accept actión 
     */
    accept() {
        if (this.acctionYes != null && this.acctionYes != undefined) {
            this.baseComponent[this.acctionYes]();
            this.modalComponent.closeModal();
        }
    }

    /**
     * Cancel action
     */
    cancel() {
        if (this.acctionNo == null || this.acctionNo == undefined) {
            this.modalComponent.closeModal();
        } else {
            
        }
    }

}