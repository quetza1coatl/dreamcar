import React from 'react'
import ReactDOM from 'react-dom'
import Footer from './component/footer'
import Header from './component/header'

const mockUser = {
    userId: 111,
    userEmail: 'anastasia.andruhovich@gmail.com',
    userRole: 'customer'
}

ReactDOM.render(
    <div>
        <Header userEmail={mockUser.userEmail}/>
        <Footer/>
    </div>,
    document.getElementById('react')
)