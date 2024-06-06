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

    const fetchData = async () => {
        try {
            if (!auth.role || !auth.userId) {
                setLoading(false);
                return;
            }

            let ordersData;
            if (auth.role === "ADMIN") {
                ordersData = await api.getAllOrders();
            } else if (auth.role === "USER") {
                ordersData = await api.getOrdersByUserId(auth.userId);
            }
            setOrders(ordersData);
            setLoading(false);
        } catch (error) {
            setError(error.message);
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchData();
    }, [auth.role, auth.userId]);

    return (
        <Page pageTitle="Order History">
            <Header title="MarioLuigi" />
            {loading && <p>Loading...</p>}
            {!loading && <OrderHistory orders={orders} error={error} />}
        </Page>
    );
};

export default OrderHistoryPage;