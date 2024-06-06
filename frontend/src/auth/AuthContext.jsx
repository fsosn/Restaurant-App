import {createContext, useEffect, useState} from "react";
import { auth } from "./auth.jsx";
import PropTypes from "prop-types";

export const AuthContext = createContext(null);

export const AuthProvider = ({ children }) => {
    const [authenticated, setAuthenticated] = useState(null);
    const [email, setEmail] = useState(null);
    const [role, setRole] = useState(null);
    const [userId, setUserId] = useState(null);

    let signIn = (email, password, callback) => {
        return auth.signIn(email, password, () => {
            setEmail(auth.email);
            setRole(auth.role);
            setUserId(auth.userId);
            setAuthenticated(true);
            callback();
        });
    };

    let signOut = (callback) => {
        return auth.signOut(() => {
            setEmail(null);
            setRole(null);
            setUserId(null);
            setAuthenticated(false);
            callback();
        });
    };

    const initializeAuthentication = async () => {
        try {
            await auth.authenticateFromCookie();
            setEmail(auth.email);
            setRole(auth.role);
            setUserId(auth.userId);
            setAuthenticated(auth.isAuthenticated);
        } catch (error) {
            console.error("Error initializing authentication:", error);
        }
    };

    useEffect(() => {
        initializeAuthentication();
    }, []);

    return (
        <AuthContext.Provider value={{ user: email, role, userId, signIn, signOut, authenticated }}>
            {children}
        </AuthContext.Provider>
    );
};

AuthProvider.propTypes = {
    children: PropTypes.node.isRequired,
};
