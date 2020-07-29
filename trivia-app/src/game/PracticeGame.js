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
    
    handleSubmit(event) {
        alert('Your favorite flavor is: ' + this.state.value);
        event.preventDefault();
    }

    render() {
        return(
            <form onSubmit={this.handleSubmit}>
                <div>
                    <p>PracticeGame</p>
                    <br />
                    <div><GameConfig /></div>
                    <br />
                    <div><TriviaQuestion /></div>
                </div>
            </form>
        );
    }
}

export default PracticeGame;
