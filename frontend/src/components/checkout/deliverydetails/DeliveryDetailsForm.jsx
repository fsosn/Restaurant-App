import React from 'react';
import './DeliveryDetailsForm.css';

const DeliveryDetailsForm = () => {
  return (
    <div className="delivery-details">
      <h2>Delivery Details</h2>
      <label>Street Address</label>
      <input />
      <label>Street Address - Line 2</label>
      <input />
      <div className="row">
        <div>
          <label>City</label>
          <input />
        </div>
        <div>
          <label>State</label>
          <input />
        </div>
        <div>
          <label>Zip Code</label>
          <input />
        </div>
      </div>
    </div>
  );
};

export default DeliveryDetailsForm;