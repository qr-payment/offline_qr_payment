import React, { Component } from "react";
import { inject, observer } from "mobx-react";
import OrderListTable from "./OrderListTable"
import ListSubheader from '@material-ui/core/ListSubheader';
import "./styles.scss";
import Divider from '@material-ui/core/Divider';
import OrderTable from './OrderTable';
import ResetButton from './ResetButton';
import OrderButton from './OrderButton';
import ResetDialog from './ResetDialog';
import QRCodeModal from './QRCodeModal';

@inject("stores")
@observer
class Order extends Component {

  render() {
    return (
      <div className="orderPage">
        <div>
          <ListSubheader component="div" style={{fontSize: 20}}>결제 목록</ListSubheader>
        </div>
        <Divider />
        <div>
          <OrderListTable/>
        </div>
        <div className="buttonContainer">
          <ResetButton/>
          <OrderButton/>
        </div>
        <div className="inlineBlock alignRight">
          <OrderTable className="inlineBlock"/>
        </div>
        <ResetDialog/>
        <QRCodeModal/>
      </div>
    );
  }
}

export default Order;
