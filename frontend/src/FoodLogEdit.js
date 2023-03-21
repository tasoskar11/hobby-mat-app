import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import AppNavbar from './AppNavbar';

class FoodLogEdit extends Component {

    //"recipe":{"id":null,"name":"grekisk bifteki med potatis i ugnen","url":null,"description":"some description","recipeIngredients":null}}

    constructor(props) {
        super(props);
        this.state = {
            id: '',
            date: '',
            name: ''
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleDateChange = this.handleDateChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.retrieveNameFromDataStructure = this.retrieveNameFromDataStructure.bind(this);
    }

    async componentDidMount() {
        if (this.props.match.params.id !== 'new') {
            const foodLog = await (await fetch(`/api/foodLogs/${this.props.match.params.id}`)).json();
            const foodLogName = this.retrieveNameFromDataStructure(foodLog);
            this.setState(state => ({id: foodLog.id, date: foodLog.date, name: foodLogName}));
        }
    }

    retrieveNameFromDataStructure(foodLog) {
        return foodLog.recipe.name;
    }

    handleChange(event) {
        this.setState({name: event.target.value});
      }
    handleDateChange(event) {
        this.setState({date: event.target.value});
      }

async handleSubmit(event) {
    event.preventDefault();
    const {item} = this.state;

    await fetch('/api/foodLogs' + (item.id ? '/' + item.id : ''), {
        method: (item.id) ? 'PUT' : 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(item),
    });
    this.props.history.push('/foodLogs');
}

    render() {
        const foodLogName = this.state.name;
        const foodLogDate = this.state.date;
        const title = <h2>{this.state.id ? 'Edit FoodLog' : 'Add FoodLog'}</h2>;

        return <div>
            <AppNavbar/>
            <Container>
                {title}
                <Form onSubmit={this.handleSubmit}>
                    <FormGroup>
                        <Label for="name">Name</Label>
                        <Input type="text" name="name" id="name" value={foodLogName || ''}
                               onChange={this.handleChange} placeholder="name"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="date">Date</Label>
                        <Input type="text" name="date" id="date" value={foodLogDate || ''}
                               onChange={this.handleDateChange} placeholder="date"/>
                    </FormGroup>
                    <FormGroup>
                        <Button color="primary" type="submit">Save</Button>{' '}
                        <Button color="secondary" tag={Link} to="/foodLogs">Cancel</Button>
                    </FormGroup>
                </Form>
            </Container>
        </div>
    }
}

export default withRouter(FoodLogEdit);