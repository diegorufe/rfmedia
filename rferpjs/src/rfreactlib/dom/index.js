
/**
 * Default class for dom operations 
 */
export default class Dom {

    constructor() {
        this.zIndex = 10;
    }

    /**
     * Method to get zindex and increment one
     */
    getUpdatedZIndex() {
        return this.zIndex++;
    }

    /**
     * Method to get zindex and increment one an cast to str
     */
    getUpdatedZIndexStr() {
        return '' + this.getUpdatedZIndex();
    }

    /**
    * Method to generate unique id
    */
    randomId() {
        return Math.random().toString(36).slice(2);
    }
}