import React, {Component} from "react";
import "../App.css";

class GameConfig extends Component {

    constructor(props) {
        super(props);
        this.state = {
            categoryId: 0,
            difficulty: "",
            categories: []
        };

        this.eventSync_onChange = props.onChange;
    }
  
    async fireChangeListeners(categoryId, difficulty) {
        this.eventSync_onChange(categoryId, difficulty);
    }

    handleChange_selectCategory = (event) => {
        let categoryId = parseInt(event.target.value);
        console.log("Category ID Changed From (" + this.state.categoryId + ") to (" + event.target.value + ")");
        this.setState({ categoryId: categoryId })

        this.fireChangeListeners(categoryId, this.state.difficulty);
    }

    handleChange_selectDifficulty = (event) => {
        let difficulty = event.target.value;
        console.log("Difficulty Changed From (" + this.state.difficulty + ") to (" + event.target.value + ")");
        this.setState({ difficulty: difficulty });

        this.fireChangeListeners(this.state.categoryId, difficulty);
    }
  
    invokeGetCategories() {
        fetch('http://localhost:8080/categories')
        .then(res => res.json())
        .then((data) => {
          this.setState({ categories: data })
        })
        .catch(console.log);
    }

    async refreshCategories() {
        await this.invokeGetCategories();

        this.fireChangeListeners();
    }

    componentDidMount() {
        this.refreshCategories();
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
                <p>Config</p>
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
                    <select value={this.state.difficulty} onChange={() => this.handleChange_selectDifficulty}>
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
