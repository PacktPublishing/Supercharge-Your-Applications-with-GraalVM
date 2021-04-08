package com.abvijay.chapter9.quarkus;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/bookinfo")
public class BookInfoService {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/getBookInfo/{query}")
    public String getBookInfo(@PathParam String query) {
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
		return responseJson;    }
}