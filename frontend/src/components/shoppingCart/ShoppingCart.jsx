import React, { useState, useEffect } from 'react';
import "./ShoppingCart.css";

const ShoppingCart = ({ cartItems, updateCartItems }) => {
    const [cart, setCart] = useState([]);
    const [deliveryOption, setDeliveryOption] = useState("delivery");

    useEffect(() => {
        if (cartItems) {
            const updatedCartItems = cartItems.map(item => ({
                ...item,
                quantity: item.quantity || 1
            }));
            setCart(updatedCartItems);
        }
    }, [cartItems]);

    useEffect(() => {
        console.log("Obecny stan koszyka: ", JSON.stringify(cart, null, 2));
    }, [cart]);

    const totalCost = cart.reduce((acc, item) => acc + item.price * item.quantity, 0);
    const deliveryCost = deliveryOption === "delivery" ? 1 : 0;

    const handleAddItem = (item) => {
        const updatedCart = cart.map(cartItem =>
            cartItem.name === item.name
                ? { ...cartItem, quantity: cartItem.quantity + 1 }
                : cartItem
        );
        setCart(updatedCart);
        updateCartItems(updatedCart);
    };

    const handleRemoveItem = (item) => {
        const updatedCart = cart.map(cartItem =>
            cartItem.name === item.name
                ? { ...cartItem, quantity: cartItem.quantity - 1 }
                : cartItem
        ).filter(cartItem => cartItem.quantity > 0);
        setCart(updatedCart);
        updateCartItems(updatedCart);
    };


    return (
        <div className="cart">
            <div className="cart-header">
                <button className={`cart-tab ${deliveryOption === "delivery" ? "active" : ""}`} onClick={() => setDeliveryOption("delivery")}>Delivery</button>
                <button className={`cart-tab ${deliveryOption === "takeout" ? "active" : ""}`} onClick={() => setDeliveryOption("takeout")}>Takeaway</button>
            </div>
            <div className="cart-content">
                <div className="cart-items-1">
                    {cart.map((item, index) => (
                        <div key={item.name} className="cart-items-2">
                            <div className="cart-item">
                                <span>{index + 1}. {item.name}</span>
                                <span className="cart-item-price">${(item.price * item.quantity).toFixed(2)}</span>
                            </div>
                            <div className="cart-item-controls">
                                <button className="cart-control-btn" onClick={() => handleRemoveItem(item)}><img src="./delete.svg" alt="Delete icon" /></button>
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
                    {deliveryOption === "delivery" && (
                        <div className="cart-summary-line">
                            <span>Delivery:</span>
                            <span>$1.00</span>
                        </div>
                    )}
                    <div className="cart-summary-total">
                        <span>Total cost:</span>
                        <span>${(totalCost + deliveryCost).toFixed(2)}</span>
                    </div>
                </div>
                <button className="cart-checkout-btn">Checkout</button>
            </div>
        </div>
    );
};

export default ShoppingCart;
