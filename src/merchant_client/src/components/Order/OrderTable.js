import React, { Component } from "react";
import { inject, observer } from "mobx-react";
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import NumberFormat from 'react-number-format';
import Divider from '@material-ui/core/Divider';
import RemoveButton from './RemoveButton';
import "./styles.scss";

const styles = theme => ({
  root: {
    width: 310,
    overflowX: 'auto',
  },
  table: {
    maxWidth: 310,
    minWidth: 310,
  },
});

@inject('stores')
@observer
class OrderTable extends Component {
  
  render() {

    const { classes } = this.props;
    const { amount } = this.props.stores.order;
    const { discountAmount } = this.props.stores.order;
    
    return (
      // <Paper className={classes.root}>
      <div className="inlineBlock">
        <Table className={classes.table}>
          <TableHead>
          </TableHead>
          <TableBody>
          <TableRow className="orderTable">
            <TableCell className="tableText" align="center">총 상품금액</TableCell>
            <TableCell className="tableText" align="right">
              <NumberFormat value={amount} thousandSeparator={true} displayType={'text'}/> 원
            </TableCell>
          </TableRow>
          <TableRow className="orderTable">
            <TableCell className="tableText" align="center">총 할인금액</TableCell>
            <TableCell className="tableText" align="right">
              <NumberFormat value={discountAmount} thousandSeparator={true} displayType={'text'}/> 원
            </TableCell>
          </TableRow>
          <TableRow className="orderResult">
            <TableCell className="tableText" align="center">결제금액</TableCell>
            <TableCell className="tableText" align="right">
              <NumberFormat value={amount - discountAmount} thousandSeparator={true} displayType={'text'}/> 원
            </TableCell>
          </TableRow>
          </TableBody>
        </Table>
        </div>
      // </Paper>
    )
  };
}

OrderTable.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(OrderTable);