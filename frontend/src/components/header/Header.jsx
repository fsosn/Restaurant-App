import React, {useState} from "react";
import PropTypes from "prop-types";
import "./Header.css";
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome'
import {faBars, faCartShopping} from "@fortawesome/free-solid-svg-icons";
import Sidebar from "../sidebar/Sidebar";

const Header = ({title}) => {
    const [sidebarOpen, setSidebarOpen] = useState(false);

    const toggleSidebar = () => {
        setSidebarOpen(!sidebarOpen);
    };

    return (
        <div className="header bg">
            <h1 className="pageTitle p-2">{title}</h1>
            <div className="icon-container">
                <div>
                    <FontAwesomeIcon icon={faCartShopping} className="icon cart-icon wrapper"/>
                    <FontAwesomeIcon icon={faBars} className="icon wrapper" onClick={toggleSidebar}/>
                </div>
            </div>
            <Sidebar isOpen={sidebarOpen} toggle={toggleSidebar}/>
        </div>
    );
};

Header.propTypes = {
    title: PropTypes.string.isRequired,
};

export default Header;
