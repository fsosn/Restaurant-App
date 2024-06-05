import React, { useState } from "react";
import PropTypes from "prop-types";
import "./Header.css";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faBars, faCartShopping } from "@fortawesome/free-solid-svg-icons";
import Sidebar from "../sidebar/Sidebar";
import {useNavigate} from "react-router-dom";

const Header = ({ title, toggleCartVisibility }) => {
    const [sidebarOpen, setSidebarOpen] = useState(false);
    const navigate = useNavigate();

    const toggleSidebar = () => {
        setSidebarOpen(!sidebarOpen);
    };

    return (
        <div className="header bg">
            <h1 className="pageTitle" onClick={()=>navigate("/")}>{title}</h1>
            <div className="icon-container">
                <div>
                    <FontAwesomeIcon icon={faCartShopping} className="icon cart-icon wrapper" onClick={toggleCartVisibility} />
                    <FontAwesomeIcon icon={faBars} className="icon wrapper" onClick={toggleSidebar} />
                </div>
            </div>
            <Sidebar isOpen={sidebarOpen} toggle={toggleSidebar} />
        </div>
    );
};

Header.propTypes = {
    title: PropTypes.string.isRequired,
    toggleCartVisibility: PropTypes.func.isRequired,
};

export default Header;
