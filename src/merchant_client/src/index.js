import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import * as serviceWorker from './serviceWorker';
import { Provider } from 'mobx-react';
import Stores from './stores';
import { BrowserRouter } from 'react-router-dom';
import TxnComplete from './components/TxnComplete';
import { Route } from 'react-router-dom';

const stores = new Stores()

ReactDOM.render(
  <Provider stores={stores}>
    <BrowserRouter>
      <Route exact path="/" component={App}/>
      <Route path="/txn/complete" component={TxnComplete}/>
    </BrowserRouter>
  </Provider>,
  document.getElementById('root'));

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: http://bit.ly/CRA-PWA
serviceWorker.unregister();
