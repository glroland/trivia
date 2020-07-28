import React, {Component} from "react";
import "../App.css";
import GameConfig from "./GameConfig.js";
import TriviaQuestion from "./TriviaQuestion.js";

class PracticeGame extends Component {

    constructor(props) {
        super(props);
        this.state = {
        };
    }

    render() {
        return(
            <div>
                <p>PracticeGame</p>
                <br />
                <div><GameConfig /></div>
                <br />
                <div><TriviaQuestion /></div>
            </div>
        );
    }
}

export default PracticeGame;
