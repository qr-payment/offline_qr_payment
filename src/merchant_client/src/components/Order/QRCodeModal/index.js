import React from 'react';
import Modal from 'react-modal';
import { inject, observer } from "mobx-react";
import QRCode from 'qrcode.react';
import CloseButtom from './CloseButton';

const customStyles = {
  content : {
    top                   : '50%',
    left                  : '50%',
    right                 : 'auto',
    bottom                : 'auto',
    marginRight           : '-50%',
    transform             : 'translate(-50%, -50%)'
  }
};

@inject("stores")
@observer
class QRCodeModal extends React.Component {
  constructor() {
    super();
    this.afterOpenModal = this.afterOpenModal.bind(this);
    this.closeModal = this.closeModal.bind(this);
  }

  conponentDidMount() {
    Modal.setAppElement('body');
    const { view } = this.props.stores;
  }
 
  afterOpenModal() {

    // references are now sync'd and can be accessed.
    // this.subtitle.style.color = '#f00';

  }
 
  closeModal() {

    const { order } = this.props.stores;
    order.hideQRCodeModal();

  }

  urlGenerate = () => {

    const { order } = this.props.stores;
    const url = order.getOrderURL();
    
    return url;

  }
 
  render() {

    const { qrCodeModal } = this.props.stores.order;
    const url = this.urlGenerate();
    
    return (
        <Modal
          isOpen={qrCodeModal}
          onAfterOpen={this.afterOpenModal}
          onRequestClose={this.closeModal}
          style={customStyles}
          contentLabel="QR Code Scan"
          ariaHideApp={false}
        >
          <div className="center">
            <QRCode value={url} level={"L"} includeMargin={true} size={512}/>
          </div>
          <div className="center">
            <CloseButtom/>
          </div>
        </Modal>
    );
  }
}

export default QRCodeModal;