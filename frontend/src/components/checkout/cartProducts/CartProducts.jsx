import React, { useEffect } from 'react';
import "./CartProducts.css";

const CartProducts = ({ cartItems, updateCartItems, orderType, onTotalCostChange }) => {
    const handleAddItem = (item) => {
        const updatedCart = cartItems.map(cartItem =>
            cartItem.name === item.name
                ? { ...cartItem, quantity: cartItem.quantity + 1 }
                : cartItem
        );
        updateCartItems(updatedCart);
    };

    const handleRemoveItem = (item) => {
        const updatedCart = cartItems.map(cartItem =>
            cartItem.name === item.name
                ? { ...cartItem, quantity: cartItem.quantity - 1 }
                : cartItem
        ).filter(cartItem => cartItem.quantity > 0);
        updateCartItems(updatedCart);
    };

    const totalCost = cartItems.reduce((acc, item) => acc + item.price * item.quantity, 0);
    const deliveryCost = orderType === 'delivery' ? 1 : 0;

    useEffect(() => {
        onTotalCostChange(totalCost + deliveryCost);
    }, [cartItems, orderType, onTotalCostChange, totalCost]);

    return (
        <div className="cart-products">
            <div className="cart-items">
                {cartItems.map((item, index) => (
                    <div key={item.name} className="cart-item-container">
                        <div className="cart-item">
                            <span>{index + 1}. {item.name}</span>
                            <span className="cart-item-price">${(item.price * item.quantity).toFixed(2)}</span>
                        </div>
                        <div className="cart-item-controls">
                            <button className="cart-control-btn" onClick={() => handleRemoveItem(item)}><img src="./delete.svg" alt="Remove icon" /></button>
                            <span className="cart-item-quantity">{item.quantity}</span>
                            <button className="cart-control-btn" onClick={() => handleAddItem(item)}><img src="./add.svg" alt="Add icon" /></button>
                        </div>
                    </div>
                ))}
            </div>
            <div className="cart-summary">
                <div className="cart-summary-line">
                    <span>Order:</span>
                    <span>${totalCost.toFixed(2)}</span>
                </div>
                <div className="cart-summary-line">
                    <span>Delivery:</span>
                    <span>${deliveryCost.toFixed(2)}</span>
                </div>
                <div className="cart-summary-total">
                    <span>Total cost:</span>
                    <span>${(totalCost + deliveryCost).toFixed(2)}</span>
                </div>
            </div>
        </div>
    );
};

export default CartProducts;
