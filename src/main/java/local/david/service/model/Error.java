package local.david.service.model;

/**
 * Created by [david] on 22.11.16.
 */
public class Error {

    private String cause;

    public Error() {
    }

    public Error(String cause) {
        this.cause = cause;
    }

    public Error(Throwable cause) {
        this.cause = cause.getMessage();
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

}
