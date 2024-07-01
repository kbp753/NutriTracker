import React, { useState } from 'react';
import { Card, Table, Button, Modal, Form } from 'react-bootstrap';

const TodayLog = ({ foodEntries, onAddFood, onDeleteLog, onUpdateLog }) => {
    const [currentDate, setCurrentDate] = useState(new Date());
    const [showModal, setShowModal] = useState(false);
    const [selectedItem, setSelectedItem] = useState(null);
    const [updatedServingSize, setUpdatedServingSize] = useState('');
    const [servingSize, setServingSize]= useState()

    const handleUpdate = (entry) => {
        setServingSize(entry[1])
        setSelectedItem(entry);
        setUpdatedServingSize(entry[1]);
        setShowModal(true);
    };

    const handleDelete = (intakeId) => {
        console.log("delete called")
        onDeleteLog(intakeId);
    };

    const handleModalClose = () => {
        setShowModal(false);
        setSelectedItem(null);
        setUpdatedServingSize('');
    };

    const handleSaveChanges = () => {
        if(updatedServingSize==0)
            {
                handleDelete(selectedItem[2])
            }
            else
            {
        const updateProportion=updatedServingSize / servingSize;
        console.log("p:", updateProportion)
        onUpdateLog(selectedItem[2], updatedServingSize, updateProportion)
            }
        handleModalClose();
    };

    const foodList = Array.isArray(foodEntries) ? foodEntries : [];

    return (
        <>
            <Card className="mb-4">
            <Card.Header style={{ backgroundColor: '#64b064', color: 'white', fontWeight: 'bold' }}>Food Diary</Card.Header>
                <Card.Body>
                    <Table striped bordered hover responsive>
                        <thead>
                            <tr>
                                <th style={{ minWidth: '200px' }}>Food Name</th>
                                <th style={{ minWidth: '150px' }}>Serving Size</th>
                                <th style={{ minWidth: '150px' }}>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            {foodList.map((entry, index) => (
                                <tr key={index}>
                                    <td>{entry[0]}</td>
                                    <td>{entry[1]}</td>
                                    <td>
                                        <Button variant="outline-primary" size="sm" className="me-2" onClick={() => handleUpdate(entry)}>
                                            Update
                                        </Button>
                                        <Button variant="outline-danger" size="sm" onClick={() => handleDelete(entry[2])}>
                                            Delete
                                        </Button>
                                    </td>
                                </tr>
                            ))}
                        </tbody>
                    </Table>
                    <Button variant="primary" onClick={onAddFood} className="mt-3">
                        Add Food
                    </Button>
                </Card.Body>
            </Card>

            {selectedItem && (
                <Modal show={showModal} onHide={handleModalClose}>
                    <Modal.Header closeButton>
                        <Modal.Title>Update Food Entry</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <Form>
                            <Form.Group>
                                <Form.Label>Food Name</Form.Label>
                                <Form.Control type="text" readOnly value={selectedItem[0]} />
                            </Form.Group>
                            <Form.Group>
                                <Form.Label>Serving Size</Form.Label>
                                <Form.Control
                                    type="text"
                                    value={updatedServingSize}
                                    onChange={(e) => setUpdatedServingSize(e.target.value)}
                                />
                            </Form.Group>
                        </Form>
                    </Modal.Body>
                    <Modal.Footer>
                        <Button variant="secondary" onClick={handleModalClose}>
                            Close
                        </Button>
                        <Button variant="primary" onClick={handleSaveChanges}>
                            Save Changes
                        </Button>
                    </Modal.Footer>
                </Modal>
            )}
        </>
    );
};

export default TodayLog;
