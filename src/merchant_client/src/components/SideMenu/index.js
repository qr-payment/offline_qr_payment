import React from "react";
import PropTypes from "prop-types";
import { withStyles } from "@material-ui/core/styles";
import SwipeableDrawer from "@material-ui/core/SwipeableDrawer";
import {
  List,
  Divider,
  ListItem,
  ListItemIcon,
  ListItemText
} from "@material-ui/core";
import { inject, observer } from "mobx-react";
import { HowToReg, Create, ExitToApp } from "@material-ui/icons";

const styles = {
  list: {
    width: 250
  },
  fullList: {
    width: "auto"
  }
};

@inject("stores")
@observer
class SideMenu extends React.Component {

  hideSideMenu = () => {
    const { view } = this.props.stores;
    view.hideSideMenu();
  };

  toggleDrawer = open => () => {
    const { view } = this.props.stores;
    if (open === "true") {
      view.showSideMenu();
    } else {
      view.hideSideMenu();
    }
  };

  onButtonClick = button => () => {

    const { view } = this.props.stores;

    switch (button) {
      case "order":
        view.showOrderPage();
        break;
      case "history":
        view.showOrderHistoryPage();
        break;
      default:
        alert("Error");
        break;
    }
  };

  renderFunc = () => {
    const { classes } = this.props;
    const { view } = this.props.stores;
    return (
      <div className={classes.list}>
        <List>
          <ListItem
            button
            key={view.views.order}
            onClick={this.onButtonClick("order")}>
            <ListItemIcon>
              <Create />
            </ListItemIcon>
            <ListItemText primary={view.views.order} />
          </ListItem>
          <ListItem
            button
            key={view.views.orderHistory}
            onClick={this.onButtonClick("history")}>
            <ListItemIcon>
              <HowToReg />
            </ListItemIcon>
            <ListItemText primary={view.views.orderHistory} />
          </ListItem>
        </List>
      </div>
    );
  };

  render() {
    const { view } = this.props.stores;

    const sideList = this.renderFunc();

    return (
      <div>
        <SwipeableDrawer
          anchor="left"
          open={view.sideMenu}
          onClose={this.toggleDrawer(false)}
          onOpen={this.toggleDrawer(true)}>
          <div
            tabIndex={0}
            role="button"
            onClick={this.toggleDrawer(false)}
            onKeyDown={this.toggleDrawer(false)}>
            {sideList}
          </div>
        </SwipeableDrawer>
      </div>
    );
  }
}

SideMenu.propTypes = {
  classes: PropTypes.object.isRequired
};

export default withStyles(styles)(SideMenu);
