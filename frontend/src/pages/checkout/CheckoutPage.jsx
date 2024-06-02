import React, { useState, useContext } from 'react';
import Page from "../template/Page.jsx";
import './CheckoutPage.css';
import DeliveryDetailsForm from '../../components/checkout/deliverydetails/DeliveryDetailsForm.jsx';
import PersonalDetailsForm from '../../components/checkout/personaldetails/PersonalDetailsForm.jsx';
import CartSummary from '../../components/checkout/cartsummary/CartSummary.jsx';
import PaymentMethod from '../../components/checkout/paymentMethod/PaymentMethod.jsx';
import api from '../../services/api.jsx';
import { AuthContext } from '../../auth/AuthContext.jsx';
import orderPayIcon from '../../assets/order-pay.svg';
import paymentTypeIcon from '../../assets/payment-type.svg';

const CheckoutPage = () => {
  const auth = useContext(AuthContext);
  const [orderType, setOrderType] = useState('takeaway'); // Default to takeaway
  const [totalCost, setTotalCost] = useState('11.99'); // Initial total cost
  const [errors, setErrors] = useState({});
  const [showPaymentModal, setShowPaymentModal] = useState(false);
  const [selectedPaymentMethod, setSelectedPaymentMethod] = useState('');

  const handleTotalCostChange = (newTotalCost) => {
    setTotalCost(newTotalCost);
  };

  const validateForm = () => {
    const newErrors = {};

    const firstName = document.getElementById('first-name').value;
    const lastName = document.getElementById('last-name').value;
    const email = document.getElementById('email').value;
    const phoneNumber = document.getElementById('phone-number').value;

    if (!firstName) {
      newErrors.firstName = 'First name is required';
    }
    if (!lastName) {
      newErrors.lastName = 'Last name is required';
    }
    if (!email || !/^\S+@\S+\.\S+$/.test(email)) {
      newErrors.email = 'Valid email is required';
    }
    if (!phoneNumber || !/^\d{9}$/.test(phoneNumber)) {
      newErrors.phoneNumber = 'Phone number should be 9 digits';
    }

    if (orderType === 'delivery') {
      const streetAddress = document.getElementById('street-address').value;
      const streetAddress2 = document.getElementById('street-address-2').value;
      const city = document.getElementById('city').value;
      const state = document.getElementById('state').value;
      const zipCode = document.getElementById('zip-code').value;

      if (!streetAddress) {
        newErrors.streetAddress = 'Street address is required for delivery';
      }
      if (!city) {
        newErrors.city = 'City is required for delivery';
      }
      if (!state) {
        newErrors.state = 'State is required for delivery';
      }
      if (!zipCode || !/^\d{2}-\d{3}$/.test(zipCode)) {
        newErrors.zipCode = 'ZIP code should be in format XX-XXX';
      }
    }

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleSubmit = async () => {
    if (!validateForm()) {
      return;
    }

    const firstName = document.getElementById('first-name').value;
    const lastName = document.getElementById('last-name').value;
    const email = document.getElementById('email').value;
    const phoneNumber = document.getElementById('phone-number').value;

    let request = {
      firstName,
      lastName,
      email,
      phoneNumber,
      totalCost: parseFloat(totalCost),
      orderType,
      paymentMethod: selectedPaymentMethod
    };

    if (orderType === 'delivery') {
      request = {
        ...request,
        streetAddress: document.getElementById('street-address').value,
        streetAddress2: document.getElementById('street-address-2').value,
        city: document.getElementById('city').value,
        state: document.getElementById('state').value,
        zipCode: document.getElementById('zip-code').value,
      };
    }

    console.log('Auth Token:', auth.token);
    console.log('Transaction Request:', request);

    try {
      const response = await api.processTransaction(request, auth.token);
      if (response.status === 200) {
        alert('Transaction processed successfully');
      } else {
        alert('Transaction failed');
      }
    } catch (error) {
      console.error('Error:', error);
      alert('Transaction failed');
    }
  };

  const handleChoosePaymentMethod = () => {
    setShowPaymentModal(true);
  };

  const handleClosePaymentModal = () => {
    setShowPaymentModal(false);
  };

  const handlePaymentMethodChange = (method) => {
    setSelectedPaymentMethod(method);
  };

  let title = "MarioLuigi";
  return (
    <Page pageTitle={title}>
      <div className="checkout-container-box">
        <h1 className="order-type-buttons" style={{ color: "orange" }}>
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
            <div className="delivery-details-box"><DeliveryDetailsForm errors={errors} /></div>
          }
          <div className="personal-details-box"><PersonalDetailsForm errors={errors} /></div>
        </div>
        <div>
          <CartSummary orderType={orderType} onTotalCostChange={handleTotalCostChange} />
        </div>
        <div className="buttons-box">
          <button className='payment-type-button' onClick={handleChoosePaymentMethod}>
            Choose payment method <img src={paymentTypeIcon} alt="Payment Type Icon" />
          </button>
          <button className='order-button' onClick={handleSubmit} disabled={!selectedPaymentMethod}>
            Order and pay (${totalCost}) <img src={orderPayIcon} alt="Order Pay Icon" />
          </button>
        </div>
      </div>
      {showPaymentModal && (
        <PaymentMethod 
          onClose={handleClosePaymentModal}
          onPaymentMethodChange={handlePaymentMethodChange}
          selectedPaymentMethod={selectedPaymentMethod}
        />
      )}
    </Page>
  );
};

export default CheckoutPage;