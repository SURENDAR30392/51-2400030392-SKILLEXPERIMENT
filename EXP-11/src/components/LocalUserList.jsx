import { useEffect, useState } from 'react';

function LocalUserList() {
  const [users, setUsers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    const loadUsers = async () => {
      try {
        setLoading(true);
        setError('');

        const response = await fetch('/users.json');
        if (!response.ok) {
          throw new Error('Failed to load local users.');
        }

        const data = await response.json();
        setUsers(data);
      } catch (loadError) {
        setError(loadError.message || 'Something went wrong while loading users.');
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
          <p className="section-tag">Part A</p>
          <h2>Local Users</h2>
        </div>
        <a className="back-link" href="#">
          Back to Dashboard
        </a>
      </div>

      {loading && <p className="status-message">Loading local users...</p>}
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

export default LocalUserList;
