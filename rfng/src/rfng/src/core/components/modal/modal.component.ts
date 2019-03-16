import { ChangeDetectorRef, ElementRef, ComponentFactoryResolver, ViewChild, ViewContainerRef, Component } from "@angular/core";
import { TranslateService } from "@ngx-translate/core";
import { BaseComponent } from "../base.component";



@Component({
    selector: 'rf-modal',
    templateUrl: './modal.component.html',
    styleUrls: ['./modal.component.css']
})
export class ModalComponent extends BaseComponent {

    @ViewChild('modalViewBody', { read: ViewContainerRef })
    modalViewBody: ViewContainerRef;

    label: string;

    constructor(changeDetectorRef: ChangeDetectorRef, el: ElementRef, componentFactoryResolver: ComponentFactoryResolver, translateService: TranslateService) {
        super(changeDetectorRef, el, componentFactoryResolver, translateService);
        this.getUpdatedZIndex();
    }

    /**
     * Mhetod to get style class modal for base component
     */
    getStyleClassBaseComponent() {
        let styleClass: string = '';
        if (this.baseComponent != null && this.baseComponent != undefined) {
            styleClass = this.baseComponent.styleClassModal;
        }
        return styleClass;
    }

    /**
     * Method to close modal
     */
    closeModal() {
        this.setLoadingTab(true);
        if (this.tabView != null && this.tabView != undefined) {
            this.tabView.tabViewBody.tabViewModalsContent.remove(this.index);
            this.tabView.tabViewBody.changeDetectorRef.markForCheck();
            this.tabView.tabViewBody.changeDetectorRef.detectChanges();
        }
        this.setLoadingTab(false);
    }

}