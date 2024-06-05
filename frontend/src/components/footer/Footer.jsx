import React from 'react';
import './Footer.css';
import {faFacebook} from "@fortawesome/free-brands-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faInstagram} from "@fortawesome/free-brands-svg-icons/faInstagram";

const Footer = () => (
    <div className="footer-container d-flex align-items-center justify-content-center">
        <div className="row w-75 text-center">
            <div className="col">
                <h5 className="text-uppercase footer-sections-font mb-3">Contact us</h5>
                <ul className="list-unstyled">
                    <li>pizza.pal@gmail.com</li>
                    <li>(312) 555-1234</li>
                </ul>
            </div>
            <div className="col">
                <h5 className="text-uppercase footer-sections-font mb-3">Opening hours</h5>
                <ul className="list-unstyled">
                    <li>Monday - Saturday</li>
                    <li>12:00 PM - 11:00 PM</li>
                </ul>
            </div>
            <div className="col">
                <h5 className="text-uppercase footer-sections-font  mb-3">Follow us</h5>
                <ul className="list-inline">
                    <li className="list-inline-item"><FontAwesomeIcon icon={faFacebook} size="2x" className="mr-16 footer-icon"/></li>
                    <li className="list-inline-item"><FontAwesomeIcon icon={faInstagram} size="2x" className="footer-icon"/></li>
                </ul>
                <div>

                </div>
            </div>
        </div>
    </div>
);

export default Footer;
