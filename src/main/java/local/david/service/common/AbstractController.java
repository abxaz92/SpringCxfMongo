package local.david.service.common;

import local.david.service.model.pojo.User;
import org.codehaus.jackson.JsonNode;
import org.slf4j.Logger;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;

/**
 * Created by [david] on 23.11.16.
 */
public abstract class AbstractController<T extends Entity> {
    protected Class type;

    public AbstractController(Class<T> type) {
        this.type = type;
    }

    protected abstract AbstractDAO<T> getDao();

    protected abstract Logger getLogger();

    protected abstract User getCurentUser();

    @GET
    @Path("/{id}")
    @RolesAllowed({"*", "MANAGER", "DISPATCHER", "OBSERVER", "SENIORDISP"})
    public T getById(@PathParam("id") String id) {
        return getDao().getById(id, getCurentUser());
    }

    @GET
    @Path("/")
    @RolesAllowed({"*", "MANAGER", "DISPATCHER", "OBSERVER", "PAYMASTER", "SENIORDISP"})
    public Object getByQuery(@QueryParam("query") String jsonQuery,
                             @QueryParam("count") String count,
                             @QueryParam("skip") Integer skip,
                             @QueryParam("limit") Integer limit,
                             @QueryParam("sort") String sortProperties,
                             @QueryParam("direction") String sortDirection) throws IOException {
        return getDao().getByQueryNew(jsonQuery, count, skip, limit, sortProperties, sortDirection, getCurentUser());
    }

    @POST
    public Response post(T entity) {
        User user = getCurentUser();
        getDao().post(entity, user);
        if (user != null)
            getLogger().info("{} : {}", user.getName(), entity.getId());
        else
            getLogger().info("{}", entity.getId());
        return Response.noContent().header("Location", entity.getId()).build();
    }

    @PUT
    @Path("/{id}")
    public T put(JsonNode obj, @PathParam("id") String id) {
        User user = getCurentUser();
        if (user != null)
            getLogger().info("{} : {}", user.getName(), id);
        else
            getLogger().info("{}", id);
        return getDao().put(obj, id, user);
    }

    @DELETE
    @Path("/{id}")
    public void deleteById(@PathParam("id") String id) {
        User user = getCurentUser();
        if (user != null)
            getLogger().info("{} : {}", user.getName(), id);
        else
            getLogger().info("{}", id);
        getDao().deleteById(id, user);
    }

}
