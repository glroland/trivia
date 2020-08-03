import React, {Component} from "react";
import "../App.css";

class GameConfig extends Component {

    constructor(props) {
        super(props);
        this.state = {
            error: null,
            categoryId: 0,
            difficulty: null,
            categories: [],
            questions: []
        };
    }
  
    handleChange_selectCategory = (event) => {
        this.setState({ categoryId: event.target.value })
        this.refreshQuestions();
    }
  
    invokeGetCategories() {
        fetch('http://localhost:8080/categories')
        .then(res => res.json())
        .then((data) => {
          this.setState({ categories: data })
        })
        .catch(console.log);
    }

    invokeGetQuestions() {
        fetch('http://localhost:8080/questions?categoryId=' + this.state.categoryId)
        .then(res => res.json())
        .then((data) => {
          this.setState({ questions: data })
        })
        .catch(console.log);
    }

    refreshQuestions() {
        this.invokeGetQuestions();
        console.log(this.state.questions);
    }

    componentDidMount() {
        this.invokeGetCategories();
    }
   
    createCategoryListOptions() {
        let items = [];
        for (let i = 0; i < this.state.categories.length; i++)
        {
            items.push(<option key={this.state.categories[i].id} value={this.state.categories[i].id}>{this.state.categories[i].name}</option>); 
        }
        return items;
    }

    render() {
        return(
            <div>
                <p>GameConfig</p>
                <label>
                    Category:
                    <select value={this.state.categoryId} onChange={this.handleChange_selectCategory}>
                        {this.createCategoryListOptions()}
                    </select>
                </label>
                <br/>
                <label>
                    Difficulty:
                    <select value={this.state.difficulty} onChange={() => this.handleChange()}>
                        <option key="" value=""></option>
                        <option key="easy" value="easy">Easy</option>
                        <option key="medium" value="medium">Medium</option>
                        <option key="hard" value="hard">Hard</option>
                    </select>
                </label>
            </div>
        );

    }
}

export default GameConfig;
