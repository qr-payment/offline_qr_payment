import React, { Component } from "react";
import { inject, observer } from "mobx-react";
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';
import Button from '@material-ui/core/Button';
import ResetDialog from './ResetDialog';

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

    onResetButtonClick = () => {

        const { order } = this.props.stores;

        order.showResetDialog();

    }

    render() {

        const { classes } = this.props;
        
        return (
            <span>
                <Button variant="outlined" size="large" className={classes.button} onClick={this.onResetButtonClick} style={{fontSize:18}}>
                    주문 내역 초기화
                </Button>
            </span>
        )
    };
}

OrderButton.propTypes = {
    classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(OrderButton);