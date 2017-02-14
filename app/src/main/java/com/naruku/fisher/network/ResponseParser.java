package com.naruku.fisher.network;

public interface ResponseParser {

	public <T> T parse(String response);
	
}
