import React from 'react';
import './index.css';
import BaseComponent from '../base/baseComponent';
/**
 * Base class for menu for modules application
 */
export default class MenuComponent extends BaseComponent {

    constructor(props){
        super(props);
    }

    render(){

        let data = (
            <div className="MenuComponent">
            </div>
        );
        
        return data;
    }

}