package org.abvijay.bozobooklibrary.bookinfoservice;

import io.vertx.core.cli.annotations.Description;
import javax.inject.Inject;
import org.abvijay.bozobooklibrary.bookinfoservice.objects.BookInfoSearchResponse;

import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Query;

@GraphQLApi
public class BookInfoResource {
    @Inject 
    BookInfoService service;


    @Query("search")
    @Description("Search the books based on a keyword")
    public BookInfoSearchResponse search(String keyword){
        return service.searchByKeyword(keyword);
    }
}
