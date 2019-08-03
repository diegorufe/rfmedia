import React from 'react';
import './index.css';
import BaseComponent from '../../base/baseComponent';
import QuickAccessComponent from '../quickaccess/component'
/**
 * Base class for application desktop
 */
export default class BodyDesktopComponent extends BaseComponent {

    constructor(props){
        super(props);
    }

    render(){

        let data = (
            <div className="BodyDesktopComponent" id="BodyDesktopComponent">
                <QuickAccessComponent />
            </div>
        );
        
        return data;
    }
}