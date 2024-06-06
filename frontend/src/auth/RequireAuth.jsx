import {Navigate, useLocation} from "react-router-dom";
import {useContext} from "react";
import {AuthContext} from "./AuthContext.jsx";
import PropTypes from "prop-types";

export function RequireAuth({children}) {
    let auth = useContext(AuthContext);
    let location = useLocation();

    if (auth.authenticated === null) {
        return (
            <div>
                Loading...
            </div>)
    }

    if (!auth.authenticated) {
        return <Navigate to="/login" state={{from: location}} replace/>;
    }

    return children;
}

RequireAuth.propTypes = {
    children: PropTypes.node.isRequired,
};