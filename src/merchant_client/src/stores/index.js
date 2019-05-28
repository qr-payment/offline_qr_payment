import View from './View';
import Order from './Order';
import Static from './Static';
import Dummy from './Dummy';

class RootStore {
  constructor() {
    this.view = new View(this);
    this.order = new Order(this);
    this.static = new Static(this);
    this.dummy = new Dummy(this);
  }
}

export default RootStore;