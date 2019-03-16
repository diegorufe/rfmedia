import { Component, ComponentFactoryResolver, Input, ChangeDetectorRef, ElementRef } from "@angular/core";
import { BaseComponent } from "../../base.component";
import { TranslateService } from "@ngx-translate/core";

@Component({
  selector: 'rf-cover',
  templateUrl: './cover.component.html',
  styleUrls: ['./cover.component.css']
})

export class CoverComponent extends BaseComponent {

  @Input()
  model?:any;
  @Input()
  property?: string;
  @Input()
  disabled?: boolean;
  @Input()
  imageData?:any;
  objectUrl;

  constructor(changeDetectorRef: ChangeDetectorRef, el: ElementRef, componentFactoryResolver: ComponentFactoryResolver, translateService: TranslateService) {
    super(changeDetectorRef, el, componentFactoryResolver, translateService);
}

  decode() {
    let dataImgValue = '';
    dataImgValue = this.imageData;
    if (dataImgValue != null) {
      dataImgValue = dataImgValue.trim().replace('---', ' ');
      dataImgValue = dataImgValue.trim().replace('...', ' ');
      dataImgValue = dataImgValue.trim();
    }
    return dataImgValue;
  }


}