package org.abvijay.bozobooklibrary.bookinfoservice;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.quarkus.redis.client.RedisClient;
import io.quarkus.redis.client.reactive.ReactiveRedisClient;
import io.vertx.redis.client.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;

import org.abvijay.bozobooklibrary.bookinfoservice.objects.BookInfoSearchResponse;
import org.abvijay.bozobooklibrary.bookinfoservice.objects.BookItem;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/bookinfo")
public class BookInfoService {
	@Inject
    RedisClient redisClient;

	@Inject
    ReactiveRedisClient reactiveRedisClient; 

	public BookInfoSearchResponse searchByKeyword(String query, int page) {
		BookInfoSearchResponse resp = new BookInfoSearchResponse();
		System.out.println("Query "+ query);
		String responseJson = "";
		try {
			String url = "https://www.googleapis.com/books/v1/volumes?q=" + query
					+ "&key=AIzaSyCaJXWqYZkFfvz6dSeIdEnaCb-xO0_xO40"
					+ "&maxResults=10"
					+ "&startIndex="+ page*10;

			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

			HttpResponse<String> response;
			response = client.send(request, BodyHandlers.ofString());
			 responseJson = response.body();

			ObjectMapper objMapper = new ObjectMapper();
			resp = objMapper.readValue(responseJson, BookInfoSearchResponse.class);
			System.out.println(resp.getTotalItems());

			for(int i=0; i< resp.getItems().size(); i++) {
				BookItem item = resp.getItems().get(i);
				String itemJson = objMapper.writeValueAsString(item);

				Response cachedItem  = redisClient.get(item.getId());
				if(cachedItem != null ) {
					System.out.println("Found");
				} else {
					System.out.println("Not found");
				}

				
				redisClient.set(Arrays.asList(item.getId(), itemJson));
			}
			

		} catch (Exception e) {
			responseJson = "{'error', '" + e.getMessage() + "'}";
			e.printStackTrace();
		}
		return  resp;    
	}


	public BookInfoSearchResponse getBooks(List<String> bookids) {
		BookInfoSearchResponse resp = new BookInfoSearchResponse();

		List<BookItem> items = new ArrayList<BookItem>();
		String responseJson = "";
		try {
			ObjectMapper objMapper = new ObjectMapper();
			for (int i=0; i<bookids.size(); i++) {

				Response cachedItem  = redisClient.get(bookids.get(i));
				if(cachedItem != null ) {
					System.out.println("Found " + cachedItem.toString());
					BookItem item = objMapper.readValue(cachedItem.toString(), BookItem.class);
					items.add(item);
				} else {
					System.out.println("Not found");
					String url = "https://www.googleapis.com/books/v1/volumes/" + bookids.get(i)
					+ "?key=AIzaSyCaJXWqYZkFfvz6dSeIdEnaCb-xO0_xO40";
	
					HttpClient client = HttpClient.newHttpClient();
					HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
					HttpResponse<String> response;
					
					response = client.send(request, BodyHandlers.ofString());
					responseJson = response.body();
					
					BookItem item = objMapper.readValue(responseJson, BookItem.class);
					items.add(item);

					String itemJson = objMapper.writeValueAsString(item);
					redisClient.set(Arrays.asList(item.getId(), itemJson));
				}

			}

		} catch (Exception e) {
			responseJson = "{'error', '" + e.getMessage() + "'}";
			e.printStackTrace();
		}
		resp.setTotalItems(items.size());
		resp.setItems(items);
		return  resp;    
	}


/*
	@GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/search/{query}/{page}")
    public String search(@PathParam String query, @PathParam int page) {
		String responseJson = "{}";
		try {
			String url = "https://www.googleapis.com/books/v1/volumes?q=" + query
					+ "&key=AIzaSyCaJXWqYZkFfvz6dSeIdEnaCb-xO0_xO40"
					+ "&maxResults=10"
					+ "&startIndex="+ page*10;

			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

			HttpResponse<String> response;
			response = client.send(request, BodyHandlers.ofString());
			responseJson = response.body();

			ObjectMapper objMapper = new ObjectMapper();
			BookInfoSearchResponse resp = objMapper.readValue(responseJson, BookInfoSearchResponse.class);
			System.out.println(resp.getTotalItems());

			for(int i=0; i< resp.getItems().size(); i++) {
				BookItem item = resp.getItems().get(i);
				String itemJson = objMapper.writeValueAsString(item);

				Response cachedItem  = redisClient.get(item.getId());
				if(cachedItem != null ) {
					System.out.println("Found");
				} else {
					System.out.println("Not found");
				}

				
				redisClient.set(Arrays.asList(item.getId(), itemJson));
			}
			


		} catch (Exception e) {
			responseJson = "{'error', '" + e.getMessage() + "'}";
			e.printStackTrace();
		}
		return responseJson;    
	}

	private void cacheBookItems(List<BookItem> items) {
		
	}
	*/
}