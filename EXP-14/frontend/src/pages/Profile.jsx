import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import Navbar from "../components/Navbar";
import { fetchProfileById } from "../api/authApi";
import { clearSession, getSession } from "../utils/session";

function Profile() {
  const [profile, setProfile] = useState(null);
  const [error, setError] = useState("");
  const [isLoading, setIsLoading] = useState(true);
  const navigate = useNavigate();

  useEffect(() => {
    const currentUser = getSession();

    if (!currentUser?.id) {
      clearSession();
      navigate("/login", { replace: true });
      return;
    }

    async function loadProfile() {
      try {
        const data = await fetchProfileById(currentUser.id);
        setProfile(data);
      } catch (requestError) {
        if (requestError.message === "User not found") {
          clearSession();
          navigate("/login", { replace: true });
          return;
        }
        setError(requestError.message);
      } finally {
        setIsLoading(false);
      }
    }

    loadProfile();
  }, [navigate]);

  return (
    <div className="dashboard-page">
      <Navbar />
      <main className="dashboard-content">
        <section className="profile-card">
          <p className="eyebrow">Profile</p>
          <h1>User Details</h1>
          {isLoading && <p>Loading profile...</p>}
          {error && !isLoading && <p className="message error-message">{error}</p>}
          {profile && !isLoading && (
            <div className="profile-details">
              <div>
                <span>Full Name</span>
                <strong>{profile.fullName}</strong>
              </div>
              <div>
                <span>Username</span>
                <strong>{profile.username}</strong>
              </div>
              <div>
                <span>Email</span>
                <strong>{profile.email}</strong>
              </div>
              <div>
                <span>User ID</span>
                <strong>{profile.id}</strong>
              </div>
            </div>
          )}
        </section>
      </main>
    </div>
  );
}

export default Profile;
