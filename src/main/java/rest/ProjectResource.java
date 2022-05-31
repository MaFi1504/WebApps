package rest;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import database.Project;
import database.ProjectAdapter;

import java.util.ArrayList;

@Path("/project")
public class ProjectResource {

    @GET
    @Produces("application/json")
    public Response get() {
        Response.ResponseBuilder builder = Response.ok(Project.getList());
        return builder.build();
    }

    @Path("/id/{id}")
    @GET
    @Produces("application/json")
    public Response getSpecific(@PathParam("id") int id) {
        Response.ResponseBuilder builder = Response.ok(Project.getProjectById(id));
        return builder.build();
    }

    @Path("/title/{title}")
    @GET
    @Produces("application/json")
    public Response getName(@PathParam("title") String title) {
        Response.ResponseBuilder builder = Response.ok(Project.getProjectByTitle(title));
        return builder.build();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response post(ProjectAdapter pa) {
        Project proj = pa.toProject();
        proj.addToDatabase();
        Response.ResponseBuilder rb = Response.ok(proj);
        return rb.build();
    }
}