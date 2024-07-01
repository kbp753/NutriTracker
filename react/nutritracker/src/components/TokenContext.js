import React, { createContext, useState } from 'react';
import axios from 'axios';

const TokenContext = createContext();

export const TokenProvider = ({ children }) => {
  const [isLoggedIn,setIsLoggedIn]=useState(localStorage.getItem('isLoggedIn')==='true')
  const [token, setToken] = useState(localStorage.getItem('token'));
  const [userId,setUserId]=useState(localStorage.getItem('userId'));
  const [loggedInUserName, setLoggedInUsername]=useState(localStorage.getItem('loggedInUserName'));

  // const [isLoggedIn,setIsLoggedIn]=useState(false)
  // const [token, setToken] = useState(null);
  // const [userId,setUserId]=useState(null);
  // const [loggedInUserName, setLoggedInUsername]=useState(null);
  // const [csrfToken, setCsrfToken] = useState('');

  // useEffect(() => {
  //   axios.get('http://localhost:8080/api/csrf/getCsrfToken').then(response => {
  //     setCsrfToken(response.data.token);
  //     console.log("csrf->",response)
  //   });
  // }, []);


  const logout=() =>{
    setIsLoggedIn(false);
    setToken(null)
    setUserId(null)
    setLoggedInUsername(null)
    localStorage.setItem('isLoggedIn', false);
    localStorage.setItem('token', null);
    localStorage.setItem('userId', null);
    localStorage.setItem('loggedInUserName', null);
  }

  const login = async (userName, password) => {
    let newToken = "Basic " + window.btoa(userName + ':' + password);
    try {
      const response = await axios.post('http://localhost:8080/login', new URLSearchParams({
        username: userName,
        password: password
      }), {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        }
      });
      console.log('response', response);
      if (response.status === 200) {
        setIsLoggedIn(true)
        setToken(newToken)
        setLoggedInUsername(userName)
        setUserId(response.data.userId)
        localStorage.setItem('token', newToken);
        localStorage.setItem('isLoggedIn', true);
        localStorage.setItem('loggedInUserName', userName);
        localStorage.setItem('userId', response.data.userId);
      }
    } catch (error) {
      console.error('Login failed:', error);
      newToken=null
    }
    return newToken;
  };

  const signupUser = async (formData) => {
    try {
      const response = await axios.post('http://localhost:8080/api/dailyIntake/create',
        formData
      );
      console.log('response -> data -> ', response.data);
      if (response.status === 200) {
        return response;
      }
    } catch (error) {
      console.error('logging food nutrition failed:', error);
      return 0;
    }
  };

  const searchByFoodName = async (foodName) => {
    try {
      const response = await axios.get('http://localhost:8080/api/foods/search', {
        params: {
          foodName: foodName,
          userId: userId
        },
        headers: {
          Authorization: token
        }
      });
      console.log('response -> data -> ', response.data);
      if (response.status === 200) {
        return response;
      }
    } catch (error) {
      console.error('search failed:', error);
      return 0;
    }
  };

  const getFoodNutritionDetails = async (food) => {
    try {
      const response = await axios.get('http://localhost:8080/api/foods/searchFoodNutrientById', {
        params: {
          fdcId: food.fdcId
        },
        headers: {
          Authorization: token
        }
      });
      console.log('response -> data -> ', response.data);
      if (response.status === 200) {
        return response;
      }
    } catch (error) {
      console.error('getting details failed:', error);
      return 0;
    }
  };

  const logFoodNutritionDetails = async (logEntry) => {
    try {
      const response = await axios.post('http://localhost:8080/api/dailyIntake/create',
        logEntry
      , {
        headers: {
          Authorization: token
        }
      });
      console.log('response -> data -> ', response.data);
      if (response.status === 200) {
        return response;
      }
    } catch (error) {
      console.error('logging food nutrition failed:', error);
      return 0;
    }
  };


  const getNutritionLogByDate = async (date) => {
    try {
      console.log("requested date-> ",date)
      const response = await axios.get('http://localhost:8080/api/dailyIntake/getLog', {
        params: {
          userId: userId,
          date:date
        },
        headers: {
          Authorization: token
        }
      });
      console.log('response -> data -> ', response.data);
      if (response.status === 200) {
        return response;
      }
      else{
        return null;
      }
    } catch (error) {
      console.error('getting nutrition log details failed:', error);
      return null;
    }
  };

  const getFoodByDate = async (date) => {
    try {
      const response = await axios.get('http://localhost:8080/api/dailyIntake/getFoodByDate', {
        params: {
          userId: userId,
          date:date
        },
        headers: {
          Authorization: token
        }
      });
      console.log('response -> data -> ', response.data);
      if (response.status === 200) {
        return response;
      }
      else{
        return null;
      }
    } catch (error) {
      console.error('getting Food log details failed:', error);
      return null;
    }
  };

  const deleteLogById = async (intakeId) => {
    try {
      const response = await axios.delete('http://localhost:8080/api/dailyIntake/deleteLog', {
        params: {
          intakeId:intakeId
        },
        headers: {
          Authorization: token
        }
      });
      console.log('response -> data -> ', response.data);
      if (response.status === 200) {
        return response;
      }
      else{
        return null;
      }
    } catch (error) {
      console.error('deleting Food log details failed:', error);
      return null;
    }
  };

  const updateLogById = async (intakeId, quantity, updateProportion) => {
    try {
      console.log("intakeId ",intakeId,"quantity ",quantity, "updated proportion ",updateProportion)
      const response = await axios.post('http://localhost:8080/api/dailyIntake/updateLog', 
          {
              intakeId: intakeId,
              quantity: quantity,
              updateProportion: updateProportion
          },
          {
              headers: {
                  Authorization: token
              }
          }
      );
      console.log('response -> data -> ', response.data);
      if (response.status === 200) {
          return response;
      } else {
          return null;
      }
  } catch (error) {
      console.error('Updating Food log details failed:', error);
      return null;
  }
  
  };

  const getAvailableNutrients = async (query) => {
    try {
      const response = await axios.get('http://localhost:8080/api/foods/getAllNutrients', { 
        params:{
          searchTerm: query
        },
        headers: {
          Authorization: token
        }
      });
      console.log('response -> data -> ', response.data);
      if (response.status === 200) {
        return response;
      }
      else{
        return null;
      }
    } catch (error) {
      console.error('deleting Food log details failed:', error);
      return null;
    }
  };

  const addCustomFood = async (newFood) => {
    try {
      newFood.userId=parseInt(userId);
      console.log("new food-> ",newFood)
      const response = await axios.post('http://localhost:8080/api/foods/addCustomFood',
        newFood
      , {
        headers: {
          Authorization: token
        }
      });
      console.log('response -> data -> ', response.data);
      if (response.status === 200) {
        return response;
      }
    } catch (error) {
      console.error('logging food nutrition failed:', error);
      return 0;
    }
  };


  return (
    <TokenContext.Provider value={{ login, signupUser, logout, isLoggedIn, searchByFoodName, getFoodNutritionDetails, logFoodNutritionDetails, getNutritionLogByDate, getFoodByDate, deleteLogById, updateLogById, getAvailableNutrients, addCustomFood }}>
      {children}
    </TokenContext.Provider>
  );
};

export default TokenContext;
