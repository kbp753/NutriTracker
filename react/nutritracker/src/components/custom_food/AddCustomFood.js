import React, { useState, useEffect, useContext, useCallback } from 'react';
import { Form, Button, Table, Badge, ListGroup } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import TokenContext from '../TokenContext';
import _ from 'lodash';

const AddCustomFood = () => {
  const { getAvailableNutrients, addCustomFood } = useContext(TokenContext);
  const [foodName, setFoodName] = useState('');
  const [filteredNutrients, setFilteredNutrients] = useState([]);
  const [addedNutrients, setAddedNutrients] = useState([]);
  const [errors, setErrors] = useState({});
  const [searchTerm, setSearchTerm] = useState('');

  useEffect(() => {
    fetchAvailableNutrients('');
  }, []);

  const fetchAvailableNutrients = async (query) => {
    console.log('Fetching nutrients with query:', query); // Debugging line
    const response = await getAvailableNutrients(query);
    console.log('Response:', response); // Debugging line
    if (response !== null) {
      setFilteredNutrients(response.data);
    }
  };

  const debounceFetchAvailableNutrients = useCallback(
    _.debounce((query) => {
      fetchAvailableNutrients(query);
    }, 1000), // Adjust the delay as needed
    []
  );

  const handleRemoveNutrient = (index) => {
    const updatedNutrients = [...addedNutrients];
    updatedNutrients.splice(index, 1);
    setAddedNutrients(updatedNutrients);
  };

  const handleSaveFood = async (event) => {
    console.log("save called")
    event.preventDefault();
    const formErrors = validateForm();
    if (Object.keys(formErrors).length === 0) {
      const newFood = {
        foodName: foodName,
        nutrientsList: addedNutrients,
        userId:0
      };
      const response = await addCustomFood(newFood);
      if (response != null) {
        console.log('Saving food:', newFood);
        setFoodName('');
        setAddedNutrients([]);
        setErrors({});
        setSearchTerm('');
      }
    } else {
      setErrors(formErrors);
    }
  };

  const validateForm = () => {
    let errors = {};
    if (!foodName.trim()) {
      errors.foodName = 'Food name is required';
    }
    if (addedNutrients.length === 0) {
      errors.nutrients = 'At least one nutrient must be added';
    }
    return errors;
  };

  const handleNutrientSearch = (event) => {
    const searchTerm = event.target.value;
    console.log("Search term:", searchTerm); // Debugging line
    setSearchTerm(searchTerm);
    debounceFetchAvailableNutrients(searchTerm);
  };

  const handleNutrientSelect = (event, nutrient) => {
    event.preventDefault(); // Prevent form submission
    console.log("nutrient selected->", nutrient)
    const nutrientExists = addedNutrients.some(addedNutrient => addedNutrient.id === nutrient.id);
    if (!nutrientExists) {
      setAddedNutrients([...addedNutrients, nutrient]);
    }
  };

  return (
    <div className="container mt-5">
      <h2 style={{ color: 'green' }}>Add Custom Food</h2>
      <Form onSubmit={handleSaveFood}>
        <Form.Group controlId="foodName">
          <Form.Label>Food Name</Form.Label>
          <Form.Control
            type="text"
            placeholder="Enter food name"
            value={foodName}
            onChange={(e) => setFoodName(e.target.value)}
            isInvalid={!!errors.foodName}
            required
          />
          <Form.Control.Feedback type="invalid">
            {errors.foodName}
          </Form.Control.Feedback>
        </Form.Group>

        <Form.Group controlId="nutrientSearch">
          <Form.Label>Select Nutrient</Form.Label>
          <Form.Control 
            type="text" 
            placeholder="Search for nutrient..." 
            value={searchTerm} 
            onChange={(event) => {
              console.log("Input change event:", event.target.value); // Debugging line
              handleNutrientSearch(event);
            }} 
          />
          <ListGroup className="mb-3">
            {filteredNutrients.map((nutrient, index) => (
              <ListGroup.Item
                key={nutrient.id}
                action
                onClick={(event) => handleNutrientSelect(event, nutrient)}
              >
                {nutrient.name}
              </ListGroup.Item>
            ))}
          </ListGroup>
        </Form.Group>

        <Table striped bordered hover className="mt-4">
          <thead>
            <tr>
              <th>Nutrient Name</th>
              <th>Amount</th>
              <th>Daily Value %</th>
              <th></th>
            </tr>
          </thead>
          <tbody>
            {addedNutrients.map((nutrient, index) => (
              <tr key={index}>
                <td>{nutrient.name}</td>
                <td>
                  <Form.Control
                    type="number"
                    size="sm"
                    required
                    placeholder="Enter amount"
                    value={nutrient.amount || ''}
                    onChange={(e) => {
                      const updatedNutrients = [...addedNutrients];
                      updatedNutrients[index].amount = parseFloat(e.target.value);
                      setAddedNutrients(updatedNutrients);
                    }}
                  />
                </td>
                <td>
                  <Form.Control
                    type="number"
                    size="sm"
                    required
                    placeholder="Enter amount"
                    value={nutrient.dailyPercentage || ''}
                    onChange={(e) => {
                      const updatedNutrients = [...addedNutrients];
                      updatedNutrients[index].dailyPercentage = parseFloat(e.target.value);
                      setAddedNutrients(updatedNutrients);
                    }}
                  />
                </td>
                <td>
                  <Button variant="link" onClick={() => handleRemoveNutrient(index)}>
                    <Badge pill bg="danger">
                      X
                    </Badge>
                  </Button>
                </td>
              </tr>
            ))}
          </tbody>
        </Table>

        <Button variant="primary" type="submit" className="mt-3">
          Save Food
        </Button>
      </Form>
    </div>
  );
};

export default AddCustomFood;
