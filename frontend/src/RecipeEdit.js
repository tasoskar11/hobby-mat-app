import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label, ButtonGroup, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import RecipeIngredientPicker from './RecipeIngredientPicker';

class RecipeEdit extends Component {

    emptyItem = {
        name: '',
        url: ''
    };

    constructor(props) {
        super(props);
        this.state = {
            item: this.emptyItem
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    async componentDidMount() {
        if (this.props.match.params.id !== 'new') {
            const recipe = await (await fetch(`/api/recipes/${this.props.match.params.id}`)).json();
            console.log("setting the state to ", {item: recipe})
            this.setState({item: recipe});
        }
    }

    handleChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let item = {...this.state.item};
        item[name] = value;
        console.log("setting the state to ", {item})
        this.setState({item});
    }

async handleSubmit(event) {
    event.preventDefault();
    const {item} = this.state;


    await fetch('/api/recipes' + (item.id ? '/' + item.id : ''), {
        method: (item.id) ? 'PUT' : 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(item),
    });
    this.props.history.push('/recipes');
}

    render() {
        const {item} = this.state;
        const title = <h2>{item.id ? 'Edit Recipe' : 'Add Recipe'}</h2>;

        const ingredientList = item.recipeIngredients && item.recipeIngredients.map(recipeIngredient => {
            return <tr key={recipeIngredient.ingredient.id}>
                <td style={{whiteSpace: 'nowrap'}}>{recipeIngredient.ingredient.name}</td>
                <td>{recipeIngredient.ingredient.imageUrl}</td>
                <td>{recipeIngredient.quantity}</td>
                <td>{recipeIngredient.priceEstimation}</td>
                <td>{recipeIngredient.caloryEstimation}</td>
                <td>
                    <ButtonGroup>
                        <Button size="sm" color="primary" tag={Link} to={"/ingredients/" + recipeIngredient.ingredient.id}>Edit</Button>
                        <Button size="sm" color="danger" onClick={() => console.log("TODO remove from recipe")}>Remove From Recipe</Button>
                    </ButtonGroup>
                </td>
            </tr>
        });

        return <div>
            <AppNavbar/>
            <Container>
                {title}
                <Form onSubmit={this.handleSubmit}>
                    <FormGroup>
                        <Label for="name">Name</Label>
                        <Input type="text" name="name" id="name" value={item.name || ''}
                               onChange={this.handleChange} autoComplete="name"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="url">Url</Label>
                        <Input type="text" name="url" id="url" value={item.url || ''}
                               onChange={this.handleChange} autoComplete="url"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="description">Description</Label>
                        <Input type="text" name="description" id="description" value={item.description || ''}
                               onChange={this.handleChange} autoComplete="description"/>
                    </FormGroup>
                    <FormGroup>
                        <Table className="mt-4">
                            <thead>
                            <tr>
                                <th width="20%">Ingredient</th>
                                <th width="30%">Image url</th>
                                <th width="10%">Quantity</th>
                                <th width="10%">Price Estimation</th>
                                <th width="10%">Calory Estimation</th>
                                <th width="20%">Actions</th>
                            </tr>
                            </thead>
                            <tbody>
                            {ingredientList}
                            </tbody>
                        </Table>
                    </FormGroup>
                    <FormGroup>
                        <RecipeIngredientPicker/>
                        
                    </FormGroup>
                    <FormGroup>
                        <Button color="primary" type="submit">Save</Button>{' '}
                        <Button color="secondary" tag={Link} to="/recipes">Cancel</Button>
                    </FormGroup>
                </Form>
            </Container>
        </div>
    }
}

export default withRouter(RecipeEdit);