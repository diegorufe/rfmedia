import React from 'react';
/**
 * Const for context 
 */
export const RFContext = React.createContext({
    // Set i18n variable 
    i18n: null,
    // Set the dom class to manage operations from document 
    dom: null,
    // Bean for resolve access
    access: null,
});