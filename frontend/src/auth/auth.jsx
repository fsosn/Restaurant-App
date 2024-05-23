import {API_ENDPOINTS} from "../config/config.jsx";
import axios from 'axios';

const auth = {
    isAuthenticated: false,
    role: null,

    signIn: async (email, password, callback) => {
        try {
            const authResponse = await axios.post(
                API_ENDPOINTS.BASE_URL +
                API_ENDPOINTS.API +
                API_ENDPOINTS.AUTH +
                API_ENDPOINTS.AUTHENTICATE,
                {
                    email,
                    password
                }
            );
            if (authResponse.data.success === false) {
                alert(authResponse.data.message);
                window.location.reload()
                return
            }

            const token = authResponse.data.token;
            axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;

            const getRoleResponse = await axios.get(
                API_ENDPOINTS.BASE_URL +
                API_ENDPOINTS.API +
                API_ENDPOINTS.AUTH +
                API_ENDPOINTS.GET_ROLE,
            );

            const role = getRoleResponse.data.role;
            auth.isAuthenticated = true;
            auth.role = role;

            console.log(auth.role)
            callback();
        } catch (e) {
            alert("Authentication failed");
            console.error("Authentication failed: ", e);
            window.location.reload()
        }
    },
    signOut: (callback) => {
        let token = null;
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
        auth.isAuthenticated = false;
        window.location.reload()
        callback();
    }
};

export {auth};
