package local.david.service.model.dao;

import local.david.service.common.AbstractDAO;
import local.david.service.model.pojo.User;
import local.david.service.model.pojo.Vendor;
import org.springframework.stereotype.Service;

/**
 * Created by [david] on 23.11.16.
 */
@Service
public class VendorDAO extends AbstractDAO<Vendor> {
    public VendorDAO() {
        super(Vendor.class);
    }

    @Override
    public void prePut(Vendor entity, String id) {

    }

    @Override
    protected void prepareEntityForSaving(Vendor entity, User user) {

    }
}
