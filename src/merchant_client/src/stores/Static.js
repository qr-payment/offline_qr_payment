import {
    observable, action, computed,
} from 'mobx'

export default class Static {

    constructor(root) {
        this.root = root
    }

    @observable ORDER_BASE_URL = "http://106.10.37.166:8081/order/qrscan/";
    
}