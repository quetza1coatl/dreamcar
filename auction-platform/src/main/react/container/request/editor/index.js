import React from 'react'

import RequestEditorComponent from '../../../component/request'

class RequestEditor extends React.Component {

    constructor(props) {
        super(props)
        this.state = {
            expirationDate: new Date()
        }
    }

    onSelectExpirationDate = (expirationDate) => {
        this.setState({
            expirationDate
        })
    };

    onSaveExpirationDate = () => {
        this.props.onSaveExpirationDate(this.state.expirationDate)
    }

    onClose = () => {
        this.setState({
            expirationDate: new Date()
        })
        this.props.onEditRequestClose()
    }

    render() {
        return <span>
            <RequestEditorComponent isShow={this.props.isShow}
                                    expirationDate={this.state.expirationDate}
                                    onSelectExpirationDate={this.onSelectExpirationDate}
                                    onSaveExpirationDate={this.onSaveExpirationDate}
                                    closeHandler={this.onClose}/>
        </span>
    }
}

export default RequestEditor