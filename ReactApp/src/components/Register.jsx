// src/components/Register.jsx

import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
function Register() {
    const nav = useNavigate();
    // Hook to store form input values
    const [form, setForm] = useState({
        username: "",
        email: "",
        password: ""
    });

    // Handles input changes
    const handleChange = (e) => {
        setForm({ ...form, [e.target.name]: e.target.value });
    };

    // Handles form submission
    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const res = await axios.post('http://localhost:8080/api/user/register', form);
            console.log(res.data);
            alert(res.data);
            nav("/log")
        } catch (err) {
            console.error(err);
            alert(err.response?.data || "Something went wrong");
        }
    };

    return (
        <>
            <h1>Welcome to Register page</h1>
            <p>Name: {form.username}</p>
            <p>Email: {form.email}</p>
            <p>Password: {form.password}</p>

            <form onSubmit={handleSubmit} autoComplete="on">
                <input
                    onChange={handleChange}
                    type="text"
                    name="username"
                    placeholder="Enter username"
                    autoComplete="username"
                    required
                />
                <br />

                <input
                    onChange={handleChange}
                    type="email"
                    name="email"
                    placeholder="Enter email"
                    autoComplete="email"
                    required
                />
                <br />

                <input
                    onChange={handleChange}
                    type="password"
                    name="password"
                    placeholder="Enter password"
                    autoComplete="new-password"
                    required
                />
                <br />

                <button type="submit">Register</button>
            </form>
        </>
    );
}

export default Register;
