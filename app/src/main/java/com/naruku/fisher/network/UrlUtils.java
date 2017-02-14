package com.naruku.fisher.network;

import android.net.Uri;

import java.util.Map;

public class UrlUtils {

	public static String formUrl(String url, Map<String, String> queryParams) {
		Uri.Builder uriBuilder = buildUri(url);
		addPositionalQueryParams(uriBuilder, queryParams);
		return uriBuilder.toString();
	}
	
	private static void addPositionalQueryParams(Uri.Builder uriBuilder, Map<String, String> queryParams) {
		if (queryParams == null || queryParams.size() < 1) {
			return;
		}
		
		for (Map.Entry<String, String> param : queryParams.entrySet()) {
		
			if(param.getValue() != null )
				appendPath(uriBuilder, param.getValue());
			else
				appendPath(uriBuilder,param.getKey());
		}
	}

	private static Uri.Builder buildUri(String url) {
		return Uri.parse(url).buildUpon();
	}

	private static void appendPath(Uri.Builder uriBuilder, String path) {
		uriBuilder.appendPath(path);
	}

}
