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
import io.vertx.core.cli.annotations.Description;
import org.abvijay.bozobooklibrary.bookinfoservice.objects.BookInfoSearchResponse;
import org.eclipse.microprofile.graphql.Query;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/bookinfo")
public class BookInfoService {

	public BookInfoSearchResponse searchByKeyword(String query) {
		BookInfoSearchResponse resp = new BookInfoSearchResponse();
		System.out.println("Query "+ query);
		String responseJson = "";
		try {
			String url = "https://www.googleapis.com/books/v1/volumes?q=" + query
					+ "&key=AIzaSyCaJXWqYZkFfvz6dSeIdEnaCb-xO0_xO40";

			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

			HttpResponse<String> response;
			response = client.send(request, BodyHandlers.ofString());
			 responseJson = response.body();

			ObjectMapper objMapper = new ObjectMapper();
			resp = objMapper.readValue(responseJson, BookInfoSearchResponse.class);
			System.out.println(resp.getTotalItems());

		} catch (Exception e) {
			responseJson = "{'error', '" + e.getMessage() + "'}";
			e.printStackTrace();
		}
		return  resp;    
	}

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/search/{query}")
    public String search(@PathParam String query) {
		String responseJson = "{}";
		try {
			String url = "https://www.googleapis.com/books/v1/volumes?q=" + query
					+ "&key=AIzaSyCaJXWqYZkFfvz6dSeIdEnaCb-xO0_xO40";

			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

			HttpResponse<String> response;
			response = client.send(request, BodyHandlers.ofString());
			responseJson = response.body();

			ObjectMapper objMapper = new ObjectMapper();
			BookInfoSearchResponse resp = objMapper.readValue(responseJson, BookInfoSearchResponse.class);
			System.out.println(resp.getTotalItems());

		} catch (Exception e) {
			responseJson = "{'error', '" + e.getMessage() + "'}";
			e.printStackTrace();
		}
		return responseJson;    
	}
}