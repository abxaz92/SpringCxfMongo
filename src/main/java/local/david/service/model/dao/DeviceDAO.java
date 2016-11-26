package local.david.service.model.dao;

import local.david.service.common.AbstractDAO;
import local.david.service.model.pojo.Device;
import local.david.service.model.pojo.User;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public void generate() {
        List<Device> devices = new ArrayList<>();
        for (int i = 0; i < 250; i++) {
            Device device = new Device();
            device.setName(String.valueOf(i));
            device.setCost(i);
            device.setDesc(String.valueOf(1));
            device.setImei(String.valueOf(1));
            device.setTimestamp(new Date());
            devices.add(device);
        }
        insert(devices);
    }

    @Override
    protected void prepareEntityForSaving(Device entity, User user) {
        entity.setTimestamp(new Date());
    }
}
