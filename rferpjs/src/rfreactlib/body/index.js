import React from 'react';
import './index.css';
import BaseComponent from '../base/baseComponent';
import PropTypes from 'prop-types';

/**
 * Base class for body for modules application
 */
export default class BodyComponent extends BaseComponent {

    constructor(props){
        super(props);
        
    }

    defaultRender(){

        let data = (
            <div className="BodyComponent">
                
            </div>
        );
        
        return data;
    }

}

BodyComponent.propTypes = {
    app: PropTypes.object
}