import React from 'react';
import './index.css';
import BaseComponent from '../../base/baseComponent';
/**
 * Base class for module for application, in dekstop one module per icon
 */
export default class DesktopModuleComponent extends BaseComponent {

    constructor(props){
        super(props);
        this.baseClassName = "DesktopModuleComponent";
    }

    render(){

        let data = (
            <div className={this.baseClassName}>
                {this.renderContent()}
            </div>
        );
        
        return data;
    }

    renderContent(){
        return null;
    }
}