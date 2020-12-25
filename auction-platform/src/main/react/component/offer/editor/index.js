import React from 'react'
import Modal from 'react-bootstrap/Modal'
import ModalHeader from 'react-bootstrap/ModalHeader'
import ModalTitle from 'react-bootstrap/ModalTitle'
import ModalBody from 'react-bootstrap/ModalBody'
import ModalFooter from 'react-bootstrap/ModalFooter'

import Button from 'react-bootstrap/Button'

const OfferEditorComponent = ({isShow, price, onSavePrice, onChangePrice, closeHandler}) => (
    <Modal show={isShow} onHide={closeHandler} style={{opacity: 1, paddingTop: '15px'}}>
        <ModalHeader closeButton>
            <ModalTitle>Edit request</ModalTitle>
        </ModalHeader>

        <ModalBody>
            <span>
                <label>Price: </label>
                <input type="number" placeholder="1,000.00 $" value={price} min="0" step="0.01" data-number-to-fixed="2"
                       data-number-stepfactor="100" className="currency" onChange={onChangePrice}/>
            </span>
        </ModalBody>

        <ModalFooter>
            <Button variant="secondary" onClick={closeHandler}>Close</Button>
            <Button variant="primary" onClick={onSavePrice}>Save changes</Button>
        </ModalFooter>
    </Modal>
);

export default OfferEditorComponent