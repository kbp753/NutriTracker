import React, { useContext } from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import LoginPage from './components/Loginpage';
import Header from './components/Header';
import Footer from './components/Footer';
import HomePage from './components/HomePage';
import TokenContext, { TokenProvider } from './components/TokenContext'; // Ensure correct import path
import 'bootstrap/dist/css/bootstrap.min.css';
import Search from './components/search/Search';
import AddCustomFood from './components/custom_food/AddCustomFood';
import SignupForm from './components/SignupForm';

function App() {
  return (
    <TokenProvider>
      <Router>
        <div>
          <Header />
          <div><br /></div>
          <Routes>
            <Route path="/" element={<LoginRoute />} />
            <Route path="/signup" element={<SignupForm/>} />
            <Route path="/home" element={<AuthenticateRoute><HomePage /></AuthenticateRoute>} />
            <Route path="/search" element={<AuthenticateRoute><Search /></AuthenticateRoute>} />
            <Route path="/addCustomFood" element={<AuthenticateRoute><AddCustomFood /></AuthenticateRoute>} />
            <Route path="*" element={<Navigate to="/" />} />
          </Routes>
          
        </div>
      </Router>
    </TokenProvider>
  );
}

function LoginRoute() {
  const { isLoggedIn } = useContext(TokenContext);
  console.log("Login route is logged in -> ",isLoggedIn)
  return isLoggedIn ? <Navigate to="/home" /> : <LoginPage />;
}

function AuthenticateRoute({ children }) {
  const { isLoggedIn } = useContext(TokenContext);
  console.log("is logged ->", isLoggedIn);
  return isLoggedIn ? children : <Navigate to="/" />;
}

export default App;
