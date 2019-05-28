import React, { Component } from "react";
import { inject, observer } from "mobx-react";
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';
import Paper from '@material-ui/core/Paper';
import Typography from '@material-ui/core/Typography';

const styles = theme => ({
  root: {
    ...theme.mixins.gutters(),
    paddingTop: theme.spacing.unit * 2,
    paddingBottom: theme.spacing.unit * 2,
  },
});


@inject('stores')
@observer
class ProductName extends Component {

  render() {

    const { classes } = this.props;
    const { info } = this.props;

    return (
        <div>
        <Paper className={classes.root} elevation={1}>
            <Typography variant="h5" component="h5">
                {info.name}
            </Typography>
            <Typography component="p">
                {info.description}
            </Typography>
        </Paper>
        </div>
    )
  };
}

ProductName.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(ProductName);