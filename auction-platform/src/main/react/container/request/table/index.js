import React from 'react'
import {Link} from 'react-router-dom'

import Table from '../../../component/table'
import RequestEditor from '../editor'
import OfferCreator from '../../offer/creator'

class RequestTable extends React.Component {
    REQUEST_TABLE_TITLE = 'Requests'
    REQUEST_TABLE_COLUMNS = [
        {
            name: 'Id',
            selector: 'id',
            sortable: true,
        },
        {
            name: 'Part name',
            selector: 'partName',
            sortable: true
        },
        {
            name: 'Quantity',
            selector: 'quantity',
            sortable: true,
        },
        {
            name: 'Description',
            selector: 'description',
            sortable: true
        },
        {
            name: 'Customer email',
            selector: 'customerEmail',
            sortable: true,
        },
        {
            name: 'Creation date',
            selector: 'creationDate',
            sortable: true
        },
        {
            name: 'Expiration date',
            selector: 'expirationDate',
            sortable: true,
        },
        {
            name: 'Status',
            selector: 'status',
            sortable: true
        },
        {
            name: 'Offers',
            cell: row => <Link to={`/offersByRequestId/${row.id}`}>Details</Link>
        },
        {
            name: 'Actions',
            cell: row =>
                <span>
                {row.editable ?  <a href="javascript:void(0)" onClick={() => this.onEditRequestClick(row.id)}>
                    Edit <i className="glyphicon glyphicon-pencil"/>
                </a> : null}
                    {row.offerCreated ? <a href="javascript:void(0)" onClick={() => this.onCreateOfferClick(row.id)}>
                        Create offer <i className="glyphicon glyphicon-plus"/>
                    </a> : null}
            </span>,
            width: '100px'
        }
    ]

    constructor(props) {
        super(props)
        this.state = {
            requestData: [],
            editableRequestId: null,
            isShowRequestEditor: false,
            createOfferRequestId: null,
            isShowOfferCreator: false
        }
    }

    componentDidMount() {
        fetch("/requests")
            .then(res => res.json())
            .then(
                (result) => {
                    this.setState({
                        requestData: result,
                    });
                },
                (error) => {
                    console.log(error)
                }
            )
    }

    onEditRequestClick = (requestId) => {
        this.setState({
            isShowRequestEditor: true,
            editableRequestId: requestId
        })
    };

    onCreateOfferClick = (requestId) => {
        this.setState({
            isShowOfferCreator: true,
            createOfferRequestId: requestId
        })
    };

    onCreateOffer = (price, description) => {
        /*fetch("/api/createOffer", {
            method: "POST",
            body: {
                requestId: this.state.createOfferRequestId,
                price,
                description
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

    onSaveExpirationDate = (expirationDate) => {
        fetch(`/requests/updateRequest/${this.state.editableRequestId}`, {
            method: "POST",
            body: expirationDate.getTime()
        })
            .then(
                () => {
                },
                (error) => {
                    console.log(error)
                }
            )
    };

    onEditRequestClose = () => {
        this.setState({
            isShowRequestEditor: false,
            editableRequestId: null
        })
    };

    onCreateOfferClose = () => {
        this.setState({
            createOfferRequestId: null,
            isShowOfferCreator: false
        })
    };

    render() {
        return <span>
            <Table title={this.REQUEST_TABLE_TITLE}
                   columns={this.REQUEST_TABLE_COLUMNS}
                   data={this.state.requestData}/>
            <RequestEditor isShow={this.state.isShowRequestEditor}
                           onSaveExpirationDate={this.onSaveExpirationDate}
                           onEditRequestClose={this.onEditRequestClose}/>
            <OfferCreator isShow={this.state.isShowOfferCreator}
                          onCreateOffer={this.onCreateOffer}
                          onCreateOfferClose={this.onCreateOfferClose}/>
        </span>
    }
}

export default RequestTable