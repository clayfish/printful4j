package in.clayfish.printful.clients;

import android.util.Base64;

import in.clayfish.printful.SimpleClient;
import in.clayfish.printful.models.Configuration;
import in.clayfish.printful.models.Response;
import in.clayfish.printful.models.ShippingRequest;
import in.clayfish.printful.models.info.ShippingInfo;

/**
 * See https://www.theprintful.com/docs/shipping
 *
 * @author shuklaalok7
 * @since 24/12/2016
 */
public class ShippingRateApiClient extends SimpleClient {
    private final static String TAG = ShippingRateApiClient.class.getSimpleName();

    private final String base64Key;
    private Configuration configuration;

    /**
     * @param apiKey
     */
    public ShippingRateApiClient(String apiKey) {
        this(apiKey, new Configuration());
    }

    /**
     * @param apiKey
     * @param configuration
     */
    public ShippingRateApiClient(String apiKey, Configuration configuration) {
        this.base64Key = Base64.encodeToString(apiKey.getBytes(), Base64.NO_WRAP);
        this.configuration = configuration;
    }

    @Override
    public Response<ShippingInfo> calculateShippingRates(ShippingRequest request) {
        return super.calculateShippingRates(request);
    }

}
