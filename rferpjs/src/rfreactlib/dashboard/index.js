import React from 'react';
import './index.css';
import PropTypes from 'prop-types';
import BaseComponent from '../base/baseComponent';


/**
 * Base class for dashboard
 */
export default class DashboardComponent extends BaseComponent {

    constructor(props) {
        super(props);
    }

    render() {
        return (<div className="DashboardComponent">
            {this.props.header != null && this.props.header != undefined ? this.props.header : null}
            {this.props.menu != null && this.props.menu != undefined ? this.props.menu : null}
        </div>);
    }
}

DashboardComponent.propTypes = {
    menu: PropTypes.object,
    header: PropTypes.object
}