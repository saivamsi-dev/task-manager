import { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function Admin() {
    const navigate = useNavigate();
    const [username, setUsername] = useState("");
    const [tasks, setTasks] = useState([]);

    useEffect(() => {
        fetchUser();
        fetchTasks();
    }, []);

    const fetchUser = async () => {
        try {
            const res = await axios.get("http://localhost:8080/api/user/profile", {
                withCredentials: true
            });
            setUsername(res.data.username);
        } catch {
            alert("Please login again");
            navigate("/log");
        }
    };

    const fetchTasks = async () => {
        try {
            const res = await axios.get("http://localhost:8080/api/task/user-tasks", {
                withCredentials: true
            });
            setTasks(res.data);
        } catch (err) {
            console.error(err);
        }
    };
    const del = async (id) => {
        const confirmDelete = window.confirm("Are you sure you want to delete this task?");
        if (!confirmDelete) return;
        try {
            const res = await axios.delete(`http://localhost:8080/api/task/delete/${id}`, {
                withCredentials: true
            });
            alert("Task deleted successfully!");
            fetchTasks(); // Refresh
        } catch (err) {
            console.error("Delete failed", err);
            alert("Failed to delete task");
        }
    };
    const toggleStatus = async (task) => {
        try {
            const updatedTask = {
                ...task,
                completed: !task.completed
            };

            await axios.put(`http://localhost:8080/api/task/update/${task.id}`, updatedTask, {
                withCredentials: true
            });

            alert(updatedTask.completed ? "Task marked as complete!" : "Task marked as incomplete!");
            fetchTasks(); // Refresh after update
        } catch (err) {
            console.error("Failed to update status:", err);
            alert("Failed to update task status");
        }
    };

    // const update = async (id) => {
    //     const confirmDelete = window.confirm("Are you sure you want to delete this task?");
    //     if (!confirmDelete) return;
    //     try {
    //         const res = await axios.delete(`http://localhost:8080/api/task/delete/${id}`, {
    //             withCredentials: true
    //         });
    //         alert("Task deleted successfully!");
    //         fetchTasks(); // Refresh
    //     } catch (err) {
    //         console.error("Delete failed", err);
    //         alert("Failed to delete task");
    //     }
    // };

    return (
        <div style={{ padding: "20px" }}>
            <h2>👋 Hey, {username}</h2>
            <p>Welcome to your admin dashboard</p>
            <div>
                <button onClick={() => navigate("/tskm")}>Go to Task Manager</button><br /><br />
                <button onClick={() => navigate("/log")}>Logout</button>
            </div>

            <hr />
            <h3>Your Tasks</h3>
            {tasks.length === 0 ? (
                <p>No tasks found</p>
            ) : (
                <table border="1" cellPadding="10">
                    <thead>
                        <tr>
                            <th>Title</th>
                            <th>Description</th>
                            <th>Due Date</th>
                            <th>Status</th>
                            {/* <th>Updation</th> */}
                            <th>Deletion</th>
                        </tr>
                    </thead>
                    <tbody>
                        {tasks.map((task) => (
                            <tr key={task.id}>
                                <td>{task.title}</td>
                                <td>{task.description}</td>
                                <td>{task.dueDate}</td>
                                {/* <td>{task.completed ? "✅" : "❌"}</td> */}
                                <td><button onClick={() => toggleStatus(task)}>{task.completed ? "✅" : "❌"}</button></td>
                                {/* <td><button>Update</button></td> */}
                                <td><button onClick={() => del(task.id)}>Delete</button></td>
                                {/* <td><button onClick={() => update(task.id)}>Update</button></td> */}
                                {/* <td><button onClick={del}>Delete</button></td> */}
                            </tr>
                        ))}
                    </tbody>
                </table>
            )}
        </div>
    );
}

export default Admin;
