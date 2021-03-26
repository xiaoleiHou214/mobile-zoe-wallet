package com.zoecasheth.app.ui.widget;

import java.io.Serializable;

import com.zoecasheth.app.entity.DApp;

public interface OnDappClickListener extends Serializable {
    void onDappClick(DApp dapp);
}
