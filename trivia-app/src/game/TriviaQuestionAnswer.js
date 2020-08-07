import React, {Component} from "react";
import "../App.css";

class TriviaQuestionAnswer extends Component {

    constructor(props) {
        super(props);
        this.state = {
            answer: props.answer,
            questionId: props.questionId,
            answerId: props.answerId
        };

        console.log("Trivia Question Answer - QID=" + props.questionId + " AID=" + props.answerId + " Answer='" + props.answer + "'");
    }

    render() {
        let nameAnswerValue = 'TQ_' + this.state.questionId;
        let answerId = 'TQA_' + this.state.questionId + '_' + this.state.answerId;
        return(
            <div id={answerId}>
                <input type="radio" value={this.state.answerId} name={nameAnswerValue} /> {this.state.answer}
            </div>
        );
    }
}

export default TriviaQuestionAnswer;
