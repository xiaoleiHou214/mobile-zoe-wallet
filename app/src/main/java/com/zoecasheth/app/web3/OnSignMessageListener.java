package com.zoecasheth.app.web3;

import com.zoecasheth.token.entity.EthereumMessage;

public interface OnSignMessageListener {
    void onSignMessage(EthereumMessage message);
}
