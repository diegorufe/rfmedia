import React from 'react';
import './index.css';
import BaseComponent from '../../base/baseComponent';
import {RFContext} from '../../context';
/**
 * Base class for application desktop
 */
export default class ToolBarDesktopComponent extends BaseComponent {

    render(){

        let data = (
            <div className="ToolBarDesktopComponent">
                <div className="ToolBarDesktopComponentDivShape">
                    <div className="ToolBarDesktopComponentStart">
                        <button type="button"  className="ToolBarDesktopComponentStartButton rfFontFamily"><i className="fas fa-bars"></i>{RFContext.i18n.__("desktop.start")}</button>
                    </div>
                    <div className="ToolBarDesktopComponentAccess">
                        
                    </div>
                </div>
            </div>
        );
        
        return data;
    }
}