package com.zoecasheth.app.repository;

import com.zoecasheth.app.entity.NetworkInfo;

public interface OnNetworkChangeListener {
	void onNetworkChanged(NetworkInfo networkInfo);
}
