import React from 'react'
import Modal from 'react-bootstrap/Modal'
import ModalHeader from 'react-bootstrap/ModalHeader'
import ModalTitle from 'react-bootstrap/ModalTitle'
import ModalBody from 'react-bootstrap/ModalBody'
import ModalFooter from 'react-bootstrap/ModalFooter'

import Button from 'react-bootstrap/Button'

const OfferCreatorComponent = ({isShow, description, price, createHandler, onChangePrice, onChangeDescription, closeHandler}) => (
    <Modal show={isShow} onHide={closeHandler} style={{opacity:1, paddingTop: '15px'}}>
        <ModalHeader closeButton>
            <ModalTitle>Create offer</ModalTitle>
        </ModalHeader>

        <ModalBody>
            <div>
                <label>Price: </label>
                <input type="number" placeholder="1,000.00 $" value={price} min="0" step="0.01" data-number-to-fixed="2"
                       data-number-stepfactor="100" className="currency" onChange={onChangePrice}/>
            </div>
            <div>
                <label>Description: </label>
                <textarea maxLength={3000} value={description} onChange={onChangeDescription}/>
            </div>
        </ModalBody>

        <ModalFooter>
            <Button variant="secondary" onClick={closeHandler}>Close</Button>
            <Button variant="primary" onClick={createHandler}>Create</Button>
        </ModalFooter>
    </Modal>
);

export default OfferCreatorComponent