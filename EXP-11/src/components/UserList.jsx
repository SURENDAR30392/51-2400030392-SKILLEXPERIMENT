import { useEffect, useState } from 'react';

function UserList() {
  const [users, setUsers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    const loadUsers = async () => {
      try {
        setLoading(true);
        setError('');

        const response = await fetch('https://jsonplaceholder.typicode.com/users');
        if (!response.ok) {
          throw new Error('Failed to fetch users from JSONPlaceholder.');
        }

        const data = await response.json();
        setUsers(data);
      } catch (loadError) {
        setError(loadError.message || 'Something went wrong while fetching users.');
      } finally {
        setLoading(false);
      }
    };

    loadUsers();
  }, []);

  return (
    <section>
      <div className="section-header">
        <div>
          <p className="section-tag">Part B</p>
          <h2>Users API</h2>
        </div>
        <a className="back-link" href="#">
          Back to Dashboard
        </a>
      </div>

      {loading && <p className="status-message">Fetching users from API...</p>}
      {error && <p className="status-message error-text">{error}</p>}

      {!loading && !error && (
        <div className="card-grid">
          {users.map((user) => (
            <article className="data-card" key={user.id}>
              <h3>{user.name}</h3>
              <p>{user.email}</p>
              <p>{user.phone}</p>
            </article>
          ))}
        </div>
      )}
    </section>
  );
}

export default UserList;
