import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';

class FoodLogList extends Component {

    constructor(props) {
        super(props);
        this.state = {foodLogs: []};
        this.remove = this.remove.bind(this);
    }

    componentDidMount() {
        fetch('/api/foodLogs')
              
        .then(response => response.json())
        .then(data => this.setState({foodLogs: data}));
    }

    async remove(id) {
        await fetch(`/api/foodLogs/${id}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            let updatedFoodLogs = [...this.state.foodLogs].filter(i => i.id !== id);
            this.setState({foodLogs: updatedFoodLogs});
        });
    }

    render() {
        const {foodLogs} = this.state;

        const foodLogList = foodLogs.map(foodLog => {
            return <tr key={foodLog.id}>
                <td style={{whiteSpace: 'nowrap'}}>{foodLog.date}</td>
                <td>{foodLog.recipe.name}</td>
                <td>
                    <ButtonGroup>
                        <Button size="sm" color="primary" tag={Link} to={"/foodLogs/" + foodLog.id}>Edit</Button>
                        <Button size="sm" color="danger" onClick={() => this.remove(foodLog.id)}>Delete</Button>
                    </ButtonGroup>
                </td>
            </tr>
        });

        return (
            <div>
                <AppNavbar/>
                <Container fluid>
                    <div className="float-right">
                        <Button color="success" tag={Link} to="/foodLogs/new">Add Food Log</Button>
                    </div>
                    <h3>Food Logs</h3>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="30%">Date</th>
                            <th width="30%">Food Name</th>
                            <th width="40%">Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        {foodLogList}
                        </tbody>
                    </Table>
                </Container>
            </div>
        );
    }
}

export default FoodLogList;