import React from 'react';
import './PersonalDetailsForm.css';

const PersonalDetailsForm = () => {
  return (
    <div className="personal-details">
      <h2>Personal Details</h2>
      <div className="row">
        <div>
          <label>First Name</label>
          <input />
        </div>
        <div>
          <label>Last Name</label>
          <input />
        </div>
      </div>
      <div className="row">
        <div>
          <label>E-mail</label>
          <input />
        </div>
        <div>
          <label>Phone number</label>
          <input />
        </div>
      </div>
    </div>
  );
};

export default PersonalDetailsForm;