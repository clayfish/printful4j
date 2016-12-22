package in.clayfish.printful.models;

/**
 * @author shuklaalok7
 * @since 21/12/2016
 */
public class Configuration {

    private String baseUrl = "https://api.theprintful.com/";
    private int timeout = 30000;

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        if (!baseUrl.endsWith("/")) {
            baseUrl += "/";
        }
        this.baseUrl = baseUrl;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
}
