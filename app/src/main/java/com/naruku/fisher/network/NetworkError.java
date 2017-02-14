package com.naruku.fisher.network;
import java.net.HttpURLConnection;

public class NetworkError extends Exception {
	
	/** 
	 * Error code returned by the service call. One of 
	 * the constants from {@link HttpURLConnection}
	 */
	private int errorCode;
	
	/**
	 * Status code returned by the server. This value
	 * can be populated only after parsing of the 
	 * error response is complete.
	 */
	private int statusCode;
	
	/** 
	 * Contains message received form web service. 
	 */
	private String errorMessage;

	/**
	 * Response object holding information regarding the error report
	 * sent by server.
	 */
	private Object errorResponse;
	
	
	/** 
	 * Contains message to be displayed to user. 
	 */
	private String displayMessage;
	
	public NetworkError(int errorCode, int statusCode, String errorMessage, Object errorResponse) {
		this.errorCode = errorCode;
		this.statusCode = statusCode;
		this.errorMessage = errorMessage;
		this.errorResponse = errorResponse;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public Object getErrorResponse() {
		return errorResponse;
	}

	public String getDisplayMessage() {
		return displayMessage;
	}

	public void setDisplayMessage(String displayMessage) {
		this.displayMessage = displayMessage;
	}

}
