import React, { useState, useContext, useEffect, useCallback } from 'react';
import { Container, Button, Spinner, Alert, FormControl, InputGroup, Modal } from 'react-bootstrap';
import SearchResults from './SearchResults';  // Ensure the path is correct
import FoodDetails from './FoodDetails';  // Ensure the path is correct
import TokenContext from '../TokenContext';
import { useNavigate } from 'react-router-dom';
import _ from 'lodash';

const Search = ({ onAddFood }) => {
    const navigate = useNavigate();
    const [searchResults, setSearchResults] = useState([]);
    const [selectedFoodNutrition, setSelectedFoodNutrition] = useState(null);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState('');
    const [searchTerm, setSearchTerm] = useState('');
    const [showFoodDetails, setShowFoodDetails] = useState(false); // State for modal
    const { searchByFoodName, getFoodNutritionDetails, logFoodNutritionDetails } = useContext(TokenContext);
    const [successMessage, setSuccessMessage] = useState('');

    useEffect(() => {
        if (successMessage) {
            const timer = setTimeout(() => {
                setSuccessMessage('');
            }, 3000);

            return () => clearTimeout(timer);
        }
    }, [successMessage]);

    const handleFoodSearch = (event) => {
        const searchTerm = event.target.value;
        setSearchTerm(searchTerm);
        debounceFetchFood(searchTerm);
      };

      const debounceFetchFood = useCallback(
        _.debounce((query) => {
            handleSearch(query);
        }, 500), // Adjust the delay as needed
        []
      );

    const handleSearch = async (query) => {
        setError('');
        if (query.length >= 3) {
            setLoading(true);
            try {
                const searchResponse = await searchByFoodName(query);
                if (searchResponse.data.length === 0) {
                    setError('No results found');
                } else {
                    setSearchResults(searchResponse.data);
                }
            } catch (err) {
                setError('Search failed. Please try again.');
            } finally {
                setLoading(false);
            }
        } else {
            setError('Please enter at least 3 characters');
        }
    };

    const handleSelectResult = async (food) => {
        setLoading(true);
        setError('');
        try {
            const foodNutritionDetailsResponse = await getFoodNutritionDetails(food);
            console.log("selected food->",food)
            setSelectedFoodNutrition({ name: food.foodName, nutrients: foodNutritionDetailsResponse.data, fdcId: food.fdcId });
            setShowFoodDetails(true); // Show modal on selection
        } catch (err) {
            setError('Failed to fetch food details. Please try again.');
        } finally {
            setLoading(false);
        }
    };

    const handleAddFood = async (servingSize) => {
        setLoading(true);
        setError('');
        try {
            const adjustedNutrients = selectedFoodNutrition.nutrients.map(nutrient => {
                const [nutrientName, amount, nutrientUnitName, percentDailyValue, nutrientId] = nutrient;
              
                return {
                  nutrientId: nutrientId,
                  amount: amount * servingSize, // Multiply amount by servingSize
                  dailyPercentage: percentDailyValue * servingSize, // Multiply percentDailyValue by servingSize
                };
              });
              
              const logEntry = {
                userId: localStorage.getItem("userId"),
                fdcId: selectedFoodNutrition.fdcId,
                date: new Date().toISOString().split('T')[0],
                quantity: parseFloat(servingSize),
                adjustedNutrients: adjustedNutrients
              };
console.log("Log entry -> ",logEntry);
            const foodLogAddResponse = await logFoodNutritionDetails(logEntry);
            if (foodLogAddResponse.status !== 200) {
                setError('Failed to add log details. Please try again.');
            } else {
                setSuccessMessage('Successfully added to your log.');
                setShowFoodDetails(false); // Close FoodDetails popup
            }
        } catch (err) {
            setError('Failed to add log details. Please try again.');
        } finally {
            setLoading(false);
        }
    };

    const handleClose = () => setShowFoodDetails(false);
    const handleSuccessDismiss = () => {
        setSuccessMessage(''); // Clear success message
    };

    return (
        <Container className="py-4">
            <h3>Search Food</h3>
            <InputGroup className="mb-3">
                <FormControl
                    placeholder="Search for food"
                    value={searchTerm}
                    onChange={(event) => {
                        handleFoodSearch(event);
                      }} 
                />
                
            </InputGroup>
            {loading && <Spinner animation="border" />}
            {error && <Alert variant="danger">{error}</Alert>}
            {successMessage && (
                <Alert variant="success" onClose={handleSuccessDismiss} dismissible>
                    {successMessage}
                </Alert>
            )}
            {searchResults.length > 0 && (
                <SearchResults results={searchResults} onSelect={handleSelectResult} />
            )}
            <Modal show={showFoodDetails} onHide={handleClose}>
                <Modal.Header closeButton>
                    <Modal.Title>{selectedFoodNutrition && selectedFoodNutrition.name}</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    {selectedFoodNutrition && (
                        <FoodDetails foodItem={selectedFoodNutrition} onAdd={handleAddFood} />
                    )}
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleClose}>Close</Button>
                </Modal.Footer>
            </Modal>
            <Button variant="secondary" onClick={() => navigate('/home')}>Back</Button>
        </Container>
    );
};

export default Search;
