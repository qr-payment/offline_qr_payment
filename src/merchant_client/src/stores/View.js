import {
    observable, action, computed,
} from 'mobx'

export default class View {
    views = {
        order: "결제 페이지",
        orderHistory: "결제 목록 페이지"
    }

    constructor(root) {
        this.root = root
    }

    @observable isLoading = false;
    @observable currentPage = this.views.order;
    @observable sideMenu = false;

    @action showOrderHistoryPage = () => {
        this.currentPage = this.views.orderHistory;
    }

    @action showOrderPage = () => {
        this.currentPage = this.views.order;
    }

    @action hideSideMenu = () => {
        this.sideMenu = false;
    }

    @action showSideMenu = () => {
        this.sideMenu = true;
    }

    @action showLoading = () => {
        this.isLoading = true;
    }

    @action hideLoading = () => {
        this.isLoading = false;
    }
}