import PropTypes from "prop-types";
import Header from "../../components/header/Header.jsx";

const Page = ({pageTitle, children}) => {
    return (
        <div className="container-fluid">
            <Header title={pageTitle}/>
            {children}
        </div>

    );
};

Page.propTypes = {
    pageTitle: PropTypes.string.isRequired,
    children: PropTypes.node.isRequired,
};

export default Page;