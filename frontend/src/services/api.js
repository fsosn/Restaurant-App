import axios from 'axios';
import {API_ENDPOINTS} from "../config/config.js";

const handleErrors = (response) => {
    if (response.status !== 200) {
        throw Error(response.statusText);
    }
    return response;
};

const api = {

    getAllProducts: async () => {
        try {
            const response = await axios.get(
                API_ENDPOINTS.BASE_URL +
                API_ENDPOINTS.API +
                API_ENDPOINTS.PRODUCTS +
                API_ENDPOINTS.GET_ALL,
                {
                    headers: {
                        'Content-Type': 'application/json',
                    },
                }
            );

            handleErrors(response);

            return response.data;
        } catch (error) {
            console.error('Error getting loans:', error);
            throw error;
        }
    },

    getProductById: async (productId) => {
        try {
            const response = await axios.get(
                API_ENDPOINTS.BASE_URL +
                API_ENDPOINTS.API +
                API_ENDPOINTS.PRODUCTS +
                API_ENDPOINTS.GET_ALL,
                {
                    headers: {
                        'Content-Type': 'application/json',
                    },
                });

            handleErrors(response);

            return response.data;
        } catch (error) {
            console.error('Error geting product:', error);
            throw error;
        }
    },

    createProduct: async (productDto) => {
        try {
            const response = await axios.post(
                API_ENDPOINTS.BASE_URL +
                API_ENDPOINTS.API +
                API_ENDPOINTS.PRODUCTS +
                API_ENDPOINTS.CREATE,
                productDto,
                {
                    headers: {
                        'Content-Type': 'application/json',
                    },
                }
            );

            handleErrors(response);

            return response;
        } catch (error) {
            console.log('Error creating product:', error);
            throw error;
        }
    },

    updateProduct: async (productId, productDto) => {
        try {
            const response = await axios.put(
                `${API_ENDPOINTS.BASE_URL}${API_ENDPOINTS.API}${API_ENDPOINTS.PRODUCTS}${API_ENDPOINTS.PUT}/${productId}`,
                productDto,
                {
                    headers: {
                        'Content-Type': 'application/json',
                    },
                }
            );
    
            handleErrors(response);
    
            return response.data;
        } catch (error) {
            console.error('Error updating product:', error);
            throw error;
        }
    },

    deleteProduct: async (loanId) => {
        try {
            const response = await axios.delete(
                API_ENDPOINTS.BASE_URL +
                API_ENDPOINTS.API +
                API_ENDPOINTS.PRODUCTS +
                API_ENDPOINTS.DELETE,
                {
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    params: {
                        loanId: loanId
                    }
                }
            );

            handleErrors(response);

            return response.data;
        } catch (error) {
            console.error('Error deleting product:', error);
            throw error;
        }
    },
};

export default api;