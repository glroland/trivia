import React, {Component} from "react";
import "../App.css";

class GameConfig extends Component {

    constructor(props) {
        super(props);
        this.state = {
            error: null,
            categoryId: 0,
            difficulty: "",
            categories: [],
            questions: []
        };
    }
  
    handleChange_selectCategory = (event) => {
        let categoryId = parseInt(event.target.value);
        console.log("Category ID Changed From(" + this.state.categoryId + ") to (" + event.target.value + ")");
        this.setState({ categoryId: categoryId })
        this.refreshQuestions(categoryId);
    }
  
    invokeGetCategories() {
        fetch('http://localhost:8080/categories')
        .then(res => res.json())
        .then((data) => {
          this.setState({ categories: data })
        })
        .catch(console.log);
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

    refreshQuestions(categoryId) {
        console.log("Refreshing Question List for Category = " + categoryId);
        this.invokeGetQuestions(categoryId);
    }

    componentDidMount() {
        this.invokeGetCategories();

        this.refreshQuestions();
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
                        <option key={0} value={0}></option>
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
