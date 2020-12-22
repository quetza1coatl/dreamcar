import React from 'react'
import Modal from 'react-bootstrap/Modal'
import ModalHeader from 'react-bootstrap/ModalHeader'
import ModalTitle from 'react-bootstrap/ModalTitle'
import ModalBody from 'react-bootstrap/ModalBody'
import ModalFooter from 'react-bootstrap/ModalFooter'

import Button from 'react-bootstrap/Button'

import DatePicker from 'react-datepicker'
import 'react-datepicker/dist/react-datepicker.css'

const RequestEditorComponent = ({isShow, expirationDate, onSaveExpirationDate, onSelectExpirationDate, closeHandler}) => (
    <Modal show={isShow} onHide={closeHandler} style={{opacity:1, paddingTop: '15px'}}>
        <ModalHeader closeButton>
            <ModalTitle>Edit request</ModalTitle>
        </ModalHeader>

        <ModalBody>
            <span>
                <label>Expiration date: </label>
                <DatePicker selected={expirationDate} onSelect={onSelectExpirationDate}/>
            </span>

        </ModalBody>

        <ModalFooter>
            <Button variant="secondary" onClick={closeHandler}>Close</Button>
            <Button variant="primary" onClick={onSaveExpirationDate}>Save changes</Button>
        </ModalFooter>
    </Modal>
);

export default RequestEditorComponent