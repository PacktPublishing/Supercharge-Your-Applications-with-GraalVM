package com.abvijay.chapter9.micronaut;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

/**
 * BookInfoService
 */
public class BookInfoService {

	public BookInfoService() {
	}

	public String fetch(String query) {

		String responseJson = "{}";
		try {
			String url = "https://www.googleapis.com/books/v1/volumes?q=" + query
					+ "&key=AIzaSyCaJXWqYZkFfvz6dSeIdEnaCb-xO0_xO40";

			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

			HttpResponse<String> response;
			response = client.send(request, BodyHandlers.ofString());
			responseJson = response.body();

		} catch (Exception e) {
			responseJson = "{'error', '" + e.getMessage() + "'}";
			e.printStackTrace();
		}
		return responseJson;
	}

}