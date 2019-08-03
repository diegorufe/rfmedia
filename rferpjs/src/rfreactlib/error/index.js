import BaseComponent from '../base/baseComponent';
import React from 'react';

/**
 * Base error class for components 
 */
export default class ErrorHandlerComponent extends BaseComponent {

    /**
     * Method to get default state props 
     * Added error and errorInfo for show data when has error in a page
     */
    defaultStateProps() {
        let returnData = { error: null, errorInfo: null };
        let propsState = super.defaultStateProps();
        if (propsState !== null && propsState !== undefined) {

            Object.keys(propsState).forEach(function (key) {
                returnData[key] = propsState[key];
            });

        }
        return returnData;
    }

    /**
     * Method to catch error in a component 
     * @param {*} error 
     * @param {*} errorInfo 
     */
    componentDidCatch(error, errorInfo) {
        // Catch errors in any components below and re-render with error message
        this.setState({
            error: error,
            errorInfo: errorInfo
        })
        // You can also log error messages to an error reporting service here
    }

    defaultRender() {
        if (this.state.errorInfo) {
            // Error path
            return (
                <div>
                    <h2>Something went wrong.</h2>
                    <details style={{ whiteSpace: 'pre-wrap' }}>
                        {this.state.error && this.state.error.toString()}
                        <br />
                        {this.state.errorInfo.componentStack}
                    </details>
                </div>
            );
        }
        // Normally, just render children
        return this.props.children;
    }

}