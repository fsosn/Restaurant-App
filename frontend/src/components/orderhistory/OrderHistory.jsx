import React, { useContext } from 'react';
import PropTypes from 'prop-types';
import './OrderHistory.css';
import { AuthContext } from "../../auth/AuthContext.jsx";

const OrderHistory = ({ orders }) => {
    const auth = useContext(AuthContext);

    const renderOrderHistory = () => {
        if (!auth.role) {
            return <p>To see your Order History please log in</p>;
        } else if (auth.role === "USER") {
            return (
                <div className="order-history">
                    <h2 className="order-history-title">Order History</h2>
                    {orders.length > 0 ? (
                        <div className="order-list">
                            {orders.map((order, index) => (
                                <div className="order-item" key={index}>
                                    <div className="order-date">Date: {order.date}</div>
                                    <div className="order-details">
                                        <div>
                                            {order.orderItems.map((item, idx) => (
                                                <div key={idx}>
                                                    {item.quantity} x {item.product.name}
                                                </div>
                                            ))}
                                        </div>
                                        <div className="order-total">
                                            Total: ${order.orderItems.reduce((acc, item) => acc + item.product.price * item.quantity, 0).toFixed(2)}
                                        </div>
                                    </div>
                                </div>
                            ))}
                        </div>
                    ) : (
                        <p>No orders found for user.</p>
                    )}
                </div>
            );
        } else if (auth.role === "ADMIN") {
            return (
                <div className="order-history">
                    <h2 className="order-history-title">Orders</h2>
                    {orders.length > 0 ? (
                        <div className="order-list">
                            {orders.map((order, index) => (
                                <div className="order-item" key={index}>
                                    <div className="order-date">Date: {order.date}</div>
                                    <div className="order-details">
                                        <div>
                                            {order.orderItems.map((item, idx) => (
                                                <div key={idx}>
                                                    {item.quantity} x {item.product.name}
                                                </div>
                                            ))}
                                        </div>
                                        <div className="order-total">
                                            Total: ${order.orderItems.reduce((acc, item) => acc + item.product.price * item.quantity, 0).toFixed(2)}
                                        </div>
                                    </div>
                                </div>
                            ))}
                        </div>
                    ) : (
                        <p>No orders found.</p>
                    )}
                </div>
            );
        }
    };

    return (
        <>
            {renderOrderHistory()}
        </>
    );
};

OrderHistory.propTypes = {
    orders: PropTypes.array.isRequired,
};

export default OrderHistory;
