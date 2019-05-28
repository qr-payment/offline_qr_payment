import React from "react";
import { Component } from "react";
import "./Container.scss";
import { inject, observer } from "mobx-react";
import Order from '../components/Order'

@inject("stores")
@observer
class RightContainer extends Component {

  render() {
    return (
      <div className="RightContainer">
        <Order/>
      </div>
    );
  }
}

export default RightContainer;
