import './index.css';
import BaseComponent from '../base/baseComponent';
import PropTypes from 'prop-types';
import ReactDOM from 'react-dom';
import React from 'react';
/**
 * Base class for tooltip
 */
export default class ToolTipComponent extends BaseComponent {

    constructor(props) {
        super(props);
    }

    componentDidMount() {
        let idParent = null;
        let findById = true;
        let parent = null;

        if (this.props.for != null && this.props.for != undefined) {
            idParent = this.props.for;
        } else {
            let parentNode = ReactDOM.findDOMNode(this);

            if (parentNode != null && parentNode != undefined) {
                try {
                    findById = false;
                    parent = parentNode.parentNode;
                    idParent = true;
                } catch (ex) {

                }
            }
        }

        if (idParent != null && idParent != undefined) {

            if (findById) {
                parent = document.getElementById(idParent);
            }

            let value = this.props.value;

            if (parent != null && parent != undefined) {

                parent.onmouseover = function (event) {
                    let toolTipContainer = document.getElementById("tooltipcontainer");
                    if (toolTipContainer == null || toolTipContainer == undefined) {
                        toolTipContainer = document.createElement("DIV");
                        toolTipContainer.classList.add('ToolTipComponentContainer');
                        toolTipContainer.style.display = 'none';
                        toolTipContainer.id = "tooltipcontainer";
                        document.body.appendChild(toolTipContainer);
                    }

                    toolTipContainer.innerText = value;

                    toolTipContainer.style.left = parent.offsetLeft + "px";
                    toolTipContainer.style.top = (parent.offsetTop + parent.offsetHeight + 5) + "px";
                    toolTipContainer.style.display = 'block';

                    if(toolTipContainer.offsetLeft + toolTipContainer.offsetWidth > document.body.offsetWidth){
                        toolTipContainer.style.left = (document.body.offsetWidth - toolTipContainer.offsetWidth)+"px";
                    }


                };

                parent.onmouseout = function (event) {
                    let toolTipContainer = document.getElementById("tooltipcontainer");
                    if (toolTipContainer != null && toolTipContainer != undefined) {
                        toolTipContainer.innerText = '';
                        toolTipContainer.style.display = 'none';
                    }
                };
            }
        }
    }

    defaultRender() {
        return <span className="ToolTipComponent"></span>;
    }

}

ToolTipComponent.propTypes = {
    for: PropTypes.string,
    value: PropTypes.string
}