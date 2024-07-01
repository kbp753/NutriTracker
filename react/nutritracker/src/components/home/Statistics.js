import React from 'react';
import { Table, Card, ProgressBar } from 'react-bootstrap';
import { GiFruitBowl, GiCarrot } from 'react-icons/gi'; // Example icons for nutrients

const Statistics = ({ nutrients }) => {
    return (
        <Card className="mb-4" style={{ borderColor: '#64b064' }}>
            <Card.Header style={{ backgroundColor: '#64b064', color: 'white', fontWeight: 'bold' }}>Statistics</Card.Header>
            <Card.Body>
                <Table responsive striped bordered hover>
                    <thead>
                        <tr style={{ backgroundColor: '#f3f3f3' }}>
                            <th>Nutrient</th>
                            <th>Total Amount</th>
                            <th>% Daily Value</th>
                            <th>Progress</th>
                        </tr>
                    </thead>
                    <tbody>
                        {nutrients.map((nutrient, index) => (
                            <tr key={index}>
                                <td className="fw-bold">
                                    <div className="d-flex align-items-center">
                                        {nutrient.nutrientName === 'Vitamin C' && <GiFruitBowl style={{ fontSize: '24px', marginRight: '10px' }} />}
                                        {nutrient.nutrientName === 'Vitamin A' && <GiCarrot style={{ fontSize: '24px', marginRight: '10px' }} />}
                                        {nutrient.nutrientName}
                                    </div>
                                </td>
                                <td>{nutrient.totalAmount} {nutrient.nutrientUnitName}</td>
                                <td>{nutrient.totalPercentDailyValue}%</td>
                                <td>
                                    <ProgressBar now={nutrient.totalPercentDailyValue} label={`${nutrient.totalPercentDailyValue}%`} variant="success" />
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </Table>
            </Card.Body>
        </Card>
    );
};

export default Statistics;
