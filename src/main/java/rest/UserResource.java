package rest;

import database.User;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/user")
public class UserResource {

    @GET
    @Produces("application/json")
    public Response get() {
        Response.ResponseBuilder builder = Response.ok(User.getList());
        return builder.build();
    }

    @Path("/id/{id}")
    @GET
    @Produces("application/json")
    public Response getSpecific(@PathParam("id") int id) {
        Response.ResponseBuilder builder = Response.ok(User.getUserById(id));
        return builder.build();
    }

    @Path("/username/{username}")
    @GET
    @Produces("application/json")
    public Response getUsername(@PathParam("username") String username) {
        Response.ResponseBuilder builder = Response.ok(User.getUserByName(username));
        return builder.build();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response post(User user) {
        user.addToDatabase();
        Response.ResponseBuilder rb = Response.ok(user);
        return rb.build();
    }
}