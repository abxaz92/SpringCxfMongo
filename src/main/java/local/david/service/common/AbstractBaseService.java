package local.david.service.common;

/**
 * Created by [david] on 22.11.16.
 */

import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import local.david.service.model.pojo.User;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;

public abstract class AbstractBaseService<T extends Entity> extends ExceptionFactory {
    public static final Logger LOG = LoggerFactory.getLogger(AbstractBaseService.class);
    @Autowired
    protected MongoTemplate mt;

    protected Class<T> type;


    private FindAndModifyOptions option = new FindAndModifyOptions()
            .returnNew(true);

    public AbstractBaseService(Class<T> type) {
        this.type = type;
    }

    public Long count(User user) {
        if (user == null) {
            return mt.count(new Query(), this.type);
        } else {
            return mt.count(Query.query(getUserScopeCriteria(user)), this.type);
        }
    }

    public Long count(Query query, User user) {
        if (user == null) {
            return mt.count(query, this.type);
        } else {
            return mt.count(query.addCriteria(getUserScopeCriteria(user)), this.type);
        }
    }

    public List<T> find(User user) {
        if (user == null) {
            return mt.findAll(this.type);
        } else {
            return mt.find(Query.query(getUserScopeCriteria(user)), this.type);
        }
    }

    public List<T> find(User user, Integer skip, Integer limit, Sort sort) {
        Query query = null;
        if (user == null) {
            query = new Query();
        } else {
            query = Query.query(getUserScopeCriteria(user));
        }
        if (skip != null && limit != null && skip >= 0 && limit > 0) {
            query = query.skip(skip).limit(limit);
        }
        if (sort != null) {
            query = query.with(sort);
        }
        return mt.find(query, this.type);
    }

    public List<T> find(Query query, User user) {
        if (user == null) {
            return mt.find(query, this.type);
        } else {
            return mt.find(query.addCriteria(getUserScopeCriteria(user)), this.type);
        }
    }

    public List<T> find(Query query, User user, Integer skip, Integer limit, Sort sort) {
        if (user != null) {
            query = query.addCriteria(getUserScopeCriteria(user));
        }
        if (skip != null && limit != null && skip >= 0 && limit > 0) {
            query = query.skip(skip).limit(limit);
        }
        if (sort != null) {
            query = query.with(sort);
        }

        return mt.find(query, this.type);
    }

    public List<T> find(Query query, User user, Integer skip, Integer limit, Sort sort, Class type) {
        if (user != null) {
            query = query.addCriteria(getUserScopeCriteria(user));
        }
        if (skip != null && limit != null && skip >= 0 && limit > 0) {
            query = query.skip(skip).limit(limit);
        }
        if (sort != null) {
            query = query.with(sort);
        }

        return mt.find(query, type);
    }

    public T findOne(Query query, User user) {
        if (user == null) {
            return mt.findOne(query, this.type);
        } else {
            return mt.findOne(query.addCriteria(getUserScopeCriteria(user)), this.type);
        }
    }

    public T findById(String id, User user) {
        if (user == null) {
            return mt.findById(id, this.type);
        } else {
            return mt.findOne(
                    Query.query(Criteria.where("id").is(id))
                            .addCriteria(getUserScopeCriteria(user)), this.type);
        }
    }

    public List<T> findByIds(List<String> ids, User user) {
        if (user == null) {
            return mt.find(Query.query(Criteria.where("id").in(ids)), this.type);
        } else {
            return mt.find(
                    Query.query(Criteria.where("id").in(ids)).addCriteria(
                            getUserScopeCriteria(user)), this.type);
        }
    }

    public List<T> findByIds(Set<String> ids, User user) {
        if (user == null) {
            return mt.find(Query.query(Criteria.where("id").in(ids)), this.type);
        } else {
            return mt.find(
                    Query.query(Criteria.where("id").in(ids)).addCriteria(
                            getUserScopeCriteria(user)), this.type);
        }
    }

    public T findOneByName(String name, User user) {
        if (user == null) {
            return mt.findOne(Query.query(Criteria.where("name").is(name)), this.type);
        } else {
            return mt.findOne(
                    Query.query(Criteria.where("name").is(name)).addCriteria(
                            getUserScopeCriteria(user)), this.type);
        }

    }

    public List<T> findByName(String name, User user) {
        if (user == null) {
            return mt.find(Query.query(Criteria.where("name").is(name)), this.type);
        } else {
            return mt.find(
                    Query.query(Criteria.where("name").is(name)).addCriteria(
                            getUserScopeCriteria(user)), this.type);
        }

    }

