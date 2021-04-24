package org.abvijay.bozobooklibrary.bookinfoservice;

import io.vertx.core.cli.annotations.Description;
import java.util.List;
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
    public BookInfoSearchResponse search(String keyword, int page){
        return service.searchByKeyword(keyword, page);
    }

    @Query("getBooks")
    @Description("Search the books based on a keyword")
    public BookInfoSearchResponse getBooks(List<String> bookids){
        return service.getBooks(bookids);
    }
}
