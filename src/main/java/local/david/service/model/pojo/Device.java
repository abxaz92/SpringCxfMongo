package local.david.service.model.pojo;

import local.david.service.common.Entity;

import java.util.Date;
import java.util.Set;

/**
 * Created by [david] on 23.11.16.
 */
public class Device extends Entity {
    private String name;
    private String desc;
    private String vendorId;
    private Date timestamp;
    private boolean restored;
    private double cost;
    private String phone;
    private Date completeDate;
    private Set<Image> images;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isRestored() {
        return restored;
    }

    public void setRestored(boolean restored) {
        this.restored = restored;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(Date completeDate) {
        this.completeDate = completeDate;
    }

    public Set<String> getImages() {
        return images;
    }

    public void setImages(Set<String> images) {
        this.images = images;
    }
}
