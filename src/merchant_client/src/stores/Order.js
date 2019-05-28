import {
    observable, action, computed,
} from 'mobx'

export default class Order {

    constructor(root) {
        this.root = root
    }

    @observable productList = [];
    @observable countedProductList = [];
    @observable orderList = [];
    @observable amount = 0;
    @observable totalCount = 0;
    @observable discountAmount = 0;
    @observable resetDialog = false;
    @observable qrCodeModal = false;

    @action getDummyProductList = () => {

        this.productList = this.root.dummy.dummyProductList;
        this.countedProductList = this.productList.map(function (obj) { obj.count = 0; return obj; });

    }

    @action resetOrderList = () => {

        this.countedProductList = this.productList.map(function (obj) { obj.count = 0; return obj; });
        this.orderList = [];
        this.amount = 0;
        this.totalCount = 0;
        this.discountAmount = 0;

    }

    @action addOrderList = (idx) => {

        const product = this.countedProductList.find(product => product.idx === idx);

        if (product.count == 0) {
            this.orderList.push(product);
        }

        product.count++;

        this.amount += product.price;
        this.totalCount ++;

    }

    @action removeOrderList = (idx) => {

        const product = this.countedProductList.find(product => product.idx === idx);

        if (product.count == 1) {
            const index = this.orderList.indexOf(product);
            if (index > -1) {
                this.orderList.splice(index, 1);
            }
        }

        if (product.count > 0) {
            product.count--;
        }

        this.amount -= product.price;
        this.totalCount--;

    }

    @action removeProduct = (idx) => {

        const product = this.countedProductList.find(product => product.idx === idx);

        this.amount -= product.price * product.count;
        this.totalCount -= product.count;

        const index = this.orderList.indexOf(product);
        if (index > -1) {
            this.orderList.splice(index, 1);
        }

        if (product.count > 0) {
            product.count = 0;
        }

    }

    @action showResetDialog = () => {
        this.resetDialog = true;
    }

    @action hideResetDialog = () => {
        this.resetDialog = false;
    }

    @action showQRCodeModal = () => {
        this.qrCodeModal = true;
    }

    @action hideQRCodeModal = () => {
        this.qrCodeModal = false;
    }

    @action getOrderURL = () => {

        if(this.orderList.length !== 0) {

            const orderName = this.orderList[0].name + ' 외 ' + (this.totalCount - 1) + '건'
            const encodedOrderName = new Buffer(orderName).toString('base64');
            const amount = this.amount;

            const url = this.root.static.ORDER_BASE_URL + '?orderName=' + encodedOrderName + '&amount=' + amount + '&count=' + this.totalCount;

        return url;

        }
        
    }
}