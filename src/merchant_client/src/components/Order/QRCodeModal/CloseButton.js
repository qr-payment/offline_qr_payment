import React, { Component } from "react";
import { inject, observer } from "mobx-react";
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';
import Button from '@material-ui/core/Button';
import "./styles.scss";

const styles = theme => ({
    button: {
        margin: theme.spacing.unit,
    },
    input: {
        display: 'none',
    },
});

@inject('stores')
@observer
class CloseButton extends Component {


    onCloseButtonClick = () => {

        const { order } = this.props.stores;
        order.hideQRCodeModal();

    }

    render() {

        const { classes } = this.props;
        
        return (
            <span>
                <Button variant="outlined" size="large" className={classes.button} onClick={this.onCloseButtonClick}>
                    닫기
                </Button>
            </span>
        )
    };
}

CloseButton.propTypes = {
    classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(CloseButton);