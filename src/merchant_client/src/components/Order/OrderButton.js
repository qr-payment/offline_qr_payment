import React, { Component } from "react";
import { inject, observer } from "mobx-react";
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';
import Button from '@material-ui/core/Button';

const styles = theme => ({
    button: {
        marginRight: 10,
        marginTop: 12,
        marginLeft: 0,
        width: '46%',
        height: '100%',
    },
    input: {
        display: 'none',
    },
});

@inject('stores')
@observer
class OrderButton extends Component {


    onOrderButtonClick = () => {

        const { order } = this.props.stores;
        
        if(order.orderList.length !== 0) {
            order.showQRCodeModal();
        }

    }

    render() {

        const { classes } = this.props;
        
        return (
            <span>
                <Button variant="outlined" size="large" className={classes.button} onClick={this.onOrderButtonClick} style={{color:'#20c354', fontSize:18}}>
                    QR 코드 생성
                </Button>
            </span>
        )
    };
}

OrderButton.propTypes = {
    classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(OrderButton);