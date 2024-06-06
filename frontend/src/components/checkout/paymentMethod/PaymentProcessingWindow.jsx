import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import './PaymentProcessingWindow.css';

const PaymentProcessingWindow = ({ onClose, onFinalize, paymentMethod, totalCost }) => {
  const [isLoading, setIsLoading] = useState(false);
  const [statusMessage, setStatusMessage] = useState('');
  const [controller, setController] = useState(null);
  const navigate = useNavigate();

  const handleFinalizePayment = () => {
    if (paymentMethod.toLowerCase() === 'cash') {
      setStatusMessage(`Your total order cost is $${totalCost.toFixed(2)}. Please pay this amount when claiming your order.`);
      return;
    }

    const newController = new AbortController();
    setController(newController);

    setIsLoading(true);
    setStatusMessage('Processing the payment...');

    const finalize = async () => {
      try {
        const success = await onFinalize(null, newController.signal);
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
  };

  const handleSuccessNavigate = () => {
    navigate('/');
  };

  useEffect(() => {
    handleFinalizePayment();
    return () => {
      if (controller) {
        controller.abort();
      }
    };
  }, []);

  return (
    <div className="payment-processing-overlay">
      <div className="payment-processing-window">
        <div className="payment-processing-content">
          <h2>{paymentMethod.toUpperCase()} Payment</h2>
          {paymentMethod.toLowerCase() === 'cash' ? (
            <div>
              <p>{statusMessage}</p>
              <button onClick={handleSuccessNavigate}>Make Another Order</button>
            </div>
          ) : (
            <>
              {isLoading ? (
                <div className="centered">
                  <p>{statusMessage}</p>
                  <div className="spinner"></div>
                </div>
              ) : (
                <div className="centered">
                  <p>{statusMessage}</p>
                  {statusMessage === 'Payment successful!' ? (
                    <button onClick={handleSuccessNavigate}>Make another order</button>
                  ) : (
                    <button onClick={onClose}>Close</button>
                  )}
                </div>
              )}
            </>
          )}
        </div>
      </div>
    </div>
  );
};

export default PaymentProcessingWindow;
