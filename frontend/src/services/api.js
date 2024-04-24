import axios from 'axios';

const BASE_URL = 'http://localhost:8080';

const handleErrors = (response) => {
    if (response.status !== 200) {
        throw Error(response.statusText);
    }
    return response;
};

const API = {
    getAllProducts: async () => {
        try {
            const response = await axios.get(`${BASE_URL}/products`);
            return handleErrors(response).data;
        } catch (error) {
            console.error('Error fetching all products:', error);
            throw error;
        }
    },

    getProductById: async (productId) => {
        try {
            const response = await axios.get(`${BASE_URL}/products/${productId}`);
            return handleErrors(response).data;
        } catch (error) {
            console.error('Error fetching product by ID:', error);
            throw error;
        }
    },

    createProduct: async (product) => {
        try {
            const response = await axios.post(`${BASE_URL}/products`, product);
            return handleErrors(response).data;
        } catch (error) {
            console.error('Error creating product:', error);
            throw error;
        }
    },

    updateProduct: async (productId, productDetails) => {
        try {
            const response = await axios.put(`${BASE_URL}/products/${productId}`, productDetails);
            return handleErrors(response).data;
        } catch (error) {
            console.error('Error updating product:', error);
            throw error;
        }
    },

    deleteProduct: async (productId) => {
        try {
            const response = await axios.delete(`${BASE_URL}/products/${productId}`);
            return handleErrors(response);
        } catch (error) {
            console.error('Error deleting product:', error);
            throw error;
        }
    }
};

export default API;
