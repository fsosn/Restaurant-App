import PropTypes from "prop-types";
import Header from "../../components/header/Header.jsx";
import "./Page.css"

const Page = ({pageTitle, children}) => {
    return (
        <div className="container-fluid">
            <div className={"header"}>
                <Header title={pageTitle}/>
            </div>
            <div className={"main"}>
                {children}
            </div>
        </div>

    );
};

Page.propTypes = {
    pageTitle: PropTypes.string.isRequired,
    children: PropTypes.node.isRequired,
};

export default Page;