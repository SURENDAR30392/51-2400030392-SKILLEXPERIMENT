import { useEffect, useState } from 'react';
import LocalUserList from './LocalUserList';
import UserList from './UserList';
import FakePostList from './FakePostList';

const routes = {
  '#/local-users': 'local-users',
  '#/users-api': 'users-api',
  '#/fake-posts': 'fake-posts',
};

function getCurrentView() {
  return routes[window.location.hash] ?? 'home';
}

function Dashboard() {
  const [view, setView] = useState(getCurrentView);

  useEffect(() => {
    const handleHashChange = () => setView(getCurrentView());

    window.addEventListener('hashchange', handleHashChange);
    return () => window.removeEventListener('hashchange', handleHashChange);
  }, []);

  return (
    <main className="app-shell">
      <section className="hero-card">
        <p className="eyebrow">React API Integration</p>
        <h1>News Portal Data Dashboard</h1>
        <p className="hero-copy">
          Explore user and article data from a local JSON file, a public users API,
          and a fake posts API with loading, refresh, and filtering support.
        </p>

        <nav className="nav-grid" aria-label="Dashboard navigation">
          <a className="nav-card" href="#/local-users">
            <span>Local Users</span>
            <small>Fetch records from the public folder</small>
          </a>
          <a className="nav-card" href="#/users-api">
            <span>Users API</span>
            <small>Load user details from JSONPlaceholder</small>
          </a>
          <a className="nav-card" href="#/fake-posts">
            <span>Fake API Posts</span>
            <small>Read, filter, and refresh remote post data</small>
          </a>
        </nav>
      </section>

      <section className="content-card">
        {view === 'home' && (
          <div className="empty-state">
            <h2>Select a data source</h2>
            <p>Use the dashboard links above to open each component.</p>
          </div>
        )}
        {view === 'local-users' && <LocalUserList />}
        {view === 'users-api' && <UserList />}
        {view === 'fake-posts' && <FakePostList />}
      </section>
    </main>
  );
}

export default Dashboard;
