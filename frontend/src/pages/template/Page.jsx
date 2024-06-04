import PropTypes from "prop-types";
import React from 'react';
import Header from "../../components/header/Header.jsx";
import ShoppingCart from "../../components/shoppingCart/ShoppingCart";
import "./Page.css";

const Page = ({ pageTitle, children, toggleCartVisibility, cartItems, isCartVisible, updateCartItems }) => {
    return (
        <div className="container-fluid">
            <div className={"header"}>
                <Header title={pageTitle} toggleCartVisibility={toggleCartVisibility} />
            </div>
            <div className={"main"}>
                {children}
            </div>
            {isCartVisible && <ShoppingCart cartItems={cartItems} updateCartItems={updateCartItems} />}
        </div>
    );
};

Page.propTypes = {
    pageTitle: PropTypes.string.isRequired,
    children: PropTypes.node.isRequired,
    toggleCartVisibility: PropTypes.func.isRequired,
    cartItems: PropTypes.array.isRequired,
    isCartVisible: PropTypes.bool.isRequired,
};

export default Page;
