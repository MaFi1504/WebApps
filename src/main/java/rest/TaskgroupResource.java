package rest;

import database.TaskGroup;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/taskgroup")
public class TaskgroupResource {

    @GET
    @Produces("application/json")
    public Response get() {
        Response.ResponseBuilder builder = Response.ok(TaskGroup.getList());
        return builder.build();
    }

    @Path("/id/{id}")
    @GET
    @Produces("application/json")
    public Response getSpecific(@PathParam("id") int id) {
        Response.ResponseBuilder builder = Response.ok(TaskGroup.getTaskGroupById(id));
        return builder.build();
    }

    @Path("/title/{title}")
    @GET
    @Produces("application/json")
    public Response getName(@PathParam("title") String title) {
        Response.ResponseBuilder builder = Response.ok(TaskGroup.getTaskGroupByTitle(title));
        return builder.build();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response post(TaskGroup tg) {
        tg.addToDatabase();
        Response.ResponseBuilder rb = Response.ok(tg);
        return rb.build();
    }
}