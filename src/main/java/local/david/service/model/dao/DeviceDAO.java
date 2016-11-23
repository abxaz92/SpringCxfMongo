package local.david.service.model.dao;

import local.david.service.common.AbstractDAO;
import local.david.service.model.pojo.Device;
import local.david.service.model.pojo.User;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by [david] on 23.11.16.
 */
@Service
public class DeviceDAO extends AbstractDAO<Device> {
    public DeviceDAO() {
        super(Device.class);
    }

    public Device makeRestored(String id) {
        Update update = new Update();
        update.set("completeDate", new Date());
        update.set("restored", true);
        return findAndUpdate(id, update);
    }

    @Override
    protected void prepareEntityForSaving(Device entity, User user) {
        entity.setRestored(false);
        entity.setTimestamp(new Date());
        entity.setCompleteDate(null);
    }
}
