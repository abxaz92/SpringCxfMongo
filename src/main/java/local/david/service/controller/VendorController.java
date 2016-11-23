package local.david.service.controller;

import local.david.service.common.AbstractController;
import local.david.service.common.AbstractDAO;
import local.david.service.model.dao.VendorDAO;
import local.david.service.model.pojo.User;
import local.david.service.model.pojo.Vendor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by [david] on 23.11.16.
 */
@Service("vendorController")
@Path("/vendor")
@Produces(MediaType.APPLICATION_JSON)
public class VendorController extends AbstractController<Vendor> {
    private static final Logger logger = LoggerFactory.getLogger(VendorController.class);

    @Autowired
    private VendorDAO vendorDAO;

    public VendorController() {
        super(Vendor.class);
    }

    @Override
    protected AbstractDAO<Vendor> getDao() {
        return vendorDAO;
    }

    @Override
    protected Logger getLogger() {
        return logger;
    }

    @Override
    protected User getCurentUser() {
        return null;
    }
}
