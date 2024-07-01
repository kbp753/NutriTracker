import React, { useState } from 'react';
import { Card, Button, Form, Row, Col, Table } from 'react-bootstrap';

const FoodDetails = ({ foodItem, onAdd }) => {
    const [servingSize, setServingSize] = useState(1);

    const handleAddClick = () => {
            onAdd(servingSize);
    };

    return (
        <Card>
            <Card.Body>
                {/* <Card.Title>{foodItem.name}</Card.Title> */}
                <Card.Text as="div">
                    <Table striped bordered hover>
                        <thead>
                            <tr>
                                <th>Nutrient</th>
                                <th>Amount</th>
                                <th>Daily %</th>
                            </tr>
                        </thead>
                        <tbody>
                            {foodItem.nutrients.map((nutrient, index) => (
                                <tr key={index}>
                                    <td>{nutrient[0]}</td>
                                    <td>{nutrient[1] +" " +nutrient[2]}</td>
                                    <td>{nutrient[3] !== null ? `${nutrient[3]}%` : 'N/A'}</td>
                                </tr>
                            ))}
                        </tbody>
                    </Table>
                </Card.Text>
                <Form>
                    <Form.Group as={Row} controlId="formServingSize">
                        <Form.Label column sm="4">Serving Size:</Form.Label>
                        <Col sm="8">
                            <Form.Control
                                type="number"
                                placeholder="Enter serving size"
                                value={servingSize}
                                onChange={(e) => setServingSize(e.target.value)}
                            />
                        </Col>
                    </Form.Group>
                    <Button variant="primary" onClick={handleAddClick}>Add to Log</Button>
                </Form>
            </Card.Body>
        </Card>
    );
};

export default FoodDetails;
