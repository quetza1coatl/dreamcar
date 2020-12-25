import React from 'react'
import ReactDOM from 'react-dom'
import Footer from './component/footer'
import Header from './component/header'

const mockUser = {
    userId: 2,
    userEmail: 'supplier@test.com',
    userRole: 'supplier'
}

ReactDOM.render(
    <div>
        <Header userEmail={mockUser.userEmail}/>
        <Footer/>
    </div>,
    document.getElementById('react')
)