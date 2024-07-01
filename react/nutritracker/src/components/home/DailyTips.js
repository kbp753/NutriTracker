// components/DailyTips.js
import React from 'react';
import { Card } from 'react-bootstrap';

const DailyTips = ({ tip }) => {
    return (
        <Card className="mt-4">
            <Card.Body>
                <Card.Title>Daily Health Tip</Card.Title>
                <Card.Text>{tip}</Card.Text>
            </Card.Body>
        </Card>
    );
};

export default DailyTips;
