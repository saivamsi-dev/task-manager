// import { useState } from "react";
// import axios from "axios";
// import { useNavigate } from "react-router-dom";

// function Login() {
//     const navigate = useNavigate(); // For navigating after successful login

//     // State to store form input
//     const [form, setForm] = useState({
//         email: "",
//         password: ""
//     });

//     // Handle input changes
//     const handleChange = (e) => {
//         setForm({ ...form, [e.target.name]: e.target.value });
//     };

//     // Handle form submission
//     const handleSubmit = async (e) => {
//         e.preventDefault(); // Prevent default form reload

//         try {
//             const res = await axios.post("http://localhost:8080/api/user/login", form, {
//                 withCredentials: true
//             });

//             console.log(res.data);
//             alert(res.data);

//             if (res.data === "Login Success") {
//                 navigate("/adm"); // Navigate to admin page on success
//             }

//         } catch (err) {
//             console.error("Login failed:", err);
//             alert(err.response?.data || "Login failed. Try again.");
//         }
//     };

//     return (
//         <>
//             <h1>Welcome to Login page</h1>

//             <p>Email: {form.email}</p>
//             <p>Password: {form.password}</p>

//             <form onSubmit={handleSubmit}>
//                 <input
//                     onChange={handleChange}
//                     type="email"
//                     name="email"
//                     placeholder="Enter email"
//                     required
//                     autoComplete="email"
//                 />
//                 <br />

//                 <input
//                     onChange={handleChange}
//                     type="password"
//                     name="password"
//                     placeholder="Enter password"
//                     required
//                     autoComplete="current-password"
//                 />
//                 <br />

//                 <button type="submit">Login</button>
//             </form>
//         </>
//     );
// }

// export default Login;



import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./Login.css";
import Navbar from "./Navbar";
import Footer from "./Footer";

function Login() {
    const navigate = useNavigate();

    const [form, setForm] = useState({
        email: "",
        password: ""
    });

    const handleChange = (e) => {
        setForm({ ...form, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const res = await axios.post("http://localhost:8080/api/user/login", form, {
                withCredentials: true
            });

            alert(res.data);

            if (res.data === "Login Success") {
                navigate("/adm");
            }
        } catch (err) {
            alert(err.response?.data || "Login failed. Try again.");
        }
    };

    return (
        <>
            <Navbar />
            <div className="login-wrapper d-flex align-items-center justify-content-center text-light vh-100">
                <div className="login-card p-4 px-5 rounded shadow-lg text-center" style={{ backgroundColor: "rgba(0, 0, 0, 0.6)", border: "1px solid #444" }}>
                    <h2 className="mb-3" style={{ color: "#f9f871" }}>🔐 Welcome Back</h2>
                    <p className="mb-4 text-light">Login to your <strong>Task Portal</strong></p>

                    <form onSubmit={handleSubmit}>
                        <div className="mb-3">
                            <input
                                type="email"
                                name="email"
                                className="form-control bg-dark text-light border-secondary"
                                placeholder="📧 Email"
                                value={form.email}
                                onChange={handleChange}
                                required
                            />
                        </div>
                        <div className="mb-3">
                            <input
                                type="password"
                                name="password"
                                className="form-control bg-dark text-light border-secondary"
                                placeholder="🔒 Password"
                                value={form.password}
                                onChange={handleChange}
                                required
                            />
                        </div>
                        <button type="submit" className="btn btn-warning w-100 fw-bold">Login 🚀</button>
                    </form>

                    <p className="mt-4 small" style={{ color: "rgba(255, 255, 255, 0.7)" }}>
                        ❗ Don't share your credentials with anyone
                    </p>
                </div>
            </div>
            <Footer />
        </>
    );
}

export default Login;
