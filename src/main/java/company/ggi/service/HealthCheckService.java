package company.ggi.service;


/**
 * Created by Ismail ELFAQIR on 26/04/2017.
 */

public class HealthCheckService {

    private String message;

    private String version;

    public HealthCheckService() {}

    public HealthCheckService(String message, String version) {
        this.message = message;
        this.version = version;
    }


    public void setMessage(String message) {
        this.message = message;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMessage() {
        return message;
    }

    public String getVersion() {
        return version;
    }
}
