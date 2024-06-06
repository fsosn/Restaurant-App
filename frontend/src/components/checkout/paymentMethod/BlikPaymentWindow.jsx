import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './BlikPaymentWindow.css';

const BlikPaymentWindow = ({ onClose, onFinalize }) => {
  const [blikCode, setBlikCode] = useState('');
  const [isLoading, setIsLoading] = useState(false);
  const [statusMessage, setStatusMessage] = useState('');
  const [isConfirming, setIsConfirming] = useState(false);
  const navigate = useNavigate();

  const handleBlikCodeChange = (e) => {
    setBlikCode(e.target.value);
  };

  const handleFinalizePayment = () => {
    if (blikCode.length !== 6 || !/^\d+$/.test(blikCode)) {
      setStatusMessage('BLIK code must be 6 digits long');
      return;
    }

    setIsConfirming(true);
    setStatusMessage('Waiting for confirmation...');

    setTimeout(() => {
      setStatusMessage('Processing the payment...');
      setIsLoading(true);

      const finalize = async () => {
        try {
          const success = await onFinalize(blikCode);
          if (success) {
            setStatusMessage('Payment successful!');
          } else {
            setStatusMessage('Payment failed. Please try again.');
          }
        } catch (error) {
          if (error.name === 'AbortError') {
            setStatusMessage('Payment process was cancelled.');
          } else {
            setStatusMessage('Payment failed. Please try again.');
          }
        } finally {
          setIsLoading(false);
        }
      };

      setTimeout(finalize, 5000);
    }, 5000);
  };

  const handleSuccessNavigate = () => {
    navigate('/');
  };

  return (
    <div className="blik-payment-overlay">
      <div className="blik-payment-window">
        <div className="blik-payment-content">
          <h2>BLIK Payment</h2>
          {!isLoading && !isConfirming && (
            <>
              <label>Enter BLIK Code:</label>
              <input
                type="text"
                value={blikCode}
                onChange={handleBlikCodeChange}
                placeholder="______"
                disabled={isLoading}
                maxLength={6}
              />
              <div className="button-group">
                <button onClick={handleFinalizePayment}>Finalize Payment</button>
                <button onClick={onClose}>Close</button>
              </div>
            </>
          )}
          {isLoading && (
            <div className="centered">
              <p>{statusMessage}</p>
              <div className="spinner"></div>
            </div>
          )}
          {!isLoading && statusMessage && (
            <div className="centered">
              <p>{statusMessage}</p>
              {statusMessage === 'Payment successful!' && (
                <button onClick={handleSuccessNavigate}>Make another order</button>
              )}
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

export default BlikPaymentWindow;

