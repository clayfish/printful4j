package in.clayfish.printful.clients;

import com.google.gson.reflect.TypeToken;

import org.apache.commons.codec.binary.Base64;
import org.jsoup.Connection;

import java.io.IOException;
import java.lang.reflect.Type;

import in.clayfish.printful.SimpleClient;
import in.clayfish.printful.models.Configuration;
import in.clayfish.printful.models.Response;
import in.clayfish.printful.models.ShippingRequest;
import in.clayfish.printful.models.info.ShippingInfo;
import in.clayfish.printful.utils.LibUtils;

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
        this.base64Key = Base64.encodeBase64String(apiKey.getBytes());
        this.configuration = configuration;
    }

    @Override
    public Response<ShippingInfo> calculateShippingRates(ShippingRequest request) {
        try {
            String response = LibUtils.createConnection(base64Key, "shipping/rates", configuration)
                    .method(Connection.Method.POST)
                    .requestBody(LibUtils.gson.toJson(request))
                    .execute().body();

            Type type = new TypeToken<Response<ShippingInfo>>() {
            }.getType();
            return LibUtils.gson.fromJson(response, type);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
