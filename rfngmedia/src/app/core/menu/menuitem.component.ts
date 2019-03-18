import { Component, ChangeDetectorRef, ElementRef, Input, ComponentFactoryResolver, OnInit } from "@angular/core";
import { TranslateService } from "@ngx-translate/core";
import { BaseComponent } from 'src/rfng/src/public_api';
import { CoreUtils } from 'src/rfng/src/core/utils/core.utils';


@Component({
    selector: 'rf-menuitem',
    templateUrl: './menuitem.component.html',
    styleUrls: ['./menuitem.component.css']
})
export class MenuItemComponent extends BaseComponent implements OnInit {

    @Input()
    routeId?: string;
    @Input()
    menuComponent;
    @Input()
    menuItem?;
    @Input()
    label;
    @Input()
    level?: number;
    @Input()
    idMenu: string;

    levelClass: string;
    active: boolean;
    displayClass: string;

    menuItems: any[];

    constructor(changeDetectorRef: ChangeDetectorRef, el: ElementRef, componentFactoryResolver: ComponentFactoryResolver, translateService: TranslateService) {
        super(changeDetectorRef, el, componentFactoryResolver, translateService);
    }

    ngOnInit() {
        this.level = this.level || 0;
        this.levelClass = "" + this.level;
        this.active = false;
        this.displayClass = 'MenuItemComponentDisplay';
        if (this.level > 0) {
            this.displayClass = 'MenuItemComponentNoDisplay';
        }
        this.menuItems = [];
        if (this.menuItem != null && this.menuItem != undefined) {
            this.menuItem.menuItems.push(this);
        } else {
            this.menuComponent.menuItems.push(this);
        }
    }

    /**
     * Method to click menú item 
     */
    clickMenu(menuItem?, active?, secondPass?) {

        let fire = true;
        let first = false;
        // If is a menu to redirect component and active not action
        if (this.active && this.routeId != null && this.routeId != undefined && this.routeId.trim() !== '') {
            fire = false;
            first = true;
        }


        if (fire) {
            if (menuItem == null || menuItem == undefined) {
                first = true;
                if (active == null || active == undefined) {
                    active = this.active;
                }
                this.menuComponent.changueMenuItems();
                menuItem = this;
            }

            if (!active) {
                menuItem.displayClass = 'MenuItemComponentDisplay';
                for (let i = 0; i < menuItem.menuItems.length; i++) {
                    menuItem.menuItems[i].displayClass = 'MenuItemComponentDisplay';
                }
            }
            if (menuItem.menuItem != null && menuItem.menuItem != undefined && !active && menuItem.level > 0 && secondPass == null && secondPass == undefined) {
                this.clickMenu(menuItem.menuItem, false);
            } else {
                if (secondPass == null && secondPass == undefined && (!active && (menuItem.menuItem != null && menuItem.menuItem != undefined && menuItem.menuItem.level < menuItem.level))) {
                    this.clickMenu(menuItem.menuItem, false, true);
                }
            }
            menuItem.active = !active;
        }


        if (first && this.active && this.routeId != null && this.routeId != undefined && this.routeId.trim() !== '') {
            if (CoreUtils.haveAppConfig() && CoreUtils.APP_CONFIG.tabViewComponent != null && CoreUtils.APP_CONFIG.tabViewComponent != undefined) {
                CoreUtils.APP_CONFIG.tabViewComponent.addTab(this.routeId, this.label);
            }
        }
    }

    /**
     * Method to get icon level
     */
    iconLevel() {
        let iconLevel = "";
        if (this.routeId == null || this.routeId == undefined || this.routeId.trim() === '') {
            iconLevel = "MenuItemComponentIcon" + this.level + (this.active ? " fas fa-caret-down" : " fas fa-caret-right");
            if (this.level == 1) {
                iconLevel = "MenuItemComponentIcon" + this.level + " fas fa-square";
            }
        } else {
            iconLevel = "MenuItemComponentNoIcon" + this.level
        }
        return iconLevel;
    }

}