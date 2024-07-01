import { useState, useContext } from "react";
import TokenContext from './TokenContext';
import { useNavigate } from 'react-router-dom';

const Home=()=>{
    const navigate = useNavigate();
    const { logout, searchByFoodName } = useContext(TokenContext);
    const [foodInput,setFoodInput]=useState("")
    const handleFoodInputChange = (e) => setFoodInput(e.target.value);
    const searchFood = ()=>{
        searchByFoodName(foodInput)
    }

    const handleLogout = ()=>{
        logout()
        navigate('/')
    }
    return(
        <div>
            <div>
            <label>Food name</label>
            <input type="text" className="foodInput" value={foodInput} onChange={handleFoodInputChange}/>
            <button type="submit" className="searchFood" onClick={searchFood}>search</button>
            </div>
            <button onClick={handleLogout}>Logout</button>
        </div>
    )
}

export default Home;