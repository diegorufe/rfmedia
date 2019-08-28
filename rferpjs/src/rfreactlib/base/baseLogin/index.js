import React from 'react';
import BaseComponent from '../baseComponent';
import { RFContext } from '../../context';
import './index.css';
import RFUserSession, { setUserLogged } from '../../session';
import PropTypes from 'prop-types';

/**
 * Base class for login application
 */
export default class BaseLogin extends BaseComponent {

    constructor(props) {
        super(props);
        this.loginClick = this.loginClick.bind(this);
    }

    async loginClick() {
        let nickValue = document.getElementById("nick").value;
        let passwordValue = document.getElementById("password").value;
        let usserLogedReturn = null;
        if (nickValue != null && nickValue != undefined && passwordValue != null && passwordValue != undefined) {
            if (this.props.app.props.serviceLogin != null && this.props.app.props.serviceLogin != undefined) {
                usserLogedReturn = await this.props.app.props.serviceLogin.login(nickValue, passwordValue);
            }
            if (usserLogedReturn != null && usserLogedReturn != undefined) {
                let userLoggued = new RFUserSession();
                userLoggued.nick = nickValue;
                setUserLogged(userLoggued);
                this.props.app.setState({ changed: true });
            }
            // let userLoggued = new RFUserSession();
            // userLoggued.nick = nickValue;
            // setUserLogged(userLoggued);
            // this.props.app.setState({ changed: true });
        }
    }

    defaultRender() {
        return (
            <div className="Login">
                <div className="LoginPage">
                    <div className="LoginPanel">
                        <input id="nick" type="text" placeholder={RFContext.i18n.__("rf.login.username")}></input>
                        <input id="password" type="password" placeholder={RFContext.i18n.__("rf.login.password")}></input>
                        <button type="button" onClick={this.loginClick}>{RFContext.i18n.__("rf.login.login")}</button>
                    </div>
                </div>
            </div>
        );
    }
}

BaseLogin.propTypes = {
    app: PropTypes.object
}

