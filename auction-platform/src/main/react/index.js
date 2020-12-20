import React from 'react'
import ReactDOM from 'react-dom'
import Footer from './component/footer'
import Header from './component/header'

ReactDOM.render(
    <div>
        <Header userName='Anastasia Andruhovich'/>
        <Footer/>
    </div>,
    document.getElementById('react')
)