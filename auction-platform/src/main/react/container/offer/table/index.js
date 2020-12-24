import React from 'react'

import Table from '../../../component/table'
import Button from "react-bootstrap/Button";
import OfferEditor from '../editor'


const mockData = [
    {
        id: 'test1',
        price: 'dfsfdsdfs',
        requestId: 123,
        supplierEmail: 'test@test.com',
        description: 'Wow!',
        status: 'non-processed',
        isEditable: true,
        isApplying: false
    },
    {
        id: 'test2',
        price: 'aaaaaa',
        requestId: 124,
        supplierEmail: 'test2@test.com',
        description: 'Wow!',
        status: 'non-processed',
        isEditable: false,
        isApplying: true
    }
]

class OfferTable extends React.Component {
    OFFER_TABLE_TITLE = 'Offers'
    OFFER_TABLE_COLUMNS = [
        {
            name: 'Id',
            selector: 'id',
            sortable: true,
        },
        {
            name: 'Price',
            selector: 'price',
            sortable: true
        },
        {
            name: 'Request Id',
            selector: 'requestId',
            sortable: true,
        },
        {
            name: 'Supplier email',
            selector: 'supplierEmail',
            sortable: true
        },
        {
            name: 'Description',
            selector: 'description',
            sortable: true,
        },
        {
            name: 'Status',
            selector: 'status',
            sortable: true
        },
        {
            name: 'Actions',
            cell: row =>
                <span>
                {row.editable ? <a href="javascript:void(0)" onClick={() => this.onEditOfferClick(row.id)}>
                    Edit <i className="glyphicon glyphicon-pencil"/>
                </a> : null}
                    {row.applying ? <Button variant="primary" onClick={this.applyOffer(row.id)}>Apply</Button> : null}
            </span>
        }
    ]

    constructor(props) {
        super(props)
        this.state = {
            offerData: [],
            editableOfferId: null,
            isShowOfferEditor: false
        }
    }

    componentDidMount() {
        const {requestId} = this.props.match.params
        const getOffersURL = requestId ? `/offersByRequestId/${requestId}` : `/offers`
        fetch(getOffersURL)
            .then(res => res.json())
            .then(
                (result) => {
                    this.setState({
                        offerData: result,
                    });
                },
                (error) => {
                    console.log(error)
                }
            )
    }

    applyOffer = (offerId) => {
        /*fetch(`/api/applyOffer/${offerId}`)
            .then(res => res.json())
            .then(
                (result) => {
                },
                (error) => {
                    console.log(error)
                }
            )*/
    };

    onEditOfferClick = (offerId) => {
        this.setState({
            isShowOfferEditor: true,
            editableOfferId: offerId
        })
    };

    onSavePrice = (price) => {
        /*fetch("/api/updateExpirationDate", {
            method: "POST",
            body: {
                offerId: this.state.editableOfferId,
                price: price
            }
        })
            .then(res => res.json())
            .then(
                () => {
                },
                (error) => {
                    console.log(error)
                }
            )*/
    };

    onEditOfferClose = () => {
        this.setState({
            isShowOfferEditor: false,
            editableOfferId: null
        })
    };

    render() {
        return <span>
            <Table title={this.OFFER_TABLE_TITLE}
                   columns={this.OFFER_TABLE_COLUMNS}
                   data={this.state.offerData}
                   pagination/>
            <OfferEditor isShow={this.state.isShowOfferEditor}
                         onSavePrice={this.onSavePrice}
                         onEditOfferClose={this.onEditOfferClose}/>
        </span>
    }
}

export default OfferTable