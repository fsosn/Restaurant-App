import React from "react";
import "./Sidebar.css";

const Sidebar = ({isOpen}) => {
    return (
        <div className={isOpen ? "sidebar open" : "sidebar"}>
            <div className="links">
                <ul>
                    <li>
                        <h4>Link</h4>
                    </li>
                    <li>
                        <h4>Link</h4>
                    </li>
                    <li>
                        <h4>Link</h4>
                    </li>
                </ul>
            </div>
        </div>
    );
};

export default Sidebar;
