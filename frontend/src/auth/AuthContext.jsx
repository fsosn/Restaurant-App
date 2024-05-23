import {createContext, useState} from "react";
import {auth} from "./auth.jsx";
import PropTypes from "prop-types";

export const AuthContext = createContext(null);

export const AuthProvider = ({children}) => {
    const [email, setEmail] = useState(null);
    const [role, setRole] = useState(null);

    let signIn = (email, password, callback) => {
        return auth.signIn(email, password, () => {
            setEmail(email);
            setRole(auth.role);
            callback();
        });
    };

    let signOut = (callback) => {
        return auth.signOut(() => {
            setEmail(null);
            setRole(null);
            callback();
        });
    };


    return (
        <AuthContext.Provider value={{user: email, role, signIn, signOut}}>
            {children}
        </AuthContext.Provider>
    );
};

AuthProvider.propTypes = {
    children: PropTypes.node.isRequired,
}
