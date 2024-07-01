// components/WelcomeSection.js
import React from 'react';

const Welcome = ({ userName }) => {
    return (
        <div className="mt-4">
            <h2>Good Morning, {userName}!</h2>
            <p>Here is a summary of your nutrition for today.</p>
        </div>
    );
};

export default Welcome;
