import React, { useEffect, useState, useContext } from 'react';
import Page from "./template/Page.jsx";
import OrderHistory from '../components/orderhistory/OrderHistory.jsx';
import api from '../services/api.jsx';
import Header from '../components/header/Header.jsx';
import { AuthContext } from "../auth/AuthContext.jsx";

const OrderHistoryPage = ({ toggleCartVisibility, cartItems, isCartVisible, updateCartItems }) => {
    const auth = useContext(AuthContext);
    const [orders, setOrders] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchOrders = async () => {
            try {console.log(auth)
                if (!auth.role || !auth.userId) {
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

        fetchOrders();
    }, [auth.role, auth.userId]);

    return (
        <Page pageTitle="Order History" cartItems={cartItems} isCartVisible={isCartVisible} updateCartItems={updateCartItems}>
            <Header title="MarioLuigi" toggleCartVisibility={toggleCartVisibility}/>
            {loading && <p>Loading...</p>}
            {error && <p>{error}</p>}
            {!loading && !error && <OrderHistory orders={orders} />}
        </Page>
    );
};

export default OrderHistoryPage;
