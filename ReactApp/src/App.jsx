// src/App.jsx

import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Home from './components/Home';
import Register from './components/Register';
import Login from './components/Login';
import Admin from './components/Admin';
import Navbar from './components/Navbar';
import Footer from './components/Footer';

import './App.css'; // Importing global styles
import TaskManager from './components/TaskManager';

function App() {
  return (
    <div className="app-container">
      <BrowserRouter>
        {/* Top navigation bar */}
        {/* <Navbar /> */}

        {/* Main content area */}
        <div className="content-wrapper">
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/reg" element={<Register />} />
            <Route path="/log" element={<Login />} />
            <Route path="/adm" element={<Admin />} />
            <Route path="/tskm" element={<TaskManager />} />
          </Routes>
        </div>

        {/* Footer at the bottom */}
        {/* <Footer /> */}
      </BrowserRouter>
    </div>
  );
}

export default App;
