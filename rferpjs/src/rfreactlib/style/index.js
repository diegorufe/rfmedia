import React from 'react';
import './index.css';
import BaseComponent from '../base/baseComponent';

/**
 * Class for load style for application
 */
export default class StyleComponent extends BaseComponent {
    defaultRender(){
        return(<span className="styleSpan"></span>)
    }
}