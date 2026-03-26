import { useEffect, useState } from 'react';
import axios from 'axios';

function FakePostList() {
  const [posts, setPosts] = useState([]);
  const [filteredPosts, setFilteredPosts] = useState([]);
  const [selectedUserId, setSelectedUserId] = useState('all');
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  const loadPosts = async () => {
    try {
      setLoading(true);
      setError('');

      const response = await axios.get('https://dummyjson.com/posts');
      const postList = response.data.posts ?? [];
      setPosts(postList);
    } catch (loadError) {
      setError(loadError.message || 'Something went wrong while loading posts.');
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    loadPosts();
  }, []);

  useEffect(() => {
    if (selectedUserId === 'all') {
      setFilteredPosts(posts);
      return;
    }

    const nextPosts = posts.filter((post) => String(post.userId) === selectedUserId);
    setFilteredPosts(nextPosts);
  }, [posts, selectedUserId]);

  const userOptions = [...new Set(posts.map((post) => post.userId))].sort((a, b) => a - b);

  return (
    <section>
      <div className="section-header">
        <div>
          <p className="section-tag">Part C</p>
          <h2>Fake API Posts</h2>
        </div>
        <div className="toolbar">
          <a className="back-link" href="#">
            Back to Dashboard
          </a>
          <button className="refresh-button" onClick={loadPosts} type="button">
            Refresh
          </button>
        </div>
      </div>

      <div className="filter-row">
        <label htmlFor="user-filter">Filter by User ID</label>
        <select
          id="user-filter"
          value={selectedUserId}
          onChange={(event) => setSelectedUserId(event.target.value)}
        >
          <option value="all">All Users</option>
          {userOptions.map((userId) => (
            <option key={userId} value={String(userId)}>
              User {userId}
            </option>
          ))}
        </select>
      </div>

      {loading && <p className="status-message">Loading posts from fake API...</p>}
      {error && <p className="status-message error-text">{error}</p>}

      {!loading && !error && (
        <div className="post-grid">
          {filteredPosts.map((post) => (
            <article className="data-card post-card" key={post.id}>
              <p className="post-meta">User ID: {post.userId}</p>
              <h3>{post.title}</h3>
              <p>{post.body}</p>
            </article>
          ))}
        </div>
      )}
    </section>
  );
}

export default FakePostList;
