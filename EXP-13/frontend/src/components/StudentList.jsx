function StudentList({ loading, students, onDelete, onEdit }) {
  return (
    <section className="card">
      <div className="card-heading">
        <p className="section-label">Students</p>
        <h2>Student List</h2>
      </div>

      {loading ? <p className="empty-state">Loading students...</p> : null}

      {!loading && students.length === 0 ? (
        <p className="empty-state">No students added yet.</p>
      ) : null}

      {!loading && students.length > 0 ? (
        <div className="table-wrap">
          <table>
            <thead>
              <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Course</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {students.map((student) => (
                <tr key={student.id}>
                  <td>{student.id}</td>
                  <td>{student.name}</td>
                  <td>{student.email}</td>
                  <td>{student.course}</td>
                  <td className="action-cell">
                    <button
                      className="secondary-btn"
                      type="button"
                      onClick={() => onEdit(student)}
                    >
                      Update
                    </button>
                    <button
                      className="danger-btn"
                      type="button"
                      onClick={() => onDelete(student.id)}
                    >
                      Delete
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      ) : null}
    </section>
  );
}

export default StudentList;
