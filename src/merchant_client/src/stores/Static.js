import {
    observable, action, computed,
} from 'mobx'

export default class Static {

    constructor(root) {
        this.root = root
    }

    @observable ORDER_BASE_URL = "http://223.194.135.152:8081/merchant/qrscan/";
    
}