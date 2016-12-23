package in.clayfish.printful.clients;

import android.util.Base64;

import in.clayfish.printful.SimpleClient;
import in.clayfish.printful.models.Configuration;

/**
 * See https://www.theprintful.com/docs/sync
 *
 * @author shuklaalok7
 * @since 24/12/2016
 */
public class EComPlatformSyncApiClient extends SimpleClient {
    private final static String TAG = EComPlatformSyncApiClient.class.getSimpleName();

    private final String base64Key;
    private Configuration configuration;

    /**
     * @param apiKey
     */
    public EComPlatformSyncApiClient(String apiKey) {
        this(apiKey, new Configuration());
    }

    /**
     * @param apiKey
     * @param configuration
     */
    public EComPlatformSyncApiClient(String apiKey, Configuration configuration) {
        this.base64Key = Base64.encodeToString(apiKey.getBytes(), Base64.NO_WRAP);
        this.configuration = configuration;
    }


}
