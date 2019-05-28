import React from 'react';
import PropTypes from 'prop-types';
import Button from '@material-ui/core/Button';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';
import withMobileDialog from '@material-ui/core/withMobileDialog';
import { inject, observer } from "mobx-react";

@inject("stores")
@observer
class ResetDialog extends React.Component {

    onDisagreeClick = () => {

        const { order } = this.props.stores;
        order.hideResetDialog();

    }

    onAgreeClick = () => {

        const { order } = this.props.stores;
        order.resetOrderList();
        order.hideResetDialog();
        
    }

    render() {
    const { fullScreen } = this.props;
    const { resetDialog } = this.props.stores.order;
    return (
      <div>
        <Dialog
          fullScreen={fullScreen}
          open={resetDialog}
          onClose={this.handleClose}
          aria-labelledby="responsive-dialog-title"
        >
          <DialogContent>
            <DialogContentText>
              등록하신 구매 내역을 모두 비우시겠습니까?
            </DialogContentText>
          </DialogContent>
          <DialogActions>
            <Button onClick={this.onDisagreeClick} color="primary">
              No
            </Button>
            <Button onClick={this.onAgreeClick} color="primary" autoFocus>
              Yes
            </Button>
          </DialogActions>
        </Dialog>
      </div>
    );
  }
}

ResetDialog.propTypes = {
  fullScreen: PropTypes.bool.isRequired,
};

export default withMobileDialog()(ResetDialog);