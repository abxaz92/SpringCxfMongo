package local.david.service.common;

import local.david.service.model.pojo.User;
import org.codehaus.jackson.JsonNode;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;

import javax.ws.rs.core.Response;
import java.io.IOException;

/**
 * Created by [david] on 23.11.16.
 */
@org.springframework.stereotype.Service
public abstract class AbstractDAO<E extends Entity> extends
        AbstractBaseService<E> {

    protected Class<E> type;

    public AbstractDAO(Class<E> type) {
        super(type);
    }

    public E getById(String id, User user) {
        return findById(id, user);
    }

    public Object getByQuery(String jsonQuery, String count, Integer skip,
                             Integer limit, String sortProperties, String sortDirection,
                             User user) {
        if (jsonQuery != null) {
            if (count == null) {
                return find(new BasicQuery(jsonQuery), user, skip, limit,
                        createSort(sortProperties, sortDirection));
            } else {
                return count(new BasicQuery(jsonQuery), user);
            }
        }
        if (count == null) {
            return find(user, skip, limit,
                    createSort(sortProperties, sortDirection));
        } else {
            return count(user);
        }
    }

    public Object getByQueryNew(String jsonQuery, String count, Integer skip,
                                Integer limit, String sortProperties, String sortDirection,
                                User user) {
        if (jsonQuery != null) {
            Query query = geteUserScopeQuery(jsonQuery, user);
            if (count == null) {
                return find(query, null, skip, limit,
                        createSort(sortProperties, sortDirection));
            } else {
                return count(query, null);
            }
        }
        if (count == null) {
            return find(user, skip, limit,
                    createSort(sortProperties, sortDirection));
        } else {
            return count(user);
        }
    }

    public void post(E entity, User user) {
        prepareEntityForSaving(entity, user);
        insert(entity);
    }

    public E put(JsonNode json, String id, User user) {
        E entity = findAndMerge(json, id, user);
        save(entity);
        return entity;
    }

    public E findAndMerge(JsonNode json, String id, User user) {
        E old = findById(id, user);
        if (old == null)
            throw exception(Response.Status.NOT_FOUND, "Document not found");
        JsonNode res = Service.merge(Service.MAPPER.convertValue(old, JsonNode.class), json);
        try {
            return Service.MAPPER.treeToValue(res, super.type);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return null;
    }

    protected abstract void prepareEntityForSaving(E entity, User user);

    protected Query geteUserScopeQuery(String jsonQuery, User user) {
        Query query = new BasicQuery(jsonQuery);
        query.addCriteria(getUserScopeCriteria(user));
        return query;
    }

    public void deleteById(String id, User user) {
        E entity = findById(id, user);
        if (entity == null) {
            throw exception(Response.Status.NOT_FOUND, "Document not found");
        }
        remove(entity);
    }
}