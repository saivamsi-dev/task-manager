import { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function TaskManager() {
    const [isUpdate, setIsUpdate] = useState(false)
    const [id, setid] = useState(null)
    const navigate = useNavigate();

    // Form state for task creation
    const [form, setForm] = useState({
        title: "",
        description: "",
        dueDate: ""
    });

    // Username and tasks
    const [username, setUsername] = useState("");
    const [tasks, setTasks] = useState([]);

    // Fetch user and tasks on mount
    useEffect(() => {
        fetchUser();
        fetchTasks();
    }, []);

    // Fetch logged-in user's data
    const fetchUser = async () => {
        try {
            const res = await axios.get("http://localhost:8080/api/user/profile", {
                withCredentials: true
            });
            setUsername(res.data.username);
        } catch (err) {
            alert("Please login again");
            navigate("/log");
        }
    };

    // Fetch tasks assigned to the logged-in user
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

    // Handle form input change
    const handleChange = (e) => {
        setForm({ ...form, [e.target.name]: e.target.value });
    };

    // Submit task form
    const handleSubmit = async (e) => {
        e.preventDefault();
        if (!isUpdate) {
            try {
                const res = await axios.post("http://localhost:8080/api/task/add", form, {
                    withCredentials: true
                });
                alert(res.data);
                setForm({ title: "", description: "", dueDate: "" });
                fetchTasks();
            } catch (err) {
                alert(err.response?.data || "Task creation failed");
            }
        } else {
            try {
                const res = await axios.put(`http://localhost:8080/api/task/update/${id}`, form, {
                    withCredentials: true
                });
                alert("Task updated successfully!");
                setForm({ title: "", description: "", dueDate: "" });
                setIsUpdate(false);
                setid(null);
                fetchTasks();
            } catch (err) {
                alert(err.response?.data || "Update failed");
            }
        }
    };


    // Delete a task
    const del = async (id) => {
        const confirmDelete = window.confirm("Are you sure you want to delete this task?");
        if (!confirmDelete) return;
        try {
            await axios.delete(`http://localhost:8080/api/task/delete/${id}`, {
                withCredentials: true
            });
            alert("Task deleted successfully!");
            fetchTasks();
        } catch (err) {
            console.error("Delete failed", err);
            alert("Failed to delete task");
        }
    };

    // Update a task
    const upd = (task) => {
        setForm({
            title: task.title,
            description: task.description,
            dueDate: task.dueDate,
        });
        setIsUpdate(true);
        setid(task.id);
    };


    return (
        <div style={{ padding: "20px" }}>
            <h2>📝 Task Manager</h2>
            <p>Logged in as: <strong>{username}</strong></p>

            {/* --- Add Task Form --- */}
            <form onSubmit={handleSubmit}>
                <input type="text" name="title" value={form.title} onChange={handleChange} placeholder="Title" required />
                <br />
                <input type="text" name="description" value={form.description} onChange={handleChange} placeholder="Description" required />
                <br />
                <input type="date" name="dueDate" value={form.dueDate} onChange={handleChange} required />
                <br />
                <button type="submit">{isUpdate ? "Update" : "Add Task"}</button>
            </form>
            <button onClick={() => navigate("/adm")}>Admin</button>

            <hr />

            {/* --- Task Table --- */}
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
                            <th>Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                        {tasks.map((task) => (
                            <tr key={task.id}>
                                <td>{task.title}</td>
                                <td>{task.description}</td>
                                <td>{task.dueDate}</td>
                                <td>{task.completed ? "✅" : "❌"}</td>
                                <td><button onClick={() => del(task.id)}>Delete</button></td>
                                <td><button onClick={() => upd(task)}>Update</button></td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            )}
        </div>
    );
}

export default TaskManager;
