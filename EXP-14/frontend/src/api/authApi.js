const API_BASE_URL = "http://localhost:8080/api/auth";

async function handleResponse(response) {
  const data = await response.json().catch(() => ({}));

  if (!response.ok) {
    const message =
      data.message ||
      Object.values(data)[0] ||
      "Something went wrong. Please try again.";
    throw new Error(message);
  }

  return data;
}

export async function registerUser(payload) {
  const response = await fetch(`${API_BASE_URL}/register`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(payload)
  });

  return handleResponse(response);
}

export async function loginUser(payload) {
  const response = await fetch(`${API_BASE_URL}/login`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(payload)
  });

  return handleResponse(response);
}

export async function fetchProfileById(userId) {
  const response = await fetch(`${API_BASE_URL}/profile/${userId}`);
  return handleResponse(response);
}
