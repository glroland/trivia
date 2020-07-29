import React, {Component} from "react";
import "../App.css";

class GameConfig extends Component {

    constructor(props) {
        super(props);
        this.state = {
            error: null,
            categoryId: null,
            difficulty: null,
            categories: []
        };
    }
  
    handleChange(event) {
        this.setState({value: event.target.value});
    }
  
    componentDidMount() {
        fetch('http://localhost:8080/categories')
        .then(res => res.json())
        .then((data) => {
          this.setState({ categories: data })
        })
        .catch(console.log);
    }
   
    createCategoryListOptions() {
        let items = [];
        console.log(this.state.categories);
        console.log(this.state.categories.length);
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
                    <select value={this.state.categoryId} onChange={this.handleChange}>
                        {this.createCategoryListOptions()}
                    </select>
                </label>
                <br/>
                <label>
                    Difficulty:
                    <select value={this.state.difficulty} onChange={this.handleChange}>
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
