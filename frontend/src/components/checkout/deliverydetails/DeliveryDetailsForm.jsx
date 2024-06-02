import React from 'react';
import './DeliveryDetailsForm.css';

const DeliveryDetailsForm = ({ errors }) => {
  return (
    <div className="delivery-details">
      <h2>Delivery Details</h2>
      <label htmlFor="street-address">Street Address</label>
      <input id="street-address" />
      {errors.streetAddress && <p className="error">{errors.streetAddress}</p>}
      <label htmlFor="street-address-2">Street Address - Line 2</label>
      <input id="street-address-2" />
      <div className="row">
        <div>
          <label htmlFor="city">City</label>
          <input id="city" />
          {errors.city && <p className="error">{errors.city}</p>}
        </div>
        <div>
          <label htmlFor="state">State</label>
          <input id="state" />
          {errors.state && <p className="error">{errors.state}</p>}
        </div>
        <div>
          <label htmlFor="zip-code">Zip Code</label>
          <input id="zip-code" />
          {errors.zipCode && <p className="error">{errors.zipCode}</p>}
        </div>
      </div>
    </div>
  );
};

export default DeliveryDetailsForm;


