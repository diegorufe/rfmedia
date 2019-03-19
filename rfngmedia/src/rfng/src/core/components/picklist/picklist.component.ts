import { BaseComponent } from "../base.component";
import { Component, ChangeDetectorRef, ElementRef, Input, ComponentFactoryResolver, OnInit } from "@angular/core";
import { TranslateService } from "@ngx-translate/core";


@Component({
    selector: 'rf-picklist',
    templateUrl: './picklist.component.html',
    styleUrls: ['./picklist.component.css']
})
export class PickListComponent extends BaseComponent implements OnInit {

    @Input()
    basePickComponent;

    @Input()
    propertyLoad;

    @Input()
    source: any[];

    @Input()
    propertySource: string;

    @Input()
    target: any[];

    @Input()
    propertyTarget: string;

    selectSource: any;

    selectTarget: any;

    @Input()
    propertyIndex?: string;

    @Input()
    i18nColumn?: boolean;

    constructor(changeDetectorRef: ChangeDetectorRef, el: ElementRef, componentFactoryResolver: ComponentFactoryResolver, translateService: TranslateService) {
        super(changeDetectorRef, el, componentFactoryResolver, translateService);
        this.selectSource = null;
        this.selectTarget = null;
        
    }

    ngOnInit(){
        this.i18nColumn = this.i18nColumn || true;
        this.resolveSource();
    }

    /**
     * Event produces dlb click on item source
     * @param index 
     */
    onRowDblClickSource(index) {
        this.basePickComponent[this.propertyLoad] = false;
        this.selectSource = this.source[index];
        this.moveToTarget();
        this.source.splice(index,1);
        this.changeDetectorRef.detectChanges();
        this.basePickComponent[this.propertyLoad] = true;
        this.basePickComponent.changeDetectorRef.detectChanges();
    }

    /**
     * Event produces dlb click on item target
     * @param sourceTarget 
     */
    onRowDblClickTarget(index) {
        this.basePickComponent[this.propertyLoad] = false;
        this.selectTarget = this.target[index];
        this.moveToSource();
        this.target.splice(index,1);
        this.changeDetectorRef.detectChanges();
        this.basePickComponent[this.propertyLoad] = true;
        this.basePickComponent.changeDetectorRef.detectChanges();
    }

    /**
     * Method to move to target
     */
    moveToTarget() {
        if (this.selectSource != null && this.selectSource != undefined) {
            this.target.push(this.selectSource)
        }
        this.selectSource = null;
    }

    /**
     * Method to move to target
     */
    moveToSource() {
        if (this.selectTarget != null && this.selectTarget != undefined) {
            this.source.push(this.selectTarget)
        }
        this.selectTarget = null;
    }


    resolveSource() {
        let sourceCopy = Object.assign([], this.source);
        let rest = 0;
        for (let z = 0; z < sourceCopy.length; z++) {
            for (let i = 0; i < this.target.length; i++) {
                if (this.target[i][this.propertySource] == sourceCopy[z][this.propertySource]) {
                   this.source.splice(z-rest,1);
                   rest = rest + 1;
                   break;
                }
            }
        }
    }


}