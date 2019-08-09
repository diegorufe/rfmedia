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
        this.showHideMenu = this.showHideMenu.bind(this);
    }

    /**
     * Method to logout app
     */
    logoutClick() {
        setUserLogged(null);
        this.props.app.setState({ changed: true });
    }

    /**
     * Mhetod to show hide menú
     */
    showHideMenu() {
        let menuComponent = document.getElementsByClassName("MenuComponent");
        if (menuComponent != null && menuComponent != undefined && menuComponent.length > 0) {
            menuComponent = menuComponent[0];
            if (menuComponent.style.display == null || menuComponent.style.display == undefined || menuComponent.style.display === 'block') {
                menuComponent.style.display = 'none';
            } else {
                menuComponent.style.display = 'block';
            }
        }
    }

    defaultRender() {

        let data = (
            <div className="HeaderComponent">
                <div className="HeaderMenu">
                    <button className="btnLogout fas fa-bars" onClick={this.showHideMenu}>
                        <ToolTipComponent value={RFContext.i18n.__("rf.header.menu")} />
                    </button>
                </div>
                <div className="HeaderLogout">
                    <button className="btnLogout fas fa-power-off" onClick={this.logoutClick}>
                        <ToolTipComponent value={RFContext.i18n.__("rf.header.logouttooltip")} />
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