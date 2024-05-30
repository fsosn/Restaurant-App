import React, { useEffect, useState, useContext } from 'react';
import Page from "./template/Page.jsx";
import OrderHistory from '../components/orderhistory/OrderHistory.jsx';
import api from '../services/api.jsx';
import Header from '../components/header/Header.jsx';
import { AuthContext } from "../auth/AuthContext.jsx";

const OrderHistoryPage = () => {
    const [orders, setOrders] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const { userId, token } = useContext(AuthContext);

    useEffect(() => {
        const fetchOrders = async () => {
            try {
                if (userId && token) {
                    const ordersData = await api.getOrdersByUserId(userId, token);
                    setOrders(ordersData);
                }
                setLoading(false);
            } catch (error) {
                setError(error.message);
                setLoading(false);
            }
        };

        fetchOrders();
    }, [userId, token]);

    return (
        <Page pageTitle="Order History">
            <Header title="MarioLuigi" />
            {loading && <p>Loading orders...</p>}
            {error && <p>Error loading orders: {error}</p>}
            {!loading && !error && <OrderHistory orders={orders} />}
        </Page>
    );
};

export default OrderHistoryPage;
