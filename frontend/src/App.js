import React, { Component } from 'react';
import './App.css';
import Home from './Home';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import FoodLogList from './FoodLogList';
import FoodLogEdit from "./FoodLogEdit";
import IngredientEdit from './IngredientEdit';
import IngredientList from './IngredientList';
import RecipeList from './RecipeList';
import RecipeEdit from './RecipeEdit';

class App extends Component {
  render() {
    return (
        <Router>
          <Switch>
            <Route path='/' exact={true} component={Home}/>
            <Route path='/foodLogs' exact={true} component={FoodLogList}/>
            <Route path='/foodLogs/:id' component={FoodLogEdit}/>
            <Route path='/ingredients' exact={true} component={IngredientList}/>
            <Route path='/ingredients/:id' component={IngredientEdit}/>
            <Route path='/recipes' exact={true} component={RecipeList}/>
            <Route path='/recipes/:id' component={RecipeEdit}/>
          </Switch>
        </Router>
    )
  }
}

export default App;