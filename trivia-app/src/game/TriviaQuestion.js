import React, {Component} from "react";
import "../App.css";

class TriviaQuestion extends Component {

    constructor(props) {
        super(props);
        this.state = {
            difficulty: props.difficulty,
            question: props.question,
            answers: props.answers
        };

        console.log("Trivia Question - Difficulty='" + props.difficulty + "' Question='" + props.question + "'");
    }

    onChange = (event) => {
        let index = parseInt(event.target.value);
        if (this.state.answers[index].correctFlag === true)
            alert("CORRECT!");
        else
            alert("WRONG!");
    }

    renderAnswers() {
        let answers = [];

        let numAnswers = this.state.answers.length;
        for (var i = 0; i < numAnswers; i++) {
            answers.push(<div><input type="radio" value={i} name="answer" /> {this.state.answers[i].value}</div>);
        }

        return answers;
    }

    render() {
        return(
            <div>
                <p><b>Question:</b></p>
                <p>{this.state.question}</p>
                <div onChange={this.onChange}>
                    {this.renderAnswers()}
                </div>
            </div>
        );
    }
}

export default TriviaQuestion;
