function AddStudent({
  formData,
  editingId,
  error,
  onCancel,
  onChange,
  onSubmit
}) {
  return (
    <section className="card">
      <div className="card-heading">
        <p className="section-label">{editingId ? "Update" : "Add"}</p>
        <h2>{editingId ? "Edit Student" : "Add Student"}</h2>
      </div>

      <form className="student-form" onSubmit={onSubmit}>
        <label>
          Name
          <input
            name="name"
            value={formData.name}
            onChange={onChange}
            placeholder="Enter student name"
            required
          />
        </label>

        <label>
          Email
          <input
            name="email"
            type="email"
            value={formData.email}
            onChange={onChange}
            placeholder="Enter email address"
            required
          />
        </label>

        <label>
          Course
          <input
            name="course"
            value={formData.course}
            onChange={onChange}
            placeholder="Enter course name"
            required
          />
        </label>

        {error ? <p className="form-error">{error}</p> : null}

        <div className="form-actions">
          <button className="primary-btn" type="submit">
            {editingId ? "Update Student" : "Add Student"}
          </button>
          {editingId ? (
            <button className="secondary-btn" type="button" onClick={onCancel}>
              Cancel
            </button>
          ) : null}
        </div>
      </form>
    </section>
  );
}

export default AddStudent;
