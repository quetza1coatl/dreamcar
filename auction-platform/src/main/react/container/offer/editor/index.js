import React from 'react'

import OfferEditorComponent from '../../../component/offer/editor'

class OfferEditor extends React.Component {

    constructor(props) {
        super(props)
        this.state = {
            price: 0
        }
    }

    onChangePrice = (e) => {
        this.setState({
            price: e.target.value
        })
    };

    onSavePrice = () => {
        this.props.onSavePrice(this.state.price)
    };

    onClose = () => {
        this.setState({
            price: 0
        })
        this.props.onEditOfferClose()
    };

    render() {
        return <span>
            <OfferEditorComponent isShow={this.props.isShow}
                                  price={this.state.price}
                                  onChangePrice={this.onChangePrice}
                                  onSavePrice={this.onSavePrice}
                                  closeHandler={this.onClose}/>
        </span>
    }
}

export default OfferEditor