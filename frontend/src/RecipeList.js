import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';

class RecipeList extends Component {

    constructor(props) {
        super(props);
        this.state = {recipes: []};
        this.remove = this.remove.bind(this);
        this.estimatePrice = this.estimatePrice.bind(this);
    }

    componentDidMount() {
        fetch('/api/recipes')
            .then(response => response.json())
            .then(data => this.setState({recipes: data}));
    }

    async remove(id) {
        await fetch(`/api/recipes/${id}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            let updatedRecipes = [...this.state.recipes].filter(i => i.id !== id);
            this.setState({recipes: updatedRecipes});
        });
    }

    estimatePrice(recipeIngredients) {
        if (recipeIngredients == undefined) return 0;
        const sum = recipeIngredients.reduce((sum, ingredient) => sum + ingredient.priceEstimation, 0);
        return sum;
    }

    render() {
        const {recipes} = this.state;

        const recipeList = recipes.map(recipe => {
            return <tr key={recipe.id}>
                <td style={{whiteSpace: 'nowrap'}}>{recipe.name}</td>
                <td>{recipe.description}</td>
                <td>{recipe.url}</td>
                <td>{this.estimatePrice(recipe.recipeIngredients)}</td>
                <td>
                    <ButtonGroup>
                        <Button size="sm" color="primary" tag={Link} to={"/recipes/" + recipe.id}>Edit</Button>
                        <Button size="sm" color="danger" onClick={() => this.remove(recipe.id)}>Delete</Button>
                    </ButtonGroup>
                </td>
            </tr>
        });

        return (
            <div>
                <AppNavbar/>
                <Container fluid>
                    <div className="float-right">
                        <Button color="success" tag={Link} to="/recipes/new">Add recipe</Button>
                    </div>
                    <h3>Recipes</h3>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="20%">Recipe</th>
                            <th width="30%">Description</th>
                            <th width="20%">Url</th>
                            <th width="10%">Price Estimation</th>
                            <th width="20%">Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        {recipeList}
                        </tbody>
                    </Table>
                </Container>
            </div>
        );
    }
}

export default RecipeList;