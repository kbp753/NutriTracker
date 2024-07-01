// components/Header.js
import React, { useContext } from 'react';
import { Navbar, Nav, NavDropdown, Container } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';
import TokenContext from './TokenContext';
import '../css/Header.css';

const Header = () => {
    const navigate = useNavigate();
    const { logout, isLoggedIn } = useContext(TokenContext);

    const handleLogout = async () => {
        await logout();
        navigate('/');
    };

    return (
        <Navbar expand="lg" variant="dark" className="navbar-custom">
            <Container>
                <Navbar.Brand className="navbar-brand-custom" onClick={() => navigate('/')}>Nutritracker</Navbar.Brand>
                <Navbar.Toggle aria-controls="basic-navbar-nav" />
                <Navbar.Collapse id="basic-navbar-nav">
                    <Nav className="me-auto">
                    {isLoggedIn && (<>
                        <Nav.Link className="nav-link-custom" onClick={() => navigate('/')}>Home</Nav.Link>
                        <Nav.Link className="nav-link-custom" onClick={() => navigate('/search')}>Add Food Log</Nav.Link>
                        <Nav.Link className="nav-link-custom" onClick={() => navigate('/addCustomFood')}>Add Custom Foods</Nav.Link></>
                        )}
                    </Nav>
                    <Nav>
                        {isLoggedIn ? (
                            <NavDropdown title="Profile" id="basic-nav-dropdown" className="nav-dropdown-custom">
                                <NavDropdown.Item onClick={() => navigate('/profile')} className="dropdown-item-custom">Profile</NavDropdown.Item>
                                <NavDropdown.Item onClick={handleLogout} className="dropdown-item-custom">Logout</NavDropdown.Item>
                            </NavDropdown>
                        ) : (
                            <Nav.Link onClick={() => navigate('/login')} className="nav-link-custom">Login</Nav.Link>
                        )}
                    </Nav>
                </Navbar.Collapse>
            </Container>
        </Navbar>
    );
};

export default Header;
