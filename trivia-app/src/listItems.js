import * as React from 'react';
import FaceIcon from '@material-ui/icons/Face';
import SportsEsportsIcon from '@material-ui/icons/SportsEsports';
import LocalBarIcon from '@material-ui/icons/LocalBar';
import HomeIcon from '@material-ui/icons/Home';
import GamepadIcon from '@material-ui/icons/Gamepad';
import PowerIcon from '@material-ui/icons/Power';
import SettingsIcon from '@material-ui/icons/Settings';
import ListItem from '@material-ui/core/ListItem';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemText from '@material-ui/core/ListItemText';
import ListSubheader from '@material-ui/core/ListSubheader';

export const mainListItems = (
  <div>
    <ListItem button>
      <ListItemIcon>
        <HomeIcon />
      </ListItemIcon>
      <ListItemText primary="Home" />
    </ListItem>
    <ListItem button>
      <ListItemIcon>
        <FaceIcon />
      </ListItemIcon>
      <ListItemText primary="Register" />
    </ListItem>
    <ListItem button>
      <ListItemIcon>
        <LocalBarIcon />
      </ListItemIcon>
      <ListItemText primary="Lobby" />
    </ListItem>
    <ListItem button>
      <ListItemIcon>
        <SportsEsportsIcon />
      </ListItemIcon>
      <ListItemText primary="Game" />
    </ListItem>
    <ListItem button>
      <ListItemIcon>
        <SettingsIcon />
      </ListItemIcon>
      <ListItemText primary="Settings" />
    </ListItem>
    <ListItem button>
      <ListItemIcon>
        <PowerIcon />
      </ListItemIcon>
      <ListItemText primary="Admin" />
    </ListItem>
  </div>
);

export const secondaryListItems = (
  <div>
    <ListSubheader inset>All Active Games</ListSubheader>
    <ListItem button>
      <ListItemIcon>
        <GamepadIcon />
      </ListItemIcon>
      <ListItemText primary="123" />
    </ListItem>
    <ListItem button>
      <ListItemIcon>
        <GamepadIcon />
      </ListItemIcon>
      <ListItemText primary="456" />
    </ListItem>
    <ListItem button>
      <ListItemIcon>
        <GamepadIcon />
      </ListItemIcon>
      <ListItemText primary="789" />
    </ListItem>
  </div>
);
