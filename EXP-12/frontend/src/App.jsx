import { useEffect, useState } from "react";
import axios from "axios";
import AddStudent from "./components/AddStudent";
import StudentList from "./components/StudentList";

const configuredBaseUrl = import.meta.env.VITE_API_BASE_URL?.trim() || "";

const api = axios.create({
  baseURL: configuredBaseUrl
});

const initialFormData = {
  name: "",
  email: "",
  course: ""
};

function App() {
  const [students, setStudents] = useState([]);
  const [formData, setFormData] = useState(initialFormData);
  const [editingId, setEditingId] = useState(null);
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  const fetchStudents = async () => {
    setLoading(true);
    try {
      const response = await api.get("/students");
      setStudents(response.data);
      setError("");
    } catch (fetchError) {
      setError("Unable to load students. Please check the backend server.");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchStudents();
  }, []);

  const handleChange = (event) => {
    const { name, value } = event.target;
    setFormData((current) => ({
      ...current,
      [name]: value
    }));
  };

  const resetForm = () => {
    setFormData(initialFormData);
    setEditingId(null);
  };

  const handleSubmit = async (event) => {
    event.preventDefault();

    try {
      if (editingId) {
        const response = await api.put(`/students/${editingId}`, formData);
        setStudents((current) =>
          current.map((student) =>
            student.id === editingId ? response.data : student
          )
        );
      } else {
        const response = await api.post("/students", formData);
        setStudents((current) => [...current, response.data]);
      }

      resetForm();
      setError("");
    } catch (submitError) {
      setError("Unable to save the student. Please verify the entered details.");
    }
  };

  const handleEdit = (student) => {
    setFormData({
      name: student.name,
      email: student.email,
      course: student.course
    });
    setEditingId(student.id);
    setError("");
  };

  const handleDelete = async (id) => {
    try {
      await api.delete(`/students/${id}`);
      setStudents((current) => current.filter((student) => student.id !== id));

      if (editingId === id) {
        resetForm();
      }
    } catch (deleteError) {
      setError("Unable to delete the student right now.");
    }
  };

  return (
    <div className="app-shell">
      <div className="hero-panel">
        <p className="eyebrow">Full-Stack CRUD Application</p>
        <h1>Student Management System</h1>
        <p className="subtitle">
          Add, view, update, and delete student records instantly without
          reloading the page.
        </p>
      </div>

      <div className="content-grid">
        <AddStudent
          formData={formData}
          editingId={editingId}
          error={error}
          onCancel={resetForm}
          onChange={handleChange}
          onSubmit={handleSubmit}
        />

        <StudentList
          loading={loading}
          students={students}
          onDelete={handleDelete}
          onEdit={handleEdit}
        />
      </div>
    </div>
  );
}

export default App;
