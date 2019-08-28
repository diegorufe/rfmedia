
/**
 * Base class for al services
 */
export default class BaseService {

    constructor(baseHost) {
        this.baseHost = baseHost;
    }

    /**
     * Method to fech post service
     * @param {*} url 
     * @param {*} bodyData 
     */
    async fetchPostService(url, bodyData) {
        let response = await fetch(url,
            {
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                method: 'POST',
                body: bodyData
            }
        );
        const json = await response.json();
        // console.log(json.data);
        // console.log(response.status);
        return json.data;
    }
}

/**
 * Default class for login service
 */
export class BaseLoginService extends BaseService {

    constructor(baseHost, pahtLogin) {
        super(baseHost);
        this.pahtLogin = pahtLogin;
    }

    /**
     * Method to login in app
     * @param {*} nickValue 
     * @param {*} passwordValue 
     */
    async login(nickValue, passwordValue) {
        let logginReturn = null;
        if (this.baseHost != null && this.baseHost != undefined && this.baseHost.trim() !== '' && this.pahtLogin != null && this.pahtLogin != undefined && this.pahtLogin.trim() !== '') {
            logginReturn = await this.fetchPostService(this.baseHost + this.pahtLogin, JSON.stringify({
                "username": nickValue,
                "password": passwordValue
            }));
        }
        return logginReturn;
    }

}