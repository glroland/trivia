import React, {Component} from "react";
import { BrowserRouter, Switch, Route } from 'react-router-dom';
import Dashboard from './Dashboard';
import LoginPage from './login/LoginPage';
import {playerContext} from './playerContext';

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
        user: {}
    };
  }
  
  render() {

    return (
      <playerContext.Provider value={this.state.user}>
        <div className="App">
          <BrowserRouter>
            <Switch>
              <Route exact path='/' component={ LoginPage }/>
              <Route path='/dashboard' component={ Dashboard }/>
            </Switch>
          </BrowserRouter>
        </div>
      </playerContext.Provider>
    );
  }

}

export default App;
