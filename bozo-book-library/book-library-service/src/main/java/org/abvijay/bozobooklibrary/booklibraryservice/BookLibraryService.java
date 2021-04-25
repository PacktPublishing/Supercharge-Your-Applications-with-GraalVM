package org.abvijay.bozobooklibrary.booklibraryservice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/bozolib")
public class BookLibraryService {

    @GET
    @Path("/get/{userId}")
    @Produces(APPLICATION_JSON)
    public List<BookLibrary> getBooksforUser(@QueryParam("userId") String userId) {

        List<BookLibrary> books = BookLibrary.listAll();
        /*
        try {
            ObjectMapper objMapper = new ObjectMapper();

            for (int i = 0; i < books.size(); i++) {
                BookLibrary item = books.get(i);
                String itemJson = objMapper.writeValueAsString(item);

                redisClient.set(Arrays.asList(item.getUserID(), itemJson));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        return books;
    }

    @POST
    @Path("add/{userid}/{bookid}")
    @Transactional
    public Response addBook(@PathParam("userid") String userId, @PathParam("bookid") String bookId) {
        try {
            BookLibrary book = new BookLibrary();
            book.setBookID(bookId);
            book.setUserID(userId);
            System.out.println("UserId " + book.getUserID() + " bookId " + book.getBookID());
            book.persist();
            return Response.ok("success", MediaType.TEXT_PLAIN).build();
        } catch (Exception e) {
            System.out.println("Exception " + e.getMessage());
            return Response.serverError().type(MediaType.APPLICATION_JSON).entity("{" + e.getMessage() + "}").build();

        }
    }

    @POST
    @Path("delete/{userid}/{bookid}")
    @Transactional
    public Response deleteBook(@PathParam("userid") String userId, @PathParam("bookid") String bookId) {
        try {
            
            System.out.println("Deleting UserId " + userId + " bookId " + bookId);
            
            Map<String,String> params=new HashMap<>();
            params.put("userID",userId);
            params.put("bookID",bookId);

            BookLibrary.delete("userID = :userID and bookID = :bookID", params);

            return Response.ok("success", MediaType.TEXT_PLAIN).build();
        } catch (Exception e) {
            System.out.println("Exception " + e.getMessage());
            return Response.serverError().type(MediaType.APPLICATION_JSON).entity("{" + e.getMessage() + "}").build();

        }
    }
}
