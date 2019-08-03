import React from 'react';
import './index.css';
import BaseComponent from '../base/baseComponent';
import PropTypes from 'prop-types';
/**
 * Base class for menu for modules application
 */
export default class MenuComponent extends BaseComponent {

    constructor(props){
        super(props);
    }

    defaultRender(){

        let data = (
            <div className="MenuComponent">
            </div>
        );
        
        return data;
    }

}

MenuComponent.propTypes = {
    app: PropTypes.object
}