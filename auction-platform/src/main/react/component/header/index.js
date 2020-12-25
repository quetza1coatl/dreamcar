import React from 'react'
import {HashRouter, NavLink, Route} from 'react-router-dom'

import './header.css'
import RequestTable from '../../container/request/table'
import OfferTable from "../../container/offer/table";

const Header = ({userEmail, login, logout}) => (
    <HashRouter>
        <header className="site-header">
            <div className="container">
                <div className="row">
                    <div className="col-xs-12 col-md-9">
                        <ul className="nav navbar-nav">
                            <li><NavLink to="/">Requests</NavLink></li>
                            <li><NavLink to="/offers">Offers</NavLink></li>
                        </ul>
                    </div>

                    <div className="col-xs-6 col-md-3 userName">
                        {userEmail ?
                            <span>
                                <a href="#" className="dropdown-toggle" data-toggle="dropdown" role="button"
                                   aria-haspopup="true" aria-expanded="false">{userEmail}</a>
                                <ul className="dropdown-menu"><li><a href="#" onClick={logout}>Log out</a></li></ul>
                            </span> :
                            <a href="#" role="button" onClick={login}>Log in</a>
                        }
                    </div>
                </div>
            </div>
        </header>
        <div className="content">
            <Route exact path="/" component={RequestTable}/>
            <Route path={["/offers/:requestId", "/offers"]} component={OfferTable}/>
        </div>
    </HashRouter>
);

export default Header