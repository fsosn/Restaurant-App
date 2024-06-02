import React, { useState } from 'react';
import Page from "../template/Page.jsx";
import './CheckoutPage.css';
import DeliveryDetailsForm from '../../components/checkout/deliverydetails/DeliveryDetailsForm.jsx';
import PersonalDetailsForm from '../../components/checkout/personaldetails/PersonalDetailsForm.jsx';
import CartSummary from '../../components/checkout/cartsummary/CartSummary.jsx';

const CheckoutPage = () => {
  const [orderType, setOrderType] = useState('takeaway'); // Default to takeaway
  const [totalCost, setTotalCost] = useState('11.99'); // Initial total cost

  const handleTotalCostChange = (newTotalCost) => {
    setTotalCost(newTotalCost);
  };

  let title = "MarioLuigi";
  return (
    <Page pageTitle={title}>
      <div className="checkout-container-box">
        <h1 className="order-type-buttons" style={{color: "orange"}}>
          Checkout
          <button onClick={() => setOrderType('takeaway')} className={orderType === 'takeaway' ? 'active' : ''}>
            Takeaway
          </button>
          <button onClick={() => setOrderType('delivery')} className={orderType === 'delivery' ? 'active' : ''}>
            Delivery
          </button>
        </h1>
        <div className='details-box'>
          {orderType === 'delivery' && 
            <div className="delivery-details-box"><DeliveryDetailsForm /></div>
          }
          <div className="personal-details-box"><PersonalDetailsForm /></div>
        </div>
        <div>
          <CartSummary onTotalCostChange={handleTotalCostChange} />
        </div>
        <div className="buttons-box">
          <button className='payment-type-button'>Choose payment method</button>
          <button className='order-button'>Order and pay (${totalCost})</button>
        </div>
      </div>
    </Page>
  );
};

export default CheckoutPage;