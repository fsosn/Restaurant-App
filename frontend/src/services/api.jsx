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

    getOrdersByUserId: async (userId, token) => {
        try {
            const response = await axios.get(
                API_ENDPOINTS.BASE_URL +
                API_ENDPOINTS.ORDERS +
                API_ENDPOINTS.USER + 
                '/' + 
                userId,
                {
                    headers: {
                        Authorization: `Bearer ${token}`,
                    },
                }
            );

            handleErrors(response);

            return response.data;
        } catch (error) {
            console.error('Error during fetching orders by userId:', error);
            throw error;
        }
    },

    getAllOrders: async (token) => {
        try {
            const response = await axios.get(
                `${API_ENDPOINTS.BASE_URL}${API_ENDPOINTS.ORDERS}`,
                {
                    headers: {
                        Authorization: `Bearer ${token}`,
                    },
                }
            );

            handleErrors(response);

            return response.data;
        } catch (error) {
            console.error('Error during fetching all orders:', error);
            throw error;
        }
    },

    processTransaction: async (transactionData) => {
        try {
            const response = await axios.post(
                `${API_ENDPOINTS.BASE_URL}${API_ENDPOINTS.API}${API_ENDPOINTS.TRANSACTION}`,
                transactionData
            );

            handleErrors(response);

            return response;
        } catch (error) {
            console.error('Error during processing transaction:', error);
            throw error;
        }
    }
};

export default api;
