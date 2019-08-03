import React from 'react';
import Dom from '../dom';
import AccessResolver from '../desktop/resolvers/access';

/**
 * Const for context 
 */
export const RFContext = React.createContext({
    // Set i18n variable 
    i18n: null,
    // Set the dom class to manage operations from document 
    dom: null,
    // Bean for resolve acces for quickacess desktop
    desktopAccessResolver: null,
});