import '../css/SignupForm.css'; // Import custom CSS for additional styling
import React, { useState, useContext } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import TokenContext from './TokenContext';

const SignupForm = () => {
    const { signupUser } = useContext(TokenContext);
    const [formData, setFormData] = useState({
        firstName: '',
        lastName: '',
        email: '',
        username: '',
        password: '',
        confirmPassword: '',
        gender: '',
        height: '',
        weight: '',
        dateOfBirth: ''
    });

    const [errors, setErrors] = useState({});

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({
            ...formData,
            [name]: value
        });
        // Clear validation error once user starts typing again
        if (errors[name]) {
            setErrors({
                ...errors,
                [name]: ''
            });
        }
    };

    const validateForm = () => {
        const newErrors = {};

        if (!formData.firstName.trim()) newErrors.firstName = 'First name is required';
        if (!formData.lastName.trim()) newErrors.lastName = 'Last name is required';
        if (!formData.email.trim()) newErrors.email = 'Email is required';
        if (!/\S+@\S+\.\S+/.test(formData.email)) newErrors.email = 'Email is invalid';
        if (!formData.username.trim()) newErrors.username = 'Username is required';
        if (!formData.password) newErrors.password = 'Password is required';
        if (formData.password !== formData.confirmPassword) newErrors.confirmPassword = 'Passwords do not match';
        if (!formData.gender) newErrors.gender = 'Gender is required';
        if (!formData.height || isNaN(formData.height)) newErrors.height = 'Height is required and must be a number';
        if (!formData.weight || isNaN(formData.weight)) newErrors.weight = 'Weight is required and must be a number';
        if (!formData.dateOfBirth) newErrors.dateOfBirth = 'Date of birth is required';

        setErrors(newErrors);
        return Object.keys(newErrors).length === 0;
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (validateForm()) {
            console.log('Form data submitted:', formData);
            // Simulate form submission or send data to backend
            const response=signupUser(formData);
            if(response===null)
                {
            alert('Form submitted successfully!');
            // Reset form after successful submission
            setFormData({
                firstName: '',
                lastName: '',
                email: '',
                username: '',
                password: '',
                confirmPassword: '',
                gender: '',
                height: '',
                weight: '',
                dateOfBirth: ''
            });
            setErrors({});
        }
        }
    };

    return (
        <div className="container py-5">
            <div className="row justify-content-center">
                <div className="col-lg-8">
                    <div className="card shadow p-4 rounded">
                        <h2 className="text-center mb-4">Registration Form</h2>
                        <form onSubmit={handleSubmit}>
                            {/* Personal Information Section */}
                            <div className="row mb-3">
                                <div className="col-md-6">
                                    <div className="form-group">
                                        <label htmlFor="firstName">First Name</label>
                                        <input
                                            type="text"
                                            className={`form-control ${errors.firstName ? 'is-invalid' : ''}`}
                                            id="firstName"
                                            name="firstName"
                                            value={formData.firstName}
                                            onChange={handleChange}
                                            placeholder="Enter your first name"
                                            required
                                        />
                                        {errors.firstName && <div className="invalid-feedback">{errors.firstName}</div>}
                                    </div>
                                </div>
                                <div className="col-md-6">
                                    <div className="form-group">
                                        <label htmlFor="lastName">Last Name</label>
                                        <input
                                            type="text"
                                            className={`form-control ${errors.lastName ? 'is-invalid' : ''}`}
                                            id="lastName"
                                            name="lastName"
                                            value={formData.lastName}
                                            onChange={handleChange}
                                            placeholder="Enter your last name"
                                            required
                                        />
                                        {errors.lastName && <div className="invalid-feedback">{errors.lastName}</div>}
                                    </div>
                                </div>
                            </div>
                            
                            {/* Account Information Section */}
                            <div className="row mb-3">
                                <div className="col-md-6">
                                    <div className="form-group">
                                        <label htmlFor="email">Email Address</label>
                                        <input
                                            type="email"
                                            className={`form-control ${errors.email ? 'is-invalid' : ''}`}
                                            id="email"
                                            name="email"
                                            value={formData.email}
                                            onChange={handleChange}
                                            placeholder="Enter your email address"
                                            required
                                        />
                                        {errors.email && <div className="invalid-feedback">{errors.email}</div>}
                                    </div>
                                </div>
                                <div className="col-md-6">
                                    <div className="form-group">
                                        <label htmlFor="username">Username</label>
                                        <input
                                            type="text"
                                            className={`form-control ${errors.username ? 'is-invalid' : ''}`}
                                            id="username"
                                            name="username"
                                            value={formData.username}
                                            onChange={handleChange}
                                            placeholder="Choose a username"
                                            required
                                        />
                                        {errors.username && <div className="invalid-feedback">{errors.username}</div>}
                                    </div>
                                </div>
                            </div>
                            
                            {/* Password Section */}
                            <div className="row mb-3">
                                <div className="col-md-6">
                                    <div className="form-group">
                                        <label htmlFor="password">Password</label>
                                        <input
                                            type="password"
                                            className={`form-control ${errors.password ? 'is-invalid' : ''}`}
                                            id="password"
                                            name="password"
                                            value={formData.password}
                                            onChange={handleChange}
                                            placeholder="Enter a password"
                                            required
                                        />
                                        {errors.password && <div className="invalid-feedback">{errors.password}</div>}
                                    </div>
                                </div>
                                <div className="col-md-6">
                                    <div className="form-group">
                                        <label htmlFor="confirmPassword">Confirm Password</label>
                                        <input
                                            type="password"
                                            className={`form-control ${errors.confirmPassword ? 'is-invalid' : ''}`}
                                            id="confirmPassword"
                                            name="confirmPassword"
                                            value={formData.confirmPassword}
                                            onChange={handleChange}
                                            placeholder="Confirm your password"
                                            required
                                        />
                                        {errors.confirmPassword && <div className="invalid-feedback">{errors.confirmPassword}</div>}
                                    </div>
                                </div>
                            </div>
                            
                            {/* Additional Information Section */}
                            <div className="row mb-3">
                                <div className="col-md-6">
                                    <div className="form-group">
                                        <label htmlFor="gender">Gender</label>
                                        <select
                                            className={`form-control ${errors.gender ? 'is-invalid' : ''}`}
                                            id="gender"
                                            name="gender"
                                            value={formData.gender}
                                            onChange={handleChange}
                                            required
                                        >
                                            <option value="">Select your gender</option>
                                            <option value="Male">Male</option>
                                            <option value="Female">Female</option>
                                            <option value="Other">Other</option>
                                        </select>
                                        {errors.gender && <div className="invalid-feedback">{errors.gender}</div>}
                                    </div>
                                </div>
                                <div className="col-md-6">
                                    <div className="form-group">
                                        <label htmlFor="dateOfBirth">Date of Birth</label>
                                        <input
                                            type="date"
                                            className={`form-control ${errors.dateOfBirth ? 'is-invalid' : ''}`}
                                            id="dateOfBirth"
                                            name="dateOfBirth"
                                            value={formData.dateOfBirth}
                                            onChange={handleChange}
                                            required
                                        />
                                        {errors.dateOfBirth && <div className="invalid-feedback">{errors.dateOfBirth}</div>}
                                    </div>
                                </div>
                            </div>
                            
                            {/* Physical Information Section */}
                            <div className="row mb-3">
                                <div className="col-md-6">
                                    <div className="form-group">
                                        <label htmlFor="height">Height (cm)</label>
                                        <input
                                            type="number"
                                            className={`form-control ${errors.height ? 'is-invalid' : ''}`}
                                            id="height"
                                            name="height"
                                            value={formData.height}
                                            onChange={handleChange}
                                            placeholder="Enter your height in centimeters"
                                            required
                                        />
                                        {errors.height && <div className="invalid-feedback">{errors.height}</div>}
                                    </div>
                                </div>
                                <div className="col-md-6">
                                    <div className="form-group">
                                        <label htmlFor="weight">Weight (kg)</label>
                                        <input
                                            type="number"
                                            className={`form-control ${errors.weight ? 'is-invalid' : ''}`}
                                            id="weight"
                                            name="weight"
                                            value={formData.weight}
                                            onChange={handleChange}
                                            placeholder="Enter your weight in kilograms"
                                            required
                                        />
                                        {errors.weight && <div className="invalid-feedback">{errors.weight}</div>}
                                    </div>
                                </div>
                            </div>
                            
                            {/* Submit Button */}
                            <div className="text-center mt-4">
                                <button type="submit" className="btn btn-success btn-lg px-5">Register</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default SignupForm;
