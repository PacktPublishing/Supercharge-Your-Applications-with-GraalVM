package com.abvijay.chapter9.spring.bookinfoservice;

import org.springframework.web.bind.annotation.RestController;


import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class BookInfoServiceController {

	@RequestMapping("/book-info")
	public String bookInfo(@RequestParam String query) {
		String responseJson = "{}";
		try {
			String url = "https://www.googleapis.com/books/v1/volumes?q=" + query
					+ "&key=AIzaSyCaJXWqYZkFfvz6dSeIdEnaCb-xO0_xO40";


			CloseableHttpClient client = HttpClientBuilder.create().build();    
			CloseableHttpResponse response =  client.execute(new HttpGet(url));
			responseJson = EntityUtils.toString(response.getEntity());

		} catch (Exception e) {
			responseJson = "{'error', '" + e.getMessage() + "'}";
			e.printStackTrace();
		}
		return responseJson;    
	}

}