package rest;

import database.User;

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
@Path("/user")
public class UserResource {

    @PersistenceContext(unitName = "ProjectPU")
    private EntityManager em;

    @Resource
    private UserTransaction utx;

    @GET
    @Produces("application/json")
    public Response get() {
        ArrayList<User> users;
        users = (ArrayList<User>) em.createNamedQuery("user.getAll").getResultList();
        Response.ResponseBuilder builder = Response.ok(users);
        return builder.build();
    }

    @Path("/id/{id}")
    @GET
    @Produces("application/json")
    public Response getSpecific(@PathParam("id") int id) {
        User user = em.find(User.class, id);
        Response.ResponseBuilder builder = Response.ok(user);
        return builder.build();
    }

    @Path("/username/{username}")
    @GET
    @Produces("application/json")
    public Response getUsername(@PathParam("username") String username) {
        User user = em.find(User.class, username);
        Response.ResponseBuilder builder = Response.ok(user);
        return builder.build();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response post(User user) {
        try {
            utx.begin();
            em.persist(user);
            utx.commit();

            URI location = URI.create("/user/id/" + user.getId());

            Response.ResponseBuilder rb = Response.created(location);
            URI delLocLink = URI.create("/user/user?id=" + user.getId());
            rb.link(delLocLink, "delete");
            return rb.build();
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException |
                 HeuristicRollbackException | SecurityException | IllegalStateException ex) {
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
            User user = em.find(User.class, id);
            em.remove(user);
            utx.commit();
            return Response.ok().build();
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException |
                    HeuristicRollbackException | SecurityException | IllegalStateException ex) {
                System.out.println(ex.getMessage());
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }
    }

}