import React from "react";
import "./Sidebar.css";

const Sidebar = ({isOpen}) => {
    return (
        <div className={isOpen ? "sidebar open" : "sidebar"}>
            <div className="links">
                <ul>
                    <li>
                        <p>Products</p>
                    </li>
                    <li>
                        <p>Orders</p>
                    </li>
                    <li>
                        <p>Account</p>
                    </li>
                </ul>
            </div>
        </div>
    );
};

export default Sidebar;
