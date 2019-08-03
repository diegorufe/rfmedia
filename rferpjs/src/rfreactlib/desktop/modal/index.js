import React from 'react';
import './index.css';
import BaseComponent from '../../base/baseComponent';
import { RFContext } from '../../context';
import PropTypes from 'prop-types';

/**
 * Base class for modal desktop
 */
export default class ModalDesktopComponent extends BaseComponent {

    constructor(props) {
        super(props);
        this.extraIdModal = "Modal";
        this.extraIdModalBody = "Body";
    }

    /**
     * Method to close modal and remove from body 
     * @param {*} id 
     */
    closeModal(id) {
        RFContext.desktopAccessResolver.removeComponentForBodyDesktop(id);
    }

    /**
     * Method to minimeze modal 
     * @param {*} id 
     */
    minimezeModal(id){
        RFContext.desktopAccessResolver.hiddenComponentForBodyDesktop(id);
    }

    render() {
        let zIndex = RFContext.dom.getUpdatedZIndexStr();
        let data = (
            <div className="ModalDesktopComponent" id={this.props.idModal + this.extraIdModal} style={{ "zIndex": zIndex }}>
                <div className="ModalDesktopComponentHeader">
                    <div className="ModalDesktopComponentHeaderButtons">
                        <button className="ModalDesktopComponentHeaderButtonsMin" type="button" onClick={() => this.minimezeModal(this.props.idModal)}>
                            <i className="fas fa-window-minimize"></i>
                        </button>
                        <button className="ModalDesktopComponentHeaderButtonsClose" type="button" onClick={() => this.closeModal(this.props.idModal)}>
                            <i className="fas fa-times"></i>
                        </button>
                    </div>
                </div>
                <div className="ModalDesktopComponentBody" id={this.props.idModal + this.extraIdModal+ this.extraIdModalBody}>

                </div>
            </div>
        );

        return data;
    }
}

ModalDesktopComponent.propTypes = {
    idModal: PropTypes.string
}