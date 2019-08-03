import React from 'react';
import './index.css';
import BaseComponent from '../base/baseComponent';
import PropTypes from 'prop-types';

/**
 * Class for load background for application
 */
export class BackgroundComponent extends BaseComponent {

    constructor(props) {
        super(props)
        this.state = {
            loading: true,
            fallbackIndex: 0
        };
        this.onLoad = this.onLoad.bind(this);
        this.onError = this.onError.bind(this);
        this.fetchImage = this.fetchImage.bind(this);
    }

    componentDidMount() {
        const { src } = this.props;
        this.fetchImage(src);
    }

    componentWillUnmount() {
        this.clearEvents();
    }

    onLoad() {
        this.clearEvents();
        this.setState({ loading: false, failed: false });
    }

    clearEvents() {
        if (this.image) {
            this.image.removeEventListener('load', this.onLoad);
            this.image.removeEventListener('error', this.onError);
        }
    }

    onError() {
        const { fallbackImages } = this.props;
        const { fallbackIndex } = this.state;
        if (fallbackImages && fallbackIndex < fallbackImages.length) {
            this.fetchImage(fallbackImages[fallbackIndex]);
            this.setState({ fallbackIndex: fallbackIndex + 1 });
        } else {
            this.clearEvents();
            this.setState({ loading: false, failed: true });
        }
    }

    fetchImage(src) {

        if (src === null || src === undefined || this.image.src.trim() === '') {
            this.image = null;
            this.setState({ loading: false, failed: false })
        } else {
            this.image = new Image();
            this.image.addEventListener('load', this.onLoad);
            this.image.addEventListener('error', this.onError);
            this.image.src = src;
        }

    }

    render() {
        const { className, loader } = this.props;
        const { loading } = this.state;
        if (loading) {
            return loader ? <span>{loader}</span> : null;
        } else {
            if (this.image === null || this.image === undefined || this.image.src === null || this.image.src === undefined || this.image.src.trim() === '') {
                return <div className={className || 'BackgroundComponent'}></div>;
            } else {
                return <img src={this.image.src} className={className || 'BackgroundComponent'} />;
            }
        }
    }
}

BackgroundComponent.propTypes = {
    fallbackImages: PropTypes.array,
    loader: PropTypes.object,
    className: PropTypes.string,
    src: PropTypes.string
}

export default BackgroundComponent;