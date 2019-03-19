import { Component, ChangeDetectorRef, ElementRef, Input, OnInit, ComponentFactoryResolver } from "@angular/core";
import { BaseComponent } from "../base.component";
import { TranslateService } from "@ngx-translate/core";


@Component({
    selector: 'rf-file',
    templateUrl: './file.component.html',
    styleUrls: ['./file.component.css'],
})
export class FileComponent extends BaseComponent implements OnInit {

    @Input()
    model;

    @Input()
    property: string;

    @Input()
    label?: string;

    @Input()
    accept?: string;

    @Input()
    disabled?: boolean;

    constructor(changeDetectorRef: ChangeDetectorRef, el: ElementRef, componentFactoryResolver: ComponentFactoryResolver, translateService: TranslateService) {
        super(changeDetectorRef, el, componentFactoryResolver, translateService);
    }

    ngOnInit() {
        if (this.accept == null || this.accept == undefined) {
            this.accept = "*";
        }
        if (this.disabled == null || this.disabled == undefined) {
            this.disabled = false;
        }
    }

    onChange(event: EventTarget) {
        
        if (!this.disabled) {
            let eventObj: MSInputMethodContext = <MSInputMethodContext>event;
            let target: HTMLInputElement = <HTMLInputElement>eventObj.target;
            let files: FileList = target.files;
            let file = files[0];
            this.readThis(file);
        }
    }

    readThis(file): void {
        var myReader: FileReader = new FileReader();

        myReader.onloadend = (e) => {
            //this.model[this.modelAtr.toString()] = this.base64ToArrayBuffer(myReader.result);
            this.model[this.property.toString()] = myReader.result;
        }

        myReader.readAsDataURL(file);

    }

}