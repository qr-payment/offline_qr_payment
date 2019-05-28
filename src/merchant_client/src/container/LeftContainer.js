import React from "react";
import { Component } from "react";
import "./Container.scss";
import { inject, observer } from 'mobx-react';
import ProductList from '../components/ProductList'

@inject('stores')
@observer
class LeftContainer extends Component {

  render() {
    return (
      <div className="LeftContainer">
          <ProductList/>
      </div>
    );
  }
}

export default LeftContainer;
