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

    getOrdersByUserId: async (userId) => {
        try {
            const response = await axios.get(
                API_ENDPOINTS.BASE_URL +
                API_ENDPOINTS.ORDERS +
                API_ENDPOINTS.USER + 
                '/' + 
                userId,
            );

            handleErrors(response);

            return response.data;
        } catch (error) {
            console.error('Error during fetching orders by userId:', error);
            throw error;
        }
    },

    getAllOrders: async () => {
        try {
            const response = await axios.get(
                `${API_ENDPOINTS.BASE_URL}${API_ENDPOINTS.ORDERS}`,
                {
                }
            );

            handleErrors(response);

            return response.data;
        } catch (error) {
            console.error('Error during fetching all orders:', error);
            throw error;
        }
    },

    processTransaction: async (transactionData, token) => {
        try {
            const response = await axios.post(
                `${API_ENDPOINTS.BASE_URL}${API_ENDPOINTS.API}${API_ENDPOINTS.TRANSACTION}`,
                transactionData,
                {
                    headers: {
                        Authorization: `Bearer ${token}`,
                    },
                }
            );

            handleErrors(response);

            return response;
        } catch (error) {
            console.error('Error during processing transaction:', error);
            throw error;
        }
    },
    getAllProducts: async () => {
        try {
            const response = await axios.get(`${API_ENDPOINTS.BASE_URL}${API_ENDPOINTS.PRODUCTS}`);
            return handleErrors(response).data;
        } catch (error) {
            console.error('Error fetching all products:', error);
            throw error;
        }
    },

    getProductById: async (productId) => {
        try {
            const response = await axios.get(`${API_ENDPOINTS.BASE_URL}${API_ENDPOINTS.PRODUCTS}${productId}`);
            return handleErrors(response).data;
        } catch (error) {
            console.error('Error fetching product by ID:', error);
            throw error;
        }
    },

    createProduct: async (product) => {
        try {
            const response = await axios.post(`${API_ENDPOINTS.BASE_URL}${API_ENDPOINTS.PRODUCTS}`, product);
            return handleErrors(response).data;
        } catch (error) {
            console.error('Error creating product:', error);
            throw error;
        }
    },

    updateProduct: async (productId, productDetails) => {
        try {
            const response = await axios.put(`${API_ENDPOINTS.BASE_URL}${API_ENDPOINTS.PRODUCTS}${productId}`, productDetails);
            return handleErrors(response).data;
        } catch (error) {
            console.error('Error updating product:', error);
            throw error;
        }
    },

    deleteProduct: async (productId) => {
        try {
            const response = await axios.delete(`${API_ENDPOINTS.BASE_URL}${API_ENDPOINTS.PRODUCTS}${productId}`);
            return handleErrors(response);
        } catch (error) {
            console.error('Error deleting product:', error);
            throw error;
        }
    }
};

export default api;
