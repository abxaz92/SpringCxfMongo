package local.david.service.controller;

import local.david.service.common.AbstractController;
import local.david.service.common.AbstractDAO;
import local.david.service.model.dao.DeviceDAO;
import local.david.service.model.pojo.Device;
import local.david.service.model.pojo.User;
import local.david.service.service.ContextService;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by [david] on 23.11.16.
 */
@Service("deviceController")
@Path("/device")
@Produces(MediaType.APPLICATION_JSON)
public class DeviceController extends AbstractController<Device> {
    private static final Logger logger = LoggerFactory.getLogger(DeviceController.class);
    @Autowired
    private DeviceDAO deviceDAO;
    @Autowired
    private ContextService ctx;

    public DeviceController() {
        super(Device.class);
    }

    @POST
    @Path("/sec/generate")
    public void generate() {
        deviceDAO.generate();
    }

    @POST
    @Path("/upload/image")
    public void upload(List<Attachment> attachments) {

    }

    @Override
    protected AbstractDAO<Device> getDao() {
        return deviceDAO;
    }

    @Override
    protected Logger getLogger() {
        return logger;
    }

    @Override
    protected User getCurentUser() {
        return ctx.getCurrentUser();
    }
}
