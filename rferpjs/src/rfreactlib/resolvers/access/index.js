import React, { Suspense } from 'react';
import ReactDOM from 'react-dom';
/**
 * Base class for realice resolve acces from application 
 */
export default class AccessResolver {

    constructor() {
        // Quick acess for application 
        this.permisionsAll = [];
    }

    /**
     * Method to generate unique id
     */
    randomId() {
        return Math.random().toString(36).slice(2);
    }

}