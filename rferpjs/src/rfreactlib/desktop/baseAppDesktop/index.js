import React from 'react';
import './index.css';
import BaseComponent from '../../base/baseComponent';
import BodyDesktopComponent from '../body'
import ToolBarDesktopComponent from '../toolbar'
/**
 * Base class for application desktop
 */
export default class BaseAppDesktop extends BaseComponent {


    /**
     * Method to render body for desktop 
     */
    renderBody() {
        let bodyDesktop = (<BodyDesktopComponent />);
        return bodyDesktop;
    }

     /**
     * Method to render toolbar for desktop 
     */
    renderToolBar(){
        let toolBarDesktop = (<ToolBarDesktopComponent />);
        return toolBarDesktop;
    }
}