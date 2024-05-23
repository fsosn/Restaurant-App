import axios from 'axios';
import {API_ENDPOINTS} from "../config/config.jsx";

const handleErrors = (response) => {
    if (response.status !== 200) {
        throw Error(response.statusText);
    }
    return response;
};

const api = {
    registerUser: async (userData) => {
        try {
            const response = await axios.post(
                API_ENDPOINTS.BASE_URL +
                API_ENDPOINTS.API +
                API_ENDPOINTS.AUTH +
                API_ENDPOINTS.REGISTER,
                userData,
            );

            handleErrors(response);

            return response;
        } catch (error) {
            console.error('Error during registration:', error);
            throw error;
        }
    },
};

export default api;
