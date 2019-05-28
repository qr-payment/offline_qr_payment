import React, { Component } from "react";
import "./styles.scss";
import { inject, observer } from "mobx-react";
import MenuBar from "./components/MenuBar";
import ProductList from "./components/ProductList"
import LeftContainer from "./container/LeftContainer";
import RightContainer from "./container/RightContainer";
import SideMenu from "./components/SideMenu";
import { CssBaseline, CircularProgress } from "@material-ui/core";

@inject("stores")
@observer
class App extends Component {

  renderLoadingPage = () => {
    if (this.props.stores.view.isLoading) {
      return (
        <div className="App">
        <div
          className="loading"
          onClick={e => {
            e.stopPropagation(); // disable click through this div
          }}>
          <CircularProgress className="progress" size={60} />
        </div>
        </div>
      );
    }
  };

  renderPage = () => {

    const { view } = this.props.stores;

    if(view.currentPage === view.views.order) {
      return (<div className="root">
      <div className="leftContainer">
        <LeftContainer />
      </div>
      <div className="rightContainer">
        <RightContainer />
      </div>
    </div>);
    } else {
      return (<div className="root">
      <div>History Page</div>
    </div>)
    }
    
  }

  renderHome = () => {
    const { view, user } = this.props.stores
    const page = this.renderPage();
    
    if(view.isLoading) {
      return this.renderLoadingPage();
    } else {
      return (<div className="App">
      <div className="bar">
        <MenuBar />
        <SideMenu/>
      </div>
      {page}
    </div>)
    }
  }

  render() {
    return (
      this.renderHome()
    );
  }
}

export default App;
