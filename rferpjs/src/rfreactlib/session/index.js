
const RF_USER_KEY_SESSION_STORAGE = "rfPrincipal";

/**
 * Class to store data for user session
 */
export default class RFUserSession {
    constructor() {
        this.nick = null;
        this.permisions = {};
        this.modules = {};
    }
}

/**
 * Class to contains idUrl and list roles for permisions to access data
 */
export class RFUserPermision {
    constructor() {
        this.idUrl = null;
        this.roles = [];
    }
}

/**
 * Class to contains modules for user
 */
export class RFUserModule {
    constructor() {
        this.moduleName = null;
    }
}

/**
 * Method to know user is logged in application
 */
export function isUserLogged() {
    let userLogged = getUserLogged();
    return userLogged != null && userLogged != undefined && userLogged.nick != undefined && userLogged.nick != undefined != null && userLogged.nick.trim() !== '';
}

/**
 * Mhetod to get user logged
 */
export function getUserLogged() {
    let userLogged = null;
    let data = sessionStorage.getItem(RF_USER_KEY_SESSION_STORAGE);
    if (data != null && data != undefined) {
        userLogged = Object.assign(new RFUserSession(), JSON.parse(data));
    }
    return userLogged;
}


/**
 * Mhetod to set user logged
 */
export function setUserLogged(userLogged) {
    if (userLogged != null && userLogged != undefined && userLogged.nick != undefined && userLogged.nick != undefined != null && userLogged.nick.trim() !== '') {
        sessionStorage.setItem(RF_USER_KEY_SESSION_STORAGE, JSON.stringify(userLogged));
    } else {
        sessionStorage.setItem(RF_USER_KEY_SESSION_STORAGE, null);
    }
}