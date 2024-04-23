import React, {useState} from "react";
import PropTypes from "prop-types";
import "./Header.css";
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome'
import {faBars, faCartShopping} from "@fortawesome/free-solid-svg-icons";
import Sidebar from "../sidebar/Sidebar";
import "../../../public/logo.svg"

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
                    <FontAwesomeIcon icon={faCartShopping} className="icon"/>
                    <FontAwesomeIcon icon={faBars} className="icon bars-wrapper" onClick={toggleSidebar}/>
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
