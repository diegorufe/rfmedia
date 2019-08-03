import React, { Component } from 'react';

/**
 * Base class for components 
 */
export default class BaseComponent extends Component {

    constructor(props) {
        super(props);
        this.state = this.defaultStateProps();
    }

    /**
     * Method to get default State props
     */
    defaultStateProps() {
        return {};
    }

    defaultRender() {
        return (

            <span>
            </span>

        );
    }

    hideToolTip(){
        let toolTipContainer = document.getElementById("tooltipcontainer");

        if(toolTipContainer != null && toolTipContainer != undefined){
            toolTipContainer.innerText = '';
            toolTipContainer.style.display = 'none';
        }
    }

    render() {
        // Hide tooltip

        this.hideToolTip();

        return this.defaultRender();
    }
}