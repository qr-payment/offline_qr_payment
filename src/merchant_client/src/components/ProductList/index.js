import React, { Component } from "react";
import { inject, observer } from "mobx-react";
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';
import GridList from '@material-ui/core/GridList';
import GridListTile from '@material-ui/core/GridListTile';
import GridListTileBar from '@material-ui/core/GridListTileBar';
import ListSubheader from '@material-ui/core/ListSubheader';
import IconButton from '@material-ui/core/IconButton';
import InfoIcon from '@material-ui/icons/Info';
import {
    Add,
    Remove
  } from '@material-ui/icons';
import NumberFormat from 'react-number-format';

let styles = (theme, gridList)  => ({
    root: {
        display: 'flex',
        flexWrap: 'wrap',
        justifyContent: 'space-around',
        overflow: 'hidden',
        backgroundColor: theme.palette.background.paper,
    },
    gridList: {
        width: '100%',
        height: '100%'
    },
    icon: {
        color: 'rgba(255, 255, 255, 0.54)',
    },
});

@inject('stores')
@observer
class TitlebarGridList extends Component {

    onPlusClick = (e, idx) => {

        const { order } = this.props.stores;
        order.addOrderList(idx);

    }

    onMinusClick = (e, idx) => {

        const { order } = this.props.stores;
        order.removeOrderList(idx);

    }

    renderProductList = () => {

        const { order } = this.props.stores;

        if(order.productList.length === 0) {
            order.getDummyProductList();
        } 

        return order.countedProductList;

    }    

    render() {

        const { classes } = this.props;
        const countedProductList = this.renderProductList();
        
        return (
            <div className={classes.root}>
                <GridList cellHeight={300} className={classes.gridList}>
                    <GridListTile key="Subheader" cols={2} style={{ height: 'auto' }}>
                        <ListSubheader component="div" style={{fontSize: 20}}>상품 목록</ListSubheader>
                    </GridListTile>
                    {countedProductList.map((product, i) => (
                        <GridListTile key={i}>
                            <img src={product.img} alt={product.img} />
                            <GridListTileBar
                                title={product.name}
                                subtitle={<span>가격 : <NumberFormat value={product.price}thousandSeparator={true} displayType={'text'}/> 원</span>}
                                actionIcon={
                                    <div>
                                    <IconButton className={classes.icon} onClick={e => this.onMinusClick(e, product.idx)}>
                                        <Remove/>
                                    </IconButton>
                                    <IconButton className={classes.icon}>
                                        {product.count}
                                    </IconButton>
                                    <IconButton className={classes.icon} onClick={e => this.onPlusClick(e, product.idx)}>
                                        <Add/>
                                    </IconButton>
                                    <IconButton className={classes.icon}>
                                        <InfoIcon />
                                    </IconButton>
                                    </div>
                                }
                            >
                            </GridListTileBar>
                        </GridListTile>
                    ))}
                </GridList>
            </div>
        );
    }
}

TitlebarGridList.propTypes = {
    classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(TitlebarGridList);