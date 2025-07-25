// src/components/Navbar.jsx
import React from 'react';
import { useNavigate } from 'react-router-dom';
import './Navbar.css'; // CSS file for styling

const Navbar = () => {
    const navigate = useNavigate(); // Hook for programmatic navigation

    return (
        <nav className="navbar">
            <div className="navbar-brand" onClick={() => navigate('/')}>
                <svg xmlns="http://www.w3.org/2000/svg" height="48px" viewBox="0 -960 960 960" width="48px" fill="#FFFFFF"><path d="M620-159 460-319l43-43 117 117 239-239 43 43-282 282Zm220-414h-60v-207h-60v90H240v-90h-60v600h251v60H180q-26 0-43-17t-17-43v-600q0-26 17-43t43-17h202q7-35 34.5-57.5T480-920q36 0 63.5 22.5T578-840h202q26 0 43 17t17 43v207ZM480-780q17 0 28.5-11.5T520-820q0-17-11.5-28.5T480-860q-17 0-28.5 11.5T440-820q0 17 11.5 28.5T480-780Z"/></svg>
                ToDoApp
            </div>
            <div className="navbar-links">
                <button onClick={() => navigate('/log')}>Login</button>
                <button onClick={() => navigate('/reg')}>Register</button>
            </div>
        </nav>
    );
};

export default Navbar;
