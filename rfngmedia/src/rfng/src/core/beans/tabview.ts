
/**
 * This class is a class to save value for tabs
 */
export class TabView{

    active:boolean;
    tabViewHeader;
    tabViewBody;
    label:string;
    index:number;
    toastComponent;
    tabViewComponent;
    
    constructor(){
        this.active = false;
        this.index = 0;
    }

}