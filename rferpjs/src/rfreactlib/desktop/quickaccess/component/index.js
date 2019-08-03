import React from 'react';
import './index.css';
import BaseComponent from '../../../base/baseComponent';
import { RFContext } from '../../../context';

/**
 * Component for generate quickaccess componente for desktop
 */
export default class QuickAccessComponent extends BaseComponent {

    constructor(props) {
        super(props);
        this.handleDblClick = this.handleDblClick.bind(this)
    }

    /**
     * Method to handle dblClick
     * @param {*} event 
     * @param {*} beanAccess 
     */
    handleDblClick(event, beanAccess) {
        //console.log(beanAccess);
        RFContext.desktopAccessResolver.addComponentToBodyDesktop(beanAccess);
    }

    render() {

        let data = [];

        let access = RFContext.desktopAccessResolver.quickaccess;

        let iconClassName = 'QuickAccessComponentIcon';

        let beanAccess = null;

        if (access != null && access != undefined) {

            for (let i = 0; i < access.length; i++) {

                beanAccess = access[i];

                if (beanAccess.iconClassName != null && beanAccess.iconClassName != undefined) {
                    iconClassName = 'QuickAccessComponentIcon ' + beanAccess.iconClassName;
                }

                data.push(
                    <div className="QuickAccessComponent" key={"accessDesktop" + i} onDoubleClick={(event) => this.handleDblClick(event, beanAccess)}>
                        <div className={iconClassName}>
                        </div>
                        <div className="QuickAccessComponentTtile">
                            {RFContext.i18n.__(beanAccess.keyI18nTitle)}
                        </div>
                    </div>
                );

            }
        }

        return (
            data
        );
    }
}