import React, {Component} from "react";
import "../App.css";
import GameConfig from "./GameConfig.js";
import TriviaQuestion from "./TriviaQuestion.js";

class PracticeGame extends Component {

    constructor(props) {
        super(props);
        this.state = {
            questions: []
        };

        this.gameConfigRef = React.createRef();
    }

    handleChange_gameConfig = (categoryId, difficulty) => {
        console.log("Game Config Changed - Category = (" + categoryId + "), Difficulty = (" + difficulty + ")");

        this.refreshQuestions(categoryId, difficulty);
    }

    invokeGetQuestions(categoryId) {
        let url = 'http://localhost:8080/questions';
        if ((categoryId !== undefined) && (categoryId > 0))
            url = url + '?categoryId=' + categoryId;
        
        console.log("Invoking Get Questions Service = " + url);
        fetch(url)
        .then(res => res.json())
        .then((data) => {
          this.setState({ questions: data })
          console.log(data);
        })
        .catch(console.log);
    }

    async refreshQuestions(categoryId, difficulty) {
        console.log("Refreshing Question List for Category = (" + categoryId + "), Difficulty = (" + difficulty + ")");
        await this.invokeGetQuestions(categoryId);

        this.forceUpdate();
    }

    renderQuestion(index, question) {
        console.log("Number of Questions - " + this.state.questions.length);
        console.log(this.state.questions[0]);
        if (question !== undefined) {
            console.log(this.state.questions[0].difficulty);
            return <TriviaQuestion id={index}
                                   difficulty={question.difficulty} 
                                   question={question.question} 
                                   answers={question.answers} />;    
        }
    }

    renderQuestionList() {
        let questionList = [];
        if (this.state.questions !== undefined) {
            for (let i = 0; i < this.state.questions.length; i++)
            {
                questionList.push(<div>{ this.renderQuestion(i, this.state.questions[i]) }</div>); 
            }
        }
        return questionList;

    }

    render() {
        return(
            <div>
                <p>Practice</p>
                <br />
                <div><GameConfig ref={this.gameConfigRef} onChange={this.handleChange_gameConfig} /></div>
                <br />
                <div>{ this.renderQuestionList() }</div>
            </div>
        );
    }
}

export default PracticeGame;
