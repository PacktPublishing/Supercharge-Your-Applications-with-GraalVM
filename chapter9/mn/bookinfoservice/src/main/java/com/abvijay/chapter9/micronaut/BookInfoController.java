package com.abvijay.chapter9.micronaut;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/bookinfo")
public class BookInfoController {

    @Get("get-info") 
    public String getBookInfo(String query) {
        BookInfoService svc = new BookInfoService();
        String ret = svc.fetch(query);
        return ret;
    }   
}
