import React from 'react';
import { Card, ProgressBar, ListGroup } from 'react-bootstrap';

const DailySummary = ({ calories, protein, carbs, fats }) => {
    return (
        <Card>
            <Card.Body>
                <Card.Title>Today's Summary</Card.Title>
                <ProgressBar now={(calories / 2000) * 100} label={`${calories} of 2000 kcal`} />
                <ListGroup variant="flush" className="mt-3">
                    <ListGroup.Item>Protein: {protein}g</ListGroup.Item>
                    <ListGroup.Item>Carbs: {carbs}g</ListGroup.Item>
                    <ListGroup.Item>Fats: {fats}g</ListGroup.Item>
                </ListGroup>
            </Card.Body>
        </Card>
    );
};

export default DailySummary;
