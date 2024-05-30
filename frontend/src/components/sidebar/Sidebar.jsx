import {useContext} from "react";
import "./Sidebar.css";
import {AuthContext} from "../../auth/AuthContext.jsx";
import {useNavigate} from "react-router-dom";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {
    faClockRotateLeft,
    faPizzaSlice,
    faRightFromBracket,
    faTruck,
    faUser,
    faUtensils
} from "@fortawesome/free-solid-svg-icons";
import {faRightToBracket} from "@fortawesome/free-solid-svg-icons";

const Sidebar = ({isOpen}) => {
    const auth = useContext(AuthContext);
    const navigate = useNavigate();

    const handleSignOut = () => {
        auth.signOut();
    };

    const renderSidebarContent = () => {
        if (!auth.role) {
            return (
                <div className="links">
                    <ul>
                        <li onClick={() => navigate("/")}>
                            <FontAwesomeIcon icon={faUtensils} className={"sidebar-icon"}/>
                            <span>Menu</span>
                        </li>
                        <li onClick={() => navigate("/login")}>
                            <FontAwesomeIcon icon={faRightToBracket} className={"sidebar-icon"}/>
                            <span>Sign In</span>
                        </li>
                    </ul>
                </div>
            );
        } else if (auth.role === "USER") {
            return (
                <div className="links">
                    <ul>
                        <li onClick={() => navigate("/")}>
                            <FontAwesomeIcon icon={faUtensils} className={"sidebar-icon"}/>
                            <span>Menu</span>
                        </li>
                        <li onClick={() => navigate("/order-history")}>
                            <FontAwesomeIcon icon={faClockRotateLeft} className={"sidebar-icon"}/>
                            <span>Order history</span>
                        </li>
                        <li onClick={() => navigate("/account")}>
                            <FontAwesomeIcon icon={faUser} className={"sidebar-icon"}/>
                            <span>Account</span>
                        </li>
                    </ul>
                    <div className="spacer"></div>
                    <ul>
                        <li onClick={handleSignOut}>
                            <FontAwesomeIcon icon={faRightFromBracket} className={"sidebar-icon"}/>
                            <span>Sign Out</span>
                        </li>
                    </ul>
                </div>
            );
        } else if (auth.role === "ADMIN") {
            return (
                <div className="links">
                    <ul>
                        <li onClick={() => navigate("/")}>
                            <FontAwesomeIcon icon={faUtensils} className={"sidebar-icon"}/>
                            <span>Menu</span>
                        </li>
                        <li onClick={() => navigate("/products")}>
                            <FontAwesomeIcon icon={faPizzaSlice} className={"sidebar-icon"}/>
                            <span>Products</span>
                        </li>
                        <li onClick={() => navigate("/order-history")}>
                            <FontAwesomeIcon icon={faTruck} className={"sidebar-icon"}/>
                            <span>Orders</span>
                        </li>
                        <li onClick={() => navigate("/account")}>
                            <FontAwesomeIcon icon={faUser} className={"sidebar-icon"}/>
                            <span>Account</span>
                        </li>
                    </ul>
                    <div className="spacer"></div>
                    <ul>
                        <li onClick={handleSignOut}>
                            <FontAwesomeIcon icon={faRightFromBracket} className={"sidebar-icon"}/>
                            <span>Sign Out</span>
                        </li>
                    </ul>
                </div>
            );
        }
    };

    return (
        <div className={isOpen ? "sidebar open" : "sidebar"}>
            {renderSidebarContent()}
        </div>
    );
};

export default Sidebar;
