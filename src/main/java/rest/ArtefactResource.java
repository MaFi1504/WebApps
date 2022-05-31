package rest;

import database.Artefact;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/artefact")
public class ArtefactResource {

    @GET
    @Produces("application/json")
    public Response get() {
        Response.ResponseBuilder builder = Response.ok(Artefact.getList());
        return builder.build();
    }

    @Path("/id/{id}")
    @GET
    @Produces("application/json")
    public Response getSpecific(@PathParam("id") int id) {
        Response.ResponseBuilder builder = Response.ok(Artefact.getArtefactById(id));
        return builder.build();
    }

    @Path("/title/{title}")
    @GET
    @Produces("application/json")
    public Response getName(@PathParam("title") String title) {
        Response.ResponseBuilder builder = Response.ok(Artefact.getArtefactByTitle(title));
        return builder.build();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response post(Artefact artefact) {
        artefact.addToDatabase();
        Response.ResponseBuilder rb = Response.ok(artefact);
        return rb.build();
    }
}