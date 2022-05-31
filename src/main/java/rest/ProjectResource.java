package rest;

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
import database.Project;
import database.ProjectAdapter;

import java.net.URI;
import java.util.ArrayList;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
@Path("/project")
public class ProjectResource {

    @PersistenceContext(unitName = "ProjectPU")
    private EntityManager em;

    @Resource
    private UserTransaction utx;

    @GET
    @Produces("application/json")
    public Response get() {
        ArrayList<Project> projects;
        projects = (ArrayList<Project>) em.createNamedQuery("project.getList").getResultList();
        Response.ResponseBuilder builder = Response.ok(projects);
        return builder.build();
    }

    @Path("/id/{id}")
    @GET
    @Produces("application/json")
    public Response getSpecific(@PathParam("id") int id) {
        Project proj = em.find(Project.class, id);
        Response.ResponseBuilder builder = Response.ok(proj);
        return builder.build();
    }

    @Path("/title/{title}")
    @GET
    @Produces("application/json")
    public Response getName(@PathParam("title") String title) {
        Project proj = em.find(Project.class, title);
        Response.ResponseBuilder builder = Response.ok(proj);
        return builder.build();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response post(ProjectAdapter pa) {
        try {
            utx.begin();
            Project proj = pa.toProject();
            em.persist(proj);
            utx.commit();

            URI location = URI.create("/project/id/" + proj.getId());

            Response.ResponseBuilder rb = Response.created(location);
            URI delLocLink = URI.create("/project/delete?id=" + proj.getId());
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
            Project proj = em.find(Project.class, id);
            em.remove(proj);
            utx.commit();
            return Response.ok().build();
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            System.out.println(ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

}