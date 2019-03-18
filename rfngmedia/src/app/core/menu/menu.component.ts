import { Component, ChangeDetectorRef, ElementRef, Input, ComponentFactoryResolver, OnInit } from "@angular/core";
import { TranslateService } from "@ngx-translate/core";
import { BaseComponent } from 'src/rfng/src/public_api';


@Component({
    selector: 'rf-menu',
    templateUrl: './menu.component.html',
    styleUrls: ['./menu.component.css']
})
export class MenuComponent extends BaseComponent implements OnInit {

    @Input()
    dashBoardComponent;

    menuItems: any[];

    open:boolean;

    constructor(changeDetectorRef: ChangeDetectorRef, el: ElementRef, componentFactoryResolver: ComponentFactoryResolver, translateService: TranslateService) {
        super(changeDetectorRef, el, componentFactoryResolver, translateService);
    }

    ngOnInit(){
        this.menuItems = [];
        this.open = true;
    }

    /**
     * Method to chage all items not root
     */
    changueMenuItems(menuItem) {
        let menuItemsFor = null;
        if (menuItem == null || menuItem == undefined) {
            menuItemsFor = this.menuItems;
        } else {
            menuItemsFor = menuItem.menuItems;
        }
        for (let i = 0; i < menuItemsFor.length; i++) {
            if(menuItemsFor[i].level > 0){
                menuItemsFor[i].displayClass = 'MenuItemComponentNoDisplay';
            }
            menuItemsFor[i].active = false;
            this.changueMenuItems(menuItemsFor[i])
        }
    }



}