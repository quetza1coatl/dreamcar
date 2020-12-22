import React from 'react'
import './footer.css'

const Footer = () => (
    <footer className="site-footer">
        <div className="container">
            <div className="row">
                <div className="col-xs-12 col-md-9">
                    <h6>About</h6>
                    <p className="text-justify"><i>Believe in magic </i> is an integral part of the
                        survival philosophy. Bugs and mistakes are a highlight in every sense of a memorable project with which
                        you can try to organize a tender in the form of an open auction for the purchase of car parts.
                        We will be glad if you succeed in doing this. The developers did not succeed:(</p>
                </div>

                <div className="col-xs-6 col-md-3">
                    <h6>Contacts</h6>
                    <ul className="footer-links">
                        <li>Office: Minsk, street Nowhere 666</li>
                        <li>Phone: +37544 xxx-xx-xx</li>
                        <li>Emergency cases: turn on fantasy</li>
                    </ul>
                </div>
            </div>
            <hr/>
        </div>
        <div className="container">
            <div className="row">
                <div className="col-md-8 col-sm-6 col-xs-12">
                    <p className="copyright-text">Copyright &copy; 2020 All rights could be reserved by Auction platform.</p>
                </div>
            </div>
        </div>
    </footer>
);

export default Footer