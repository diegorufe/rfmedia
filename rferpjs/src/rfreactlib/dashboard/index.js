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

    defaultRender() {
        return (
            <div className="DashboardComponent" id="Dashboard">
                {this.props.header != null && this.props.header != undefined ? this.props.header : null}
                {this.props.menu != null && this.props.menu != undefined ? this.props.menu : null}
                {this.props.body != null && this.props.body != undefined ? this.props.body : null}
            </div>
        );
    }
}

DashboardComponent.propTypes = {
    menu: PropTypes.object,
    header: PropTypes.object,
    body: PropTypes.object,
    app: PropTypes.object
}