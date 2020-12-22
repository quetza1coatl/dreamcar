import React from 'react'
import {Link} from 'react-router-dom'

import Table from '../../../component/table'
import RequestEditor from '../editor'
import OfferCreator from '../../offer/creator'

const mockData = [
    {
        id: 1,
        partName: 'suspension',
        quantity: 10,
        description: 'bla',
        customerEmail: 'rtest@asd.com',
        creationDate: '11.04.2020',
        expirationDate: '',
        status: 'draft',
        isEditable: true,
        isOfferCreated: false
    },
    {
        id: 2,
        partName: 'suspension',
        quantity: 5,
        description: 'bla',
        customerEmail: 'rtesrfdft@asd.com',
        creationDate: '15.04.2020',
        expirationDate: '',
        status: 'draft',
        isEditable: true,
        isOfferCreated: true
    },
    {
        id: 3,
        partName: 'suspension',
        quantity: 5,
        description: 'bla',
        customerEmail: 'rtesrfdft@asd.com',
        creationDate: '15.04.2020',
        expirationDate: '',
        status: 'draft',
        isEditable: true,
        isOfferCreated: true
    },
    {
        id: 4,
        partName: 'suspension',
        quantity: 5,
        description: 'bla',
        customerEmail: 'rtesrfdft@asd.com',
        creationDate: '15.04.2020',
        expirationDate: '',
        status: 'draft',
        isEditable: true,
        isOfferCreated: true
    },
    {
        id: 5,
        partName: 'suspension',
        quantity: 5,
        description: 'bla',
        customerEmail: 'rtesrfdft@asd.com',
        creationDate: '15.04.2020',
        expirationDate: '',
        status: 'draft',
        isEditable: true,
        isOfferCreated: true
    },
    {
        id: 6,
        partName: 'suspension',
        quantity: 5,
        description: 'bla',
        customerEmail: 'rtesrfdft@asd.com',
        creationDate: '15.04.2020',
        expirationDate: '',
        status: 'draft',
        isEditable: true,
        isOfferCreated: true
    },
    {
        id: 7,
        partName: 'suspension',
        quantity: 5,
        description: 'bla',
        customerEmail: 'rtesrfdft@asd.com',
        creationDate: '15.04.2020',
        expirationDate: '',
        status: 'draft',
        isEditable: true,
        isOfferCreated: true
    },
    {
        id: 8,
        partName: 'suspension',
        quantity: 5,
        description: 'bla',
        customerEmail: 'rtesrfdft@asd.com',
        creationDate: '15.04.2020',
        expirationDate: '',
        status: 'draft',
        isEditable: true,
        isOfferCreated: true
    },
    {
        id: 9,
        partName: 'suspension',
        quantity: 5,
        description: 'bla',
        customerEmail: 'rtesrfdft@asd.com',
        creationDate: '15.04.2020',
        expirationDate: '',
        status: 'draft',
        isEditable: true,
        isOfferCreated: true
    },
    {
        id: 10,
        partName: 'suspension',
        quantity: 5,
        description: 'bla',
        customerEmail: 'rtesrfdft@asd.com',
        creationDate: '15.04.2020',
        expirationDate: '',
        status: 'draft',
        isEditable: true,
        isOfferCreated: true
    },
    {
        id: 11,
        partName: 'suspension',
        quantity: 5,
        description: 'bla',
        customerEmail: 'rtesrfdft@asd.com',
        creationDate: '15.04.2020',
        expirationDate: '',
        status: 'draft',
        isEditable: true,
        isOfferCreated: true
    },
    {
        id: 12,
        partName: 'suspension',
        quantity: 5,
        description: 'bla',
        customerEmail: 'rtesrfdft@asd.com',
        creationDate: '15.04.2020',
        expirationDate: '',
        status: 'draft',
        isEditable: true,
        isOfferCreated: true
    },
    {
        id: 13,
        partName: 'suspension',
        quantity: 5,
        description: 'bla',
        customerEmail: 'rtesrfdft@asd.com',
        creationDate: '15.04.2020',
        expirationDate: '',
        status: 'draft',
        isEditable: true,
        isOfferCreated: true
    },
    {
        id: 14,
        partName: 'suspension',
        quantity: 5,
        description: 'bla',
        customerEmail: 'rtesrfdft@asd.com',
        creationDate: '15.04.2020',
        expirationDate: '',
        status: 'draft',
        isEditable: true,
        isOfferCreated: true
    },
    {
        id: 15,
        partName: 'suspension',
        quantity: 5,
        description: 'bla',
        customerEmail: 'rtesrfdft@asd.com',
        creationDate: '15.04.2020',
        expirationDate: '',
        status: 'draft',
        isEditable: true,
        isOfferCreated: true
    },
    {
        id: 16,
        partName: 'suspension',
        quantity: 5,
        description: 'bla',
        customerEmail: 'rtesrfdft@asd.com',
        creationDate: '15.04.2020',
        expirationDate: '',
        status: 'draft',
        isEditable: true,
        isOfferCreated: true
    }
]

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
                {row.isEditable ?  <a href="javascript:void(0)" onClick={() => this.onEditRequestClick(row.id)}>
                    Edit <i className="glyphicon glyphicon-pencil"/>
                </a> : null}
                    {row.isOfferCreated ? <a href="javascript:void(0)" onClick={() => this.onCreateOfferClick(row.id)}>
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
        this.setState({
            requestData: mockData
        })

        /*fetch("/api/requests")
            .then(res => res.json())
            .then(
                (result) => {
                    this.setState({
                        requestData: result.requestData,
                    });
                },
                (error) => {
                    console.log(error)
                }
            )*/
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
        /*fetch("/api/updateExpirationDate", {
            method: "POST",
            body: {
                requestId: this.state.editableRequestId,
                expirationDate: expirationDate
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