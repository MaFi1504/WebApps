package Rest;

import javax.annotation.Resource;
import javax.ejb.PostActivate;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.*;
import javax.transaction.NotSupportedException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.net.URI;

@Path("/project")
public class Project {
    @PersistenceContext(unitName = "JPA_ExamplePU")
    private EntityManager em;
    @GET

    @Produces("application/json")
    public String get() {
        return "Hello, World!";
    }


    @POST
    public void post(String data){

    }

    @Resource
    private UserTransaction utx;

    @POST
    @Consumes(PageAttributes.MediaType.APPLICATION_JSON)
    @Produces(PageAttributes.MediaType.APPLICATION_JSON)
    public Response create(ProjektAdapter pa) {

        try {
            this.utx.begin();
            // Useing adapter to create a persistable object
            Database.Project proj = pa.toProject();
            this.em.persist(proj);          // em speichert in die Tabelle tbl_Project
            this.utx.commit();

            URI location = URI.create("/projekt?id=" + proj.getId());  // hier kann man das angelegte abrufen
            Response.ResponseBuilder rb = Response.created(location);   // response 201 für cerated
            // Example for createing a HATEOAS link
            URI delLocLink = URI.create("/projekt/delete?id=" + proj.getId()); //hier kann man das angelegte löschen
            rb.link(delLocLink, "delete");
            return rb.build();

        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            // Better to add a error message here...
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)

    public Response get(@QueryParam("id") Long id) {

        Project proj = this.em.find(Project.class, id);  // holt JPA-Objekt mit id = ? aus der DB
        Response.ResponseBuilder rb = Response.ok(proj);

        return rb.build();
    }

}
