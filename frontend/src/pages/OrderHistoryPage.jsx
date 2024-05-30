import React, { useEffect, useState, useContext } from 'react';
import Page from "./template/Page.jsx";
import OrderHistory from '../components/orderhistory/OrderHistory.jsx';
import api from '../services/api.jsx';
import Header from '../components/header/Header.jsx';
import { AuthContext } from "../auth/AuthContext.jsx";

const OrderHistoryPage = () => {
    const auth = useContext(AuthContext);
    const [orders, setOrders] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchOrders = async () => {
            try {
                let ordersData;
                if (auth.role === "ADMIN") {
                    ordersData = await api.getAllOrders(auth.token);
                } else if (auth.role === "USER") {
                    ordersData = await api.getOrdersByUserId(auth.userId, auth.token);
                } else {
                    setError("To see your Order History please log in");
                    setLoading(false);
                    return;
                }
                setOrders(ordersData);
                setLoading(false);
            } catch (error) {
                setError(error.message);
                setLoading(false);
            }
        };

        fetchOrders();
    }, [auth.role, auth.userId, auth.token]);

    return (
        <Page pageTitle="Order History">
            <Header title="MarioLuigi" />
            {loading && <p>Loading orders...</p>}
            {error && <p>{error}</p>}
            {!loading && !error && <OrderHistory orders={orders} />}
        </Page>
    );
};

export default OrderHistoryPage;
