package in.clayfish.printful.models;

import java.util.Locale;

/**
 * @author shuklaalok7
 * @since 21/12/2016
 */
public class Configuration {

    private String baseUrl = "https://api.printful.com/";
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

    @Override
    public String toString() {
        return String.format(Locale.ENGLISH, "Printful configuration {baseUrl=%s, timeout=%d}",
                baseUrl, timeout);
    }
}
