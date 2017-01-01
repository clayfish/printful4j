package in.clayfish.printful.test;

import org.junit.Test;

import in.clayfish.printful.Client;
import in.clayfish.printful.clients.WebhookApiClient;

/**
 * @author shuklaalok7
 * @since 1/01/2017
 */
public class WebhookApiClientTest {

    private Client client;

    public void setup() {
        this.client = new WebhookApiClient(ProductCatalogApiClientTest.API_KEY);
    }

    @Test
    public void getWebhookConfig() {
        // TODO implement
    }

    @Test
    public void setupWebhookConfig() {
        // TODO implement
    }

    @Test
    public void disableWebhookSupport() {
        // TODO implement
    }

}
