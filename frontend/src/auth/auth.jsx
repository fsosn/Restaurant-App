import {API_ENDPOINTS} from "../config/config.jsx";
import axios from 'axios';
import Cookies from "js-cookie";

const auth = {
    email: null,
    isAuthenticated: false,
    role: null,
    userId: null,

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
                window.location.reload();
                return;
            }

            const token = authResponse.data.token;
            axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;

            const roleRes = await axios.get(
                API_ENDPOINTS.BASE_URL +
                API_ENDPOINTS.API +
                API_ENDPOINTS.AUTH +
                API_ENDPOINTS.ACCOUNT_DETAILS,
            );

            const role = roleRes.data.role;
            const userId = roleRes.data.userId;
            auth.isAuthenticated = true;
            auth.role = role;
            auth.userId = userId;

            const userData = {token, userId, role};
            Cookies.set("userData", JSON.stringify(userData), {
                secure: true,
                sameSite: "strict",
            });
            callback();
        } catch (e) {
            alert("Authentication failed");
            console.error("Authentication failed: ", e);
            window.location.reload();
        }
    },
    signOut: (callback) => {
        let token = null;
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
        auth.isAuthenticated = false;
        auth.userId = null;
        window.location.reload();
        callback();
    },

    authenticateFromCookie: async () => {
        const userDataString = Cookies.get("userData");
        if (userDataString) {
            const userData = JSON.parse(userDataString);
            const token = userData.token;
            try {
                const response = await axios.get(
                    API_ENDPOINTS.BASE_URL +
                    API_ENDPOINTS.API +
                    API_ENDPOINTS.AUTH + API_ENDPOINTS.ACCOUNT_DETAILS,
                    {
                        headers: {
                            Authorization: `Bearer ${token}`,
                        },
                    }
                );
                auth.isAuthenticated = true;
                auth.role = response.data.role;
                auth.userId = response.data.userId;
                auth.email = response.data.email;
            } catch (error) {
                console.error("Error during authentication from cookie:", error);
                Cookies.remove("userData");
                auth.isAuthenticated = false;
                auth.role = null;
                auth.userId = null;
                auth.email = null;
            }
        }
    },
};

export {auth};
