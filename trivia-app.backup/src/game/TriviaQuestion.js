import React, {Component} from "react";
import TriviaQuestionAnswer from "./TriviaQuestionAnswer.js";
import "../App.css";

class TriviaQuestion extends Component {

    constructor(props) {
        super(props);
        this.state = {
            difficulty: props.difficulty,
            question: props.question,
            answers: props.answers,
            id: props.id,
            questionClean: this.decodeHtmlEntity(props.question)
        };

        console.log("Trivia Question - ID=" + props.id + " Difficulty='" + props.difficulty + "' Question='" + props.question + "'");
    }

    decodeHtmlEntity = function(str) {
        return str.replace(/&(\w+);/g, function(match, dec) {
          return String.fromCharCode(dec);
        });
      };

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
            let id = "TQA_" + this.state.id;
            answers.push(<TriviaQuestionAnswer questionId={this.state.id} answerId={i} answer={this.state.answers[i].value} />);
        }

        return answers;
    }

    render() {
        return(
            <div>
                <p><b>Question:</b></p>
                <p>{ this.state.question }</p>
                <p>{ this.state.questionClean }</p>
                <div onChange={this.onChange}>
                    {this.renderAnswers()}
                </div>
            </div>
        );
    }
}

export default TriviaQuestion;
