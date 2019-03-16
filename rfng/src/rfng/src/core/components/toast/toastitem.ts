import { CoreUtils } from "../../utils/core.utils";


export class ToastItem {
    message: string;
    styleClassDiv: string;
    level: number;
    index: number;
    zindex: number;
    style: any;

    constructor(message: string, level: number) {
        this.message = message;
        this.level = level;
        this.zindex = CoreUtils.MODAL_Z_INDEX++;
    }

    loadStyleClass() {
        // Succes
        if (this.level == 0) {
            this.styleClassDiv = 'ToastComponentMessageSucces';
            // Info
        } else if (this.level == 1) {
            this.styleClassDiv = 'ToastComponentMessageInfo';
            // Warn
        } else if (this.level == 2) {
            this.styleClassDiv = 'ToastComponentMessageWarn';
            // error
        } else if (this.level == 3) {
            this.styleClassDiv = 'ToastComponentMessageError';
        }
    }

}