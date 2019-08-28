import { BaseLoginService } from '../../../../rfreactlib/base/baseService/index';
import AppConstans from '../../constants';

/**
 * Login service for application
 */
export default class AppLoginService extends BaseLoginService {
    constructor() {
        super(AppConstans.BASE_HOST, AppConstans.LOGIN_URL);
    }
}