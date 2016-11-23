package local.david.service.model.pojo;

import java.util.Date;

/**
 * Created by [david] on 23.11.16.
 */
public class Image {
    private String location;
    private Date timestamp;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Image image = (Image) o;

        if (location != null ? !location.equals(image.location) : image.location != null) return false;
        return timestamp != null ? timestamp.equals(image.timestamp) : image.timestamp == null;

    }

    @Override
    public int hashCode() {
        int result = location != null ? location.hashCode() : 0;
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        return result;
    }
}