    public List<T> findByNames(List<String> names, User user) {
        if (user == null) {
            return mt.find(Query.query(Criteria.where("name").in(names)), this.type);
        } else {
            return mt.find(
                    Query.query(Criteria.where("name").in(names)).addCriteria(
                            getUserScopeCriteria(user)), this.type);
        }
    }

    public void insert(List<T> entities) {
        mt.insert(entities, this.type);
    }

    public void insert(T entity) {
        try {
            insert(entity, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insert(T entity, boolean validate) throws Exception {
        if (validate) {
            validate(entity);
        }
        mt.insert(entity);
    }

    public void save(T entity) {
        try {
            save(entity, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save(T entity, boolean validate) throws Exception {
        if (validate) {
            validate(entity);
        }
        mt.save(entity);
    }

    public void remove(T entity) {
        mt.remove(entity);
    }

    protected void validate(T entity) throws Exception {
        if (entity == null) {
            throw new NullPointerException();
        }
    }

    protected Criteria getUserScopeCriteria(User user) {
        return new Criteria();
    }

    public boolean isInUserScope(T entity, User user) {
        return entity != null;
    }

    public List<String> entitiesToIds(List<T> entities) {
        List<String> ids = new ArrayList<String>();
        for (T entity : entities) {
            ids.add(entity.getId());
        }
        return ids;
    }

    public static Query queryByMap(Map<String, Object> queryMap) throws IOException {
        Query query = new Query();
        for (Map.Entry<String, Object> field : queryMap.entrySet()) {
            if (field.getValue() instanceof Collection<?>) {
                String jsonValue = new ObjectMapper().writeValueAsString(field.getValue());
                DBObject dbObject = (DBObject) JSON.parse(jsonValue);
                query.addCriteria(Criteria.where(field.getKey()).is(dbObject));
            } else {
                query.addCriteria(Criteria.where(field.getKey()).is(field.getValue()));
            }
        }
        return query;
    }

    public static Query queryByJson(String jsonQuery) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> queryMap;
        queryMap = objectMapper.readValue(jsonQuery, new TypeReference<Map<String, Object>>() {
        });
        return queryByMap(queryMap);
    }

    public void dropCollection() {
        mt.dropCollection(type);
    }

    protected Sort createSort(String propertiesString, String directionString) {
        if (propertiesString == null) {
            return null;
        }
        Direction direction = directionString != null && "desc".equals(directionString) ? Direction.DESC
                : Direction.ASC;
        String[] properties = propertiesString.split(",");

        return new Sort(direction, properties);
    }

    public JsonNode merge(T defaults, JsonNode updateNode) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode mainNode = objectMapper.valueToTree(defaults);
        Iterator<String> fieldNames = updateNode.getFieldNames();
        while (fieldNames.hasNext()) {

            String fieldName = fieldNames.next();
            JsonNode jsonNode = updateNode.get(fieldName);
            if (jsonNode != null) {
                ((ObjectNode) mainNode).put(fieldName, jsonNode);
            }
        }
        return mainNode;
    }

    public void update(String id, Map<String, Object> obj) {
        Update update = new Update();
        for (Map.Entry<String, Object> entry : obj.entrySet()) {
            update.set(entry.getKey(), entry.getValue());
        }
        mt.updateFirst(new Query(Criteria.where("id").is(id)), update, this.type);
    }

    public void update(T entity) {
        if (entity.getId() == null) return;
        try {
            Update update = getUpdateObject(entity);
            update(entity.getId(), update);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void upsert(Query query, T entity) throws Exception {
        Update update = getUpdateObject(entity);
        mt.upsert(query, update, this.type);
    }

    public T findAndUpdate(String id, Update update) {
        return mt.findAndModify(new Query(Criteria.where("id").is(id)), update,
                option, this.type);
    }

    public Update getUpdateObject(T entity) throws Exception {
        BeanInfo beanInfo = Introspector.getBeanInfo(this.type);
        Update update = new Update();

        for (PropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors()) {
            String name = propertyDescriptor.getName();
            Method method = propertyDescriptor.getReadMethod();
            if (!"class".equals(name) && !method.isAnnotationPresent(JsonIgnore.class)) {
                update.set(propertyDescriptor.getName(), method.invoke(entity));
            }
        }
        return update;
    }

    public void update(String id, Update update) {
        mt.updateFirst(new Query(Criteria.where("id").is(id)), update, this.type);
    }

    public void updateMulti(Query query, Update update) {
        mt.updateMulti(query, update, this.type);
    }
}

