import Navbar from "../components/Navbar";
import { getSession } from "../utils/session";

function Home() {
  const user = getSession();

  return (
    <div className="dashboard-page">
      <Navbar />
      <main className="dashboard-content">
        <section className="hero-card">
          <p className="eyebrow">Home</p>
          <h1>Hello, {user?.fullName || user?.username}</h1>
          <p>
            Your session is active. Use the navigation bar to view your profile
            details or log out securely.
          </p>
        </section>

        <section className="info-grid">
          <article className="info-card">
            <h2>Stored Session</h2>
            <p>Username: {user?.username}</p>
            <p>User ID: {user?.id}</p>
          </article>
          <article className="info-card">
            <h2>Authentication Flow</h2>
            <p>Register, log in, access protected pages, and log out from one place.</p>
          </article>
        </section>
      </main>
    </div>
  );
}

export default Home;
