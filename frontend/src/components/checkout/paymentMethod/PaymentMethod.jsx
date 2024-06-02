import React from 'react';
import './PaymentMethod.css';

const PaymentMethod = ({ onClose, onPaymentMethodChange, selectedPaymentMethod }) => {
  return (
    <div className="payment-method-modal">
      <div className="payment-method-content">
        <h2>Choose Payment Method</h2>
        <div className="payment-method-options">
          <label>
            <input
              type="radio"
              name="paymentMethod"
              value="card"
              checked={selectedPaymentMethod === 'card'}
              onChange={() => onPaymentMethodChange('card')}
            />
            Card
          </label>
          <label>
            <input
              type="radio"
              name="paymentMethod"
              value="blik"
              checked={selectedPaymentMethod === 'blik'}
              onChange={() => onPaymentMethodChange('blik')}
            />
            BLIK
          </label>
          <label>
            <input
              type="radio"
              name="paymentMethod"
              value="cash"
              checked={selectedPaymentMethod === 'cash'}
              onChange={() => onPaymentMethodChange('cash')}
            />
            Cash
          </label>
        </div>
        <button className="ok-button" onClick={onClose}>OK</button>
      </div>
    </div>
  );
};

export default PaymentMethod;
