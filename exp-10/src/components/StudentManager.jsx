import { useState } from "react";

function StudentManager() 
{

  const initialStudents = [
    { id: 1, name: "Venki", course: "React" },
    { id: 2, name: "Ravi", course: "Java" },
    { id: 3, name: "Anitha", course: "Python" },
    { id: 4, name: "Rahul", course: "Spring Boot" },
    { id: 5, name: "Divya", course: "AI" }
  ];

  const [students, setStudents] = useState(initialStudents);

  const [newStudent, setNewStudent] = useState({
    id: "",
    name: "",
    course: ""
  });

  const handleChange = (e) => {

    const { name, value } = e.target;

    setNewStudent({
      ...newStudent,
      [name]: value
    });

  };

  const addStudent = () => {

    if (newStudent.id && newStudent.name && newStudent.course) 
      {

      setStudents([...students, newStudent]);

      setNewStudent({
        id: "",
        name: "",
        course: ""
      });

    }

  };

  const deleteStudent = (id) => {

    const updatedList = students.filter(
      student => student.id != id
    );

    setStudents(updatedList);

  };

  return (

    <div className="container">

      <h2>Add Student</h2>

      <input
        type="text"
        name="id"
        placeholder="Enter ID"
        value={newStudent.id}
        onChange={handleChange}
      />

      <input
        type="text"
        name="name"
        placeholder="Enter Name"
        value={newStudent.name}
        onChange={handleChange}
      />

      <input
        type="text"
        name="course"
        placeholder="Enter Course"
        value={newStudent.course}
        onChange={handleChange}
      />

      <button onClick={addStudent}>
        Add Student
      </button>

      <h2>Student List</h2>

      {students.length === 0 ? (

        <p>No students available</p>

      ) : (

        <table>

          <thead>

            <tr>
              <th>ID</th>
              <th>Name</th>
              <th>Course</th>
              <th>Action</th>
            </tr>

          </thead>

          <tbody>

            {students.map((student) => (

              <tr key={student.id}>

                <td>{student.id}</td>

                <td>{student.name}</td>

                <td>{student.course}</td>

                <td>

                  <button
                    className="deleteBtn"
                    onClick={() => deleteStudent(student.id)}
                  >
                    Delete
                  </button>

                </td>

              </tr>

            ))}

          </tbody>

        </table>

      )}

    </div>
  );
}

export default StudentManager;