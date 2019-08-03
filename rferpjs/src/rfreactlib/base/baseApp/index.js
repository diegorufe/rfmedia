import BaseComponent from '../baseComponent';
import ErrorHandlerComponent from '../../error';
import './index.css';
import StyleComponent from '../../style';
import BackgroundComponent from '../../background';
import React from 'react';
import { isUserLogged } from '../../session';
import BaseLogin from '../baseLogin/';

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

    render() {
        return isUserLogged() ? this.renderHome() : this.renderLogin();
    }
}
