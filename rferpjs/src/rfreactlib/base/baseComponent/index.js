import React, { Component } from 'react';

/**
 * Base class for components 
 */
export default class BaseComponent extends Component {

    constructor(props){
        super(props);
        this.state = this.defaultStateProps();
    }

    /**
     * Method to get default State props
     */
    defaultStateProps(){
        return {};
    }

    render() {
        return (

            <span>
            </span>

        );
    }
}