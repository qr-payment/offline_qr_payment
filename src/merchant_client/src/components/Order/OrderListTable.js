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
import Typography from '@material-ui/core/Typography';
import NumberFormat from 'react-number-format';
import RemoveButton from './RemoveButton';
import Avatar from '@material-ui/core/Avatar';
import Grid from '@material-ui/core/Grid';

const styles = theme => ({
  root: {
    width: '100%',
    marginTop: theme.spacing.unit * 3,
    overflowX: 'auto',
    overflowY: 'scroll',
    height: 483,
  },
  table: {
    minWidth: 700,
  },
  bigAvatar: {
    width: 50,
    height: 50,
  },
});

@inject('stores')
@observer
class OrderListTable extends Component {
  
  render() {

    const { classes } = this.props;
    const { orderList } = this.props.stores.order;
    
    return (
      <Paper className={classes.root}>
        <Table className={classes.table}>
          <TableHead>
            <TableRow style={{fontSize: 15}}>
              <TableCell style={{fontSize: 15}}>상품정보</TableCell>
              <TableCell style={{fontSize: 15}} align="right">수량</TableCell>
              <TableCell style={{fontSize: 15}} align="right">상품금액</TableCell>
              <TableCell style={{fontSize: 15}} align="center">주문금액</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {orderList.map(product => (
              <TableRow key={product.idx}>
                <TableCell component="th" scope="row" align="left">
                  <Grid container alignItems="center">
                    <Avatar display="inline" alt="Remy Sharp" src={product.img} className={classes.bigAvatar}/>
                    <span style={{width:20}}> </span>
                    <Typography style={{fontSize: 15}}>
                      {product.name}
                    </Typography>
                  </Grid>
                </TableCell>
                <TableCell align="right">{product.count} 개 </TableCell>
                <TableCell align="right"><NumberFormat value={product.price} thousandSeparator={true} displayType={'text'}/> 원</TableCell>
                <TableCell align="right">
                    <NumberFormat value={product.count * product.price} thousandSeparator={true} displayType={'text'}/> 원
                    <RemoveButton idx={product.idx}/>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </Paper>
    )
  };
}

OrderListTable.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(OrderListTable);