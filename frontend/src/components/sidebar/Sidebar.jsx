import React from "react";
import "./Sidebar.css";

const Sidebar = ({isOpen}) => {
    return (
        <div className={isOpen ? "sidebar open" : "sidebar"}>
            <div className="links">
                <ul>
                    <li>
                        <h3>Link</h3>
                    </li>
                    <li>
                        <h3>Link</h3>
                    </li>
                    <li>
                        <h3>Link</h3>
                    </li>
                </ul>
            </div>
        </div>
    );
};

export default Sidebar;
