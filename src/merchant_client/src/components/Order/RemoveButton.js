import React, { Component } from "react";
import { inject, observer } from "mobx-react";
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';
import Button from '@material-ui/core/Button';

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
class RemoveButton extends Component {


    onRemoveButtonClick = () => {

        const { idx } = this.props;
        const { order } = this.props.stores;

        order.removeProduct(idx);

    }

    render() {

        const { classes } = this.props;
        
        return (
            <span>
                <Button variant="outlined" size="small" className={classes.button} onClick={this.onRemoveButtonClick}>
                    삭제
                </Button>
            </span>
        )
    };
}

RemoveButton.propTypes = {
    classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(RemoveButton);