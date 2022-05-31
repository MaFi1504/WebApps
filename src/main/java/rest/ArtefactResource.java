package rest;

import database.Artefact;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.*;
import javax.transaction.NotSupportedException;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.ArrayList;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
@Path("/artefact")
public class ArtefactResource {

    @PersistenceContext(unitName = "ProjectPU")
    private EntityManager em;

    @Resource
    private UserTransaction utx;

    @GET
    @Produces("application/json")
    public Response get() {
        ArrayList<Artefact> artefacts;
        artefacts = (ArrayList<Artefact>) em.createNamedQuery("artefact.getList").getResultList();
        Response.ResponseBuilder builder = Response.ok(artefacts);
        return builder.build();
    }

    @Path("/id/{id}")
    @GET
    @Produces("application/json")
    public Response getSpecific(@PathParam("id") int id) {
        Artefact artefact = em.find(Artefact.class, id);
        Response.ResponseBuilder builder = Response.ok(artefact);
        return builder.build();
    }

    @Path("/title/{title}")
    @GET
    @Produces("application/json")
    public Response getName(@PathParam("title") String title) {
        Artefact artefact = em.find(Artefact.class, title);
        Response.ResponseBuilder builder = Response.ok(artefact);
        return builder.build();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response post(Artefact artefact) {
        try{
            utx.begin();
            em.persist(artefact);
            utx.commit();
            URI location = URI.create("/artefact/id/" + artefact.getId());

            Response.ResponseBuilder rb = Response.created(location);
            URI delLocLink = URI.create("/project/delete?id=" + artefact.getId());
            rb.link(delLocLink, "delete");
            return rb.build();

        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            System.out.println(ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Path("/delete")
    @DELETE
    @Produces("application/json")
    public Response delete(@QueryParam("id") int id) {
        try {
            utx.begin();
            Artefact artefact = em.find(Artefact.class, id);
            em.remove(artefact);
            utx.commit();
            return Response.ok().build();
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            System.out.println(ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}