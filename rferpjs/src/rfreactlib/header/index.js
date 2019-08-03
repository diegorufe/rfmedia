import React from 'react';
import './index.css';
import BaseComponent from '../base/baseComponent';
import PropTypes from 'prop-types';
import { setUserLogged } from '../session';
import ToolTipComponent from '../tooltip';
import { RFContext } from '../context';

/**
 * Base class for menu for modules application
 */
export default class HeaderComponent extends BaseComponent {

    constructor(props) {
        super(props);
        this.logoutClick = this.logoutClick.bind(this);
    }

    /**
     * Method to logout app
     */
    logoutClick() {
        setUserLogged(null);
        this.props.app.setState({ changed: true });
    }

    defaultRender() {

        let data = (
            <div className="HeaderComponent">
                <div className="HeaderLogout">
                    <button className="btnLogout fas fa-power-off" onClick={this.logoutClick}>
                        <ToolTipComponent value={RFContext.i18n.__("rf.header.logouttooltip")}/>
                    </button>
                </div>
            </div>
        );

        return data;
    }

}

HeaderComponent.propTypes = {
    app: PropTypes.object
}