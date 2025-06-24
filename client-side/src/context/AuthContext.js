import React, { createContext, useContext, useState } from "react";

export const AuthContext = createContext();

export function AuthProvider({ children }) {
  const [customerId, setCustomerId] = useState(null);

  return (
    <AuthContext.Provider value={{ customerId, setCustomerId }}>
      {children}
    </AuthContext.Provider>
  );
}
export function useAuth() {
  return useContext(AuthContext);
}