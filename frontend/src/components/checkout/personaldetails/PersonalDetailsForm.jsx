import React from 'react';
import './PersonalDetailsForm.css';

const PersonalDetailsForm = ({ errors }) => {
  return (
    <div className="personal-details">
      <h2>Personal Details</h2>
      <div className="row">
        <div>
          <label htmlFor="first-name">First Name</label>
          <input id="first-name" />
          {errors.firstName && <p className="error">{errors.firstName}</p>}
        </div>
        <div>
          <label htmlFor="last-name">Last Name</label>
          <input id="last-name" />
          {errors.lastName && <p className="error">{errors.lastName}</p>}
        </div>
      </div>
      <div className="row">
        <div>
          <label htmlFor="email">E-mail</label>
          <input id="email" />
          {errors.email && <p className="error">{errors.email}</p>}
        </div>
        <div>
          <label htmlFor="phone-number">Phone number</label>
          <input id="phone-number" maxLength={9}/>
          {errors.phoneNumber && <p className="error">{errors.phoneNumber}</p>}
        </div>
      </div>
    </div>
  );
};

export default PersonalDetailsForm;


