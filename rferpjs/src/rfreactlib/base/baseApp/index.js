import BaseComponent from '../baseComponent';
import ErrorHandlerComponent from '../../error';
import './index.css';
import StyleComponent from '../../style';
import BackgroundComponent from '../../background';
import React from 'react';
import { isUserLogged } from '../../session';
import BaseLogin from '../baseLogin/';
import HeaderComponent from '../../header';
import MenuComponent from '../../menu';
import DashboardComponent from '../../dashboard';
import BodyComponent from '../../body';
import PropTypes from 'prop-types';

/**
 * Base class for application
 */
export default class BaseApp extends BaseComponent {

    renderHome() {
        return (
            <ErrorHandlerComponent>
                <div className="BaseApp">
                    <BackgroundComponent />
                    <StyleComponent />
                    <DashboardComponent app={this} menu={<MenuComponent app={this} />} header={<HeaderComponent app={this} />} body={<BodyComponent app={this} />} />
                </div>
            </ErrorHandlerComponent>
        );
    }

    defaultStateProps() {
        return {
            changed: false
        }
    }

    renderLogin() {
        return (
            <BaseLogin app={this} />
        );
    }

    defaultRender() {
        return isUserLogged() ? this.renderHome() : this.renderLogin();
    }
}

BaseApp.propTypes = {
    serviceLogin: PropTypes.object
}

