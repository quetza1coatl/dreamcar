import React from 'react'

import OfferCreatorComponent from '../../../component/offer/creator'

class OfferCreator extends React.Component {

    constructor(props) {
        super(props)
        this.state = {
            price: 0,
            description: ''
        }
    }

    onChangePrice = (e) => {
        this.setState({
            price: e.target.value
        })
    };
    onChangeDescription = (e) => {
        this.setState({
            description: e.target.value
        })
    };

    onCreateOffer = () => {
        this.props.onCreateOffer(this.state.price, this.state.description)
    };

    onClose = () => {
        this.setState({
            price: 0,
            description: ''
        })
        this.props.onCreateOfferClose()
    };

    render() {
        return <span>
            <OfferCreatorComponent isShow={this.props.isShow}
                                   price={this.state.price}
                                   description={this.state.description}
                                   onChangePrice={this.onChangePrice}
                                   onChangeDescription={this.onChangeDescription}
                                   createHandler={this.onCreateOffer}
                                   closeHandler={this.onClose}/>
        </span>
    }
}

export default OfferCreator