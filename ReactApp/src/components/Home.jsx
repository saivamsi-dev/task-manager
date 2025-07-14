// function Home(){
//     return(
//         <>
//         <h1>
//             Home Page
//         </h1>
//         </>
//     )
// }
import "bootstrap/dist/css/bootstrap.min.css";
import "./Home.css";
import { useNavigate } from "react-router-dom";
import Navbar from "./Navbar";
import Footer from "./Footer";

function Home() {
    const navigate = useNavigate();

    return (
        <>
            <Navbar />
            <div className="home-wrapper d-flex align-items-center justify-content-center text-light vh-100">
                <div className="container">
                    <div className="row align-items-center">
                        {/* Animation on the left */}
                        <div className="col-md-6 text-center mb-4 mb-md-0">
                            <lottie-player
                                src="https://assets7.lottiefiles.com/packages/lf20_49rdyysj.json"
                                background="transparent"
                                speed="1"
                                style={{ width: "100%", maxWidth: "400px", margin: "0 auto" }}
                                loop
                                autoplay
                            ></lottie-player>
                        </div>

                        {/* Welcome text on the right */}
                        <div className="col-md-6 text-center text-md-start">
                            <h1 className="display-5 fw-bold" style={{ color: "#f9f871" }}>
                                ✅ Welcome to Task Portal
                            </h1>
                            <p className="lead" style={{ color: "#d4d4d4" }}>
                                ✍️ Manage your to-dos, 📅 track progress, and 💼 stay productive!
                            </p>
                            <button className="btn btn-warning mt-3 fw-semibold" onClick={() => navigate("/log")}>
                                🚀 Get Started
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <Footer />
        </>
    );
}

export default Home;
