// components/SearchResults.js
import React from 'react';
import { ListGroup } from 'react-bootstrap';

const SearchResults = ({ results, onSelect }) => {
    return (
        <ListGroup className="mb-3">
            {results.map((result, index) => (
                <ListGroup.Item
                    key={index}
                    action
                    onClick={() => onSelect(result)}
                >
                    {result.foodName}
                </ListGroup.Item>
            ))}
        </ListGroup>
    );
};

export default SearchResults;
