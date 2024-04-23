import "./Headline.css";
import PropTypes from "prop-types";

const Headline = ({title}) => {
    return (
        <div className="container-fluid headline-rectangle">
            <div className="row">
                <div className="col text-container">
                    <h1 className="headline-title">{title}</h1>
                    <div className={"wrapper"}>
                    <p className="headline-text">We're more than just a pizza place.<br/>
                        Enjoy a culinary experience crafted with passion.<br/>
                        Delight your taste buds in a symphony of flavors.
                    </p></div>
                </div>
                <div className="col">
                    <img src="/headline.png" alt="Headline Pizza Image" className="headline-image"/>
                </div>
            </div>
        </div>
    );
};

Headline.propTypes = {
    title: PropTypes.string.isRequired,
};

export default Headline;
