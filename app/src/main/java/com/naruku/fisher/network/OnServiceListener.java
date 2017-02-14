package com.naruku.fisher.network;

public interface OnServiceListener {

	public void onSuccess(int serviceIdentifier, Object response);
	public void onError(int serviceIdentifier, NetworkError networkError);
}
