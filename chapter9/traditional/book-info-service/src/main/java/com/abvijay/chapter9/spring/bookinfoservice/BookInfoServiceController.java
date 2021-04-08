package com.abvijay.chapter9.spring.bookinfoservice;

import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class BookInfoServiceController {

	@RequestMapping("/book-info")
	public String bookInfo(@RequestParam String query) {
		String responseJson ="{}";
		try {
			String url = "https://www.googleapis.com/books/v1/volumes?q="+query+"&key=AIzaSyCaJXWqYZkFfvz6dSeIdEnaCb-xO0_xO40";

			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(url))
				.build();
		
			HttpResponse<String> response;
			response = client.send(request, BodyHandlers.ofString());
			responseJson = response.body();

		} catch (Exception e) {
			responseJson = "{'error', '"+e.getMessage()+"'}";
			e.printStackTrace();
		} 
		return responseJson;
	}

}