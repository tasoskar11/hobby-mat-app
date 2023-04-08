import React, { Component } from 'react';
import { Button, Container, Form, FormGroup, Input, Label, ButtonGroup, Table } from 'reactstrap';

class RecipeIngredientPicker extends Component {
    
    constructor(props) {
        super(props);
        this.state = {
          ingredient: {}
        };
        this.handleChange = this.handleChange.bind(this);
        this.addToRecipe = this.addToRecipe.bind(this);
    }

    async componentDidMount() {

        //if (this.props.match.params.id !== 'new') {
            //TODO is this maybe too often called?
            const allIngredients = await (await fetch(`/api/ingredients/`)).json();
            console.log("setting the state to ", {availableIngredients: allIngredients})
            this.setState({availableIngredients: allIngredients});
        //}
    }

    handleChange(event) {
      const target = event.target;
      const value = target.value;
      const name = target.name;
      let ingredient = {...this.state.ingredient};
      ingredient[name] = value;
      console.log("setting the state to ", {ingredient})
      this.setState({ingredient});
    }
    
    async addToRecipe() {
      let ingredient = {...this.state.ingredient};
      let id = ingredient.selectedIngredient; 
      const ingredientDetailed = await (await fetch(`/api/ingredients/${id}`)).json();
      console.log("need to update the state with", ingredientDetailed);
    }

    render() {
        const availableIngredients = this.state.availableIngredients;
        const ingredient = this.state.ingredient;
        const ingredientList = availableIngredients && availableIngredients.map(ingredient => {
          return <option key={ingredient.id} value={ingredient.id}>{ingredient.name}</option>
        });

        return ingredient && (
          <div>
            <label>
              Pick a new ingredient:
              <select onChange={this.handleChange}
                name="selectedIngredient"
              >
                {ingredientList}
              </select>
            </label>
             <label>
             Pick a price estimation:
             <Input type="text" name="priceEstimation" id="priceEstimation" value={ingredient.priceEstimation || ''}
                               onChange={this.handleChange} autoComplete="priceEstimation"/>
           </label>
           <label>
             Pick a calory estimation:
             <Input type="text" name="caloryEstimation" id="caloryEstimation" value={ingredient.caloryEstimation || ''}
                               onChange={this.handleChange} autoComplete="caloryEstimation"/>
           </label>
           <label>
             Pick quantity:
             <Input type="text" name="quantity" id="quantity" value={ingredient.quantity || ''}
                               onChange={this.handleChange} autoComplete="quantity"/>
           </label>
           <Button size="sm" color="primary" onClick={this.addToRecipe}>Add to Recipe</Button>
                        
           </div>
          );
    }
}
export default RecipeIngredientPicker;