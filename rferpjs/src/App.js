import './App.css';
import BaseApp from './rfreactlib/base/baseApp';
import locales from "./app/i18n/locales";
import loaderconfig from './rfreactlib/loaderconfig';

// Load configuration for application
loaderconfig([locales], null);

export default class App extends BaseApp {
    
}
