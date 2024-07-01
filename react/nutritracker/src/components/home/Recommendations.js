import React from 'react';
import { Card, Button } from 'react-bootstrap';

const Recommendations = ({ mealSuggestions }) => {
    return (
        <Card className="mt-4">
            <Card.Body>
                <Card.Title>Meal Suggestions</Card.Title>
                <Card.Text>Check out these meals based on your preferences!</Card.Text>
                <Button variant="link">View Recipes</Button>
            </Card.Body>
        </Card>
    );
};

export default Recommendations;
