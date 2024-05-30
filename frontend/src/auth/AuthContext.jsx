import { createContext, useState } from "react";
import { auth } from "./auth.jsx";
import PropTypes from "prop-types";

export const AuthContext = createContext(null);

export const AuthProvider = ({ children }) => {
    const [email, setEmail] = useState(null);
    const [role, setRole] = useState(null);
    const [userId, setUserId] = useState(null);
    const [token, setToken] = useState(null);

    let signIn = (email, password, callback) => {
        return auth.signIn(email, password, ({ userId, token }) => {
            setEmail(email);
            setRole(auth.role);
            setUserId(userId);
            setToken(token);
            callback();
        });
    };

    let signOut = (callback) => {
        return auth.signOut(() => {
            setEmail(null);
            setRole(null);
            setUserId(null);
            setToken(null);
            callback();
        });
    };

    return (
        <AuthContext.Provider value={{ user: email, role, userId, token, signIn, signOut }}>
            {children}
        </AuthContext.Provider>
    );
};

AuthProvider.propTypes = {
    children: PropTypes.node.isRequired,
};
