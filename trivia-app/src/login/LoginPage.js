import React, { Component } from "react";
import { withRouter } from 'react-router-dom';
import { Grid } from '@material-ui/core';
import { Paper } from '@material-ui/core';
import { TextField } from '@material-ui/core';
import { Button } from '@material-ui/core';
import { InputBase } from '@material-ui/core';
import { Typography } from '@material-ui/core';
import { playerContext } from '../playerContext';

class GamePage extends Component {
  
  constructor(props) {
    super(props);
    this.state = {
      name: 'First Last',
      email: 'user@email.com', 
      playerId: null
    };

    this.statusRef = React.createRef();
  }
  
  render() {

    const centerLoginBoxStyle = { width: "350px", position: "absolute", top: "50%", left: "45%", transform: "translateY(-50%)" };

    return (

      <form autoComplete="off">
        <Paper elevation={3} style={centerLoginBoxStyle}>
          <Grid container direction="column" justify="center" alignItems="center" spacing={6}>
            <Grid item xs={12}>
              <Typography variant="h4">Trivia Time!</Typography>
            </Grid>
            <Grid item xs={12}>
              <TextField  id="name" 
                          label="Name" 
                          fullWidth={true}
                          required={true}
                          value={this.state.name} 
                          onChange={(e) => this.setState({ name: e.target.value }) }  />
            </Grid>
            <Grid item xs={12}>
              <TextField  id="email" 
                          label="Email" 
                          fullWidth={true}
                          required={true}
                          value={this.state.email} 
                          onChange={(e) => this.setState({ email: e.target.value }) }  />
            </Grid>
            <Grid item xs={12}>
              <Button variant="contained" color="primary" onClick={() => { this.signinClicked() }}>Sign In</Button>
            </Grid>
            <Grid item xs={12}>
              <InputBase
                  id="status"
                  inputRef={this.statusRef}
                  disabled={true}
                  value=""
                  inputProps={{ 'aria-label': 'naked' }}
                  style={{ 'color': 'red' }}
                />
            </Grid>
          </Grid>
        </Paper>
      </form>
    );
  }

  setStatus(msg) {
    console.log("Setting Status Bar to: " + msg);
    this.statusRef.current.value = msg;
  }

  signinClicked() {
    if ((this.state.name.length == 0) || (this.state.email.length == 0))
    {
      this.setStatus("Name and Email Required");
      return;
    }

    this.invokeSigninService(this.state.name, this.state.email, this.invokeSigninServiceComplete);
    this.setStatus("Authenticating Player");
  }

  invokeSigninServiceComplete(response) {
    console.log(response.data);
    this.setStatus("Sign In Complete");
    
    const playerId = response.data;
    console.log("Sign In Service Invoked w/Resulting Player ID = " + playerId);

    this.setState({ playerId: playerId })

    this.props.history.push('/dashboard');
  }

  invokeSigninServiceError(error) {
    console.log("Unable to invoke remote service: " + error);
    this.setStatus("Server Error!  Please retry.");
  }

  invokeSigninService(name, email, responseCallback) {
    const axios = require('axios');

    axios.get('/api/gamemaster/signin', {
      params: {
        name: name,
        email: email
      }
    })
    .then((response) => this.invokeSigninServiceComplete(response))
    .catch((error) => this.invokeSigninServiceError(error));
  }
};

export default withRouter(GamePage);
