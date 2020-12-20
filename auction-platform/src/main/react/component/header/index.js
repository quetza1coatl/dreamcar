import React from 'react'
import './header.css'

const Header = ({userName, login, logout}) => (
    <header className="site-header">
        <div className="container">
            <div className="row">
                <div className="col-xs-12 col-md-9">
                    <ul className="nav navbar-nav">
                        <li><a href="">Requests</a></li>
                        <li><a href="">Offers</a></li>
                    </ul>
                </div>

                <div className="col-xs-6 col-md-3 userName">
                    {userName ?
                        <span>
                            <a href="#" className="dropdown-toggle" data-toggle="dropdown" role="button"
                               aria-haspopup="true" aria-expanded="false">{userName}</a>
                            <ul className="dropdown-menu"><li><a href="#" onClick={logout}>Log out</a></li></ul>
                        </span> :
                        <a href="#" role="button" onClick={login}>Log in</a>
                    }
                </div>
            </div>
        </div>
    </header>
);

export default Header