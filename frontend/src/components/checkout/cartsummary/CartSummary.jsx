import React, { useState, useEffect } from 'react';
import './CartSummary.css';

const CartSummary = ({ onTotalCostChange }) => {
  const [quantity, setQuantity] = useState(1);
  const itemPrice = 10.99;
  const deliveryFee = 1.00;

  useEffect(() => {
    const totalCost = (quantity * itemPrice + deliveryFee).toFixed(2);
    onTotalCostChange(totalCost);
  }, [quantity, onTotalCostChange]);

  const increaseQuantity = () => {
    setQuantity(quantity + 1);
  };

  const decreaseQuantity = () => {
    if (quantity > 1) {
      setQuantity(quantity - 1);
    }
  };

  return (
    <div className="cart-summary-box">
      <div className="cart-summary-inner-box">
        <div className="cart-summary-header">
          <span>1. Margherita</span>
          <span>$10.99</span>
        </div>
        <div className="quantity-controls">
          <button onClick={increaseQuantity}>+</button>
          <span>{quantity}</span>
          <button onClick={decreaseQuantity}>-</button>
        </div>
      </div>
      <div className="cart-summary-details">
        <p>
          <span>Order:</span> 
          <span>{quantity} x $10.99</span>
        </p>
        <p>
          <span>Delivery:</span> 
          <span>$1.00</span>
        </p>
        <p className="total-cost">
          <span>Total cost:</span> 
          <span>${(quantity * itemPrice + deliveryFee).toFixed(2)}</span>
        </p>
      </div>
    </div>
  );
};

export default CartSummary;