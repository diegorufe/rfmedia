import ModalDesktopComponent from '../../modal';
import React, { Suspense } from 'react';
import ReactDOM from 'react-dom';
/**
 * Base class for realice resolve acces from application 
 */
export default class AccessResolver {

    constructor() {
        // Quick acess for application 
        this.quickaccess = [];
        // Pwds for application
        this.pwds = {};
        // Body id 
        this.bodyId = 'BodyDesktopComponent';
    }

    /**
   * Method to generate unique id
   */
    randomId() {
        return Math.random().toString(36).slice(2);
    }

    /**
     * Method to add component to body desktop 
     */
    addComponentToBodyDesktop(access) {
        let id = this.randomId();
        let divContainer = document.createElement("DIV");
        divContainer.id = id;
        document.getElementById(this.bodyId).appendChild(divContainer);
        ReactDOM.render(<ModalDesktopComponent idModal={id} />, document.getElementById(id));
        // Add id to pwds
        this.pwds[id] = true;
        // Resolve component from modal 
        const ComponentReact = access.urlLazyComponent;
        ReactDOM.render(
            <Suspense fallback={<div>Cargando ...</div>}>
                <ComponentReact />
            </Suspense>
            , document.getElementById(id + "Modal" + "Body"));
    }

    /**
     * Method to remove component from body desktop 
     * @param {*} id is the id for element will be remove
     */
    removeComponentForBodyDesktop(id) {
        delete this.pwds[id];
        document.getElementById(id).remove();
    }

    /**
     * Mhetod to hide component for body desktop
     * @param {*} id 
     */
    hiddenComponentForBodyDesktop(id) {
        document.getElementById(id).style.display = 'none';
        this.pwds[id] = false;
    }

    /**
     * Mhetod to show component for body desktop
     * @param {*} id 
     */
    showComponentForBodyDesktop(id) {
        document.getElementById(id).style.display = 'block';
        this.pwds[id] = true;
    }

}