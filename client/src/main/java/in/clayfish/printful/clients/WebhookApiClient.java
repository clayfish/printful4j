package in.clayfish.printful.clients;

import com.google.gson.reflect.TypeToken;

import org.jsoup.Connection;

import java.io.IOException;
import java.lang.reflect.Type;

import in.clayfish.printful.SimpleClient;
import in.clayfish.printful.models.Configuration;
import in.clayfish.printful.models.Response;
import in.clayfish.printful.models.info.WebhookInfo;
import in.clayfish.printful.utils.LibUtils;

/**
 * See https://www.theprintful.com/docs/webhooks
 *
 * @author shuklaalok7
 * @since 24/12/2016
 */
public class WebhookApiClient extends SimpleClient {
    private final static String TAG = WebhookApiClient.class.getSimpleName();

    private final String base64Key;
    private Configuration configuration;

    /**
     * @param apiKey Your API key
     */
    public WebhookApiClient(String apiKey) {
        this(apiKey, new Configuration());
    }

    /**
     * @param apiKey        YOUR_API_KEY
     * @param configuration Configuration object
     */
    public WebhookApiClient(String apiKey, Configuration configuration) {
        this.base64Key = LibUtils.encodeToBase64(apiKey);
        this.configuration = configuration;
    }

    @Override
    public Response<WebhookInfo> getWebhookConfig() {
        try {
            String response = LibUtils.createConnection(base64Key, "webhooks", configuration).execute().body();
            // TODO inspect response to fix WebhookInfo model
            return createWebhookInfo(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Response<WebhookInfo> setupWebhookConfig(WebhookInfo webhookInfo) {
        try {
            String response = LibUtils.createConnection(base64Key, "webhooks", configuration)
                    .method(Connection.Method.POST).requestBody(LibUtils.gson.toJson(webhookInfo))
                    .execute().body();
            return createWebhookInfo(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Response<WebhookInfo> disableWebhookSupport() {
        try {
            String response = LibUtils.createConnection(base64Key, "webhooks", configuration)
                    .method(Connection.Method.DELETE).execute().body();
            return createWebhookInfo(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param response obtained string from API
     * @return response object
     */
    private Response<WebhookInfo> createWebhookInfo(String response) {
        Type type = new TypeToken<Response<WebhookInfo>>() {
        }.getType();
        return LibUtils.gson.fromJson(response, type);
    }

}
