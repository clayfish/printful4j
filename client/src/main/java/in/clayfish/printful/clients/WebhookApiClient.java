package in.clayfish.printful.clients;

import android.util.Base64;

import in.clayfish.printful.SimpleClient;
import in.clayfish.printful.models.Configuration;
import in.clayfish.printful.models.Response;
import in.clayfish.printful.models.info.WebhookInfo;

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
     * @param apiKey
     */
    public WebhookApiClient(String apiKey) {
        this(apiKey, new Configuration());
    }

    /**
     * @param apiKey
     * @param configuration
     */
    public WebhookApiClient(String apiKey, Configuration configuration) {
        this.base64Key = Base64.encodeToString(apiKey.getBytes(), Base64.NO_WRAP);
        this.configuration = configuration;
    }

    @Override
    public Response<WebhookInfo> getWebhookConfig() {
        return super.getWebhookConfig();
    }

    @Override
    public Response<WebhookInfo> setupWebhookConfig(WebhookInfo webhookInfo) {
        return super.setupWebhookConfig(webhookInfo);
    }

    @Override
    public Response<WebhookInfo> disableWebhookSupport() {
        return super.disableWebhookSupport();
    }

}
