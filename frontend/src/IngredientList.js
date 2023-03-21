import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';

class IngredientList extends Component {

    constructor(props) {
        super(props);
        this.state = {ingredients: []};
        this.remove = this.remove.bind(this);
    }

    componentDidMount() {
        fetch('/api/ingredients')
            .then(response => response.json())
            .then(data => this.setState({ingredients: data}));
    }

    async remove(id) {
        await fetch(`/api/ingredients/${id}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            let updatedIngredients = [...this.state.ingredients].filter(i => i.id !== id);
            this.setState({ingredients: updatedIngredients});
        });
    }

    render() {
        const {ingredients} = this.state;

        const ingredientList = ingredients.map(ingredient => {
            return <tr key={ingredient.id}>
                <td style={{whiteSpace: 'nowrap'}}>{ingredient.name}</td>
                <td>{ingredient.imageUrl}</td>
                <td>
                    <ButtonGroup>
                        <Button size="sm" color="primary" tag={Link} to={"/ingredients/" + ingredient.id}>Edit</Button>
                        <Button size="sm" color="danger" onClick={() => this.remove(ingredient.id)}>Delete</Button>
                    </ButtonGroup>
                </td>
            </tr>
        });

        return (
            <div>
                <AppNavbar/>
                <Container fluid>
                    <div className="float-right">
                        <Button color="success" tag={Link} to="/ingredients/new">Add ingredient</Button>
                    </div>
                    <h3>Ingredients</h3>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="30%">Name</th>
                            <th width="30%">ImageUrl</th>
                            <th width="40%">Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        {ingredientList}
                        </tbody>
                    </Table>
                </Container>
            </div>
        );
    }
}

export default IngredientList;