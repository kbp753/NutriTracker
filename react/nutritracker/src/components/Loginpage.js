import React, { useState, useContext } from 'react';
import TokenContext from './TokenContext'; // Adjust the path as needed
import { Link, useNavigate } from 'react-router-dom';

const LoginPage = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [errorMessage, setErrorMessage] = useState("");
  const {login } = useContext(TokenContext);
  const navigate = useNavigate();

  const handleUsernameChange = (e) => setUsername(e.target.value);
  const handlePasswordChange = (e) => setPassword(e.target.value);

  const handleSubmit = async (e) => {
    e.preventDefault();
    if(username==="" || password===""){
        setErrorMessage("Please enter username and password");
        return;
    }
    const newToken = await login(username, password)
    if(newToken===null)
        {
            window.alert("hello error")
            setErrorMessage("Login Failed")
        }
        else
        {
            // window.location.href = '/home';
            navigate('/home')
        }
  };

  return (
    <div className="container mt-5">
        <div>
    </div>
      <div className="row justify-content-center">
        <div className="col-md-6">
          <div className="card">
            <div className="card-body">
              <h5 className="card-title">Login</h5>
              <form onSubmit={handleSubmit}>
                <div className="form-group mt-5">
                  <label htmlFor="username">Username</label>
                  <input
                    type="text"
                    className="form-control"
                    id="username"
                    value={username}
                    onChange={handleUsernameChange}
                  />
                </div>
                <div className="form-group mt-2">
                  <label htmlFor="password">Password</label>
                  <input
                    type="password"
                    className="form-control"
                    id="password"
                    value={password}
                    onChange={handlePasswordChange}
                  />
                </div>
                <button type="submit" className="btn btn-primary btn-block mt-3">
                  Login
                </button>
                {errorMessage && <p className="text-danger mt-2">{errorMessage}</p>}
              </form>
              <div className="mt-3 text-center">
                <Link to="/signup" className="mx-2">Sign Up</Link>
                <span className="text-muted">|</span>
                <Link to="/reset-password" className="mx-2">Reset Password</Link>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default LoginPage;
