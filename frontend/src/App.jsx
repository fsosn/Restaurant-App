import './App.css';
import MainPage from "./pages/MainPage.jsx";
import OrderHistoryPage from "./pages/OrderHistoryPage.jsx";
import CheckoutPage from "./pages/checkout/CheckoutPage.jsx";
import {Route, Routes} from "react-router-dom";
import {RequireAuth} from "./auth/RequireAuth.jsx";
import RegisterForm from "./components/auth/register/RegisterForm.jsx";
import LoginForm from "./components/auth/login/LoginForm.jsx";
import React, {useState, useEffect} from 'react';

function App() {
    const [isCartVisible, setIsCartVisible] = useState(false);
    const [cartItems, setCartItems] = useState([]);

    useEffect(() => {
        const storedCartItems = JSON.parse(localStorage.getItem('cart')) || [];
        setCartItems(storedCartItems);
    }, []);

    const toggleCartVisibility = () => {
        setIsCartVisible(!isCartVisible);
    };

    const updateCartItems = (items) => {
        setCartItems(items);
        localStorage.setItem('cart', JSON.stringify(items));
    };

    const addToCart = (productName, price) => {
        const existingItemIndex = cartItems.findIndex(cartItem => cartItem.name === productName);
        let updatedCart;

        if (existingItemIndex !== -1) {
            updatedCart = cartItems.map((cartItem, index) =>
                index === existingItemIndex
                    ? {...cartItem, quantity: cartItem.quantity + 1}
                    : cartItem
            );
        } else {
            updatedCart = [...cartItems, {name: productName, price, quantity: 1}];
        }

        setCartItems(updatedCart);
    };

    return (
        <Routes>
            <Route
                index
                path='/'
                element={
                    <MainPage
                        toggleCartVisibility={toggleCartVisibility}
                        cartItems={cartItems}
                        isCartVisible={isCartVisible}
                        addToCart={addToCart}
                        updateCartItems={updateCartItems}
                    />
                }
            />
            <Route path='/login' element={<LoginForm/>}/>
            <Route path='/register' element={<RegisterForm/>}/>
            <Route
                path='/order-history'
                element={
                    <RequireAuth>
                        <OrderHistoryPage
                            toggleCartVisibility={toggleCartVisibility}
                            cartItems={cartItems}
                            isCartVisible={isCartVisible}
                            updateCartItems={updateCartItems}
                        />
                    </RequireAuth>
                }
            />
            <Route
                path='/checkout'
                element={
                    <CheckoutPage
                        toggleCartVisibility={toggleCartVisibility}
                        cartItems={cartItems}
                        isCartVisible={isCartVisible}
                        updateCartItems={updateCartItems}
                    />
                }
            />
        </Routes>
    );
}

export default App;
