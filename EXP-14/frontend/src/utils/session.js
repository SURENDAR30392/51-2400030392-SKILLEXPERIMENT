const STORAGE_KEY = "loggedInUser";

export function saveSession(user) {
  localStorage.setItem(STORAGE_KEY, JSON.stringify(user));
}

export function getSession() {
  const storedUser = localStorage.getItem(STORAGE_KEY);
  return storedUser ? JSON.parse(storedUser) : null;
}

export function clearSession() {
  localStorage.removeItem(STORAGE_KEY);
}

export function isLoggedIn() {
  return Boolean(getSession());
}
