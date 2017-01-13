package in.clayfish.printful.clients;

import com.google.gson.reflect.TypeToken;

import org.jsoup.Connection;

import java.io.IOException;
import java.lang.reflect.Type;

import in.clayfish.printful.SimpleClient;
import in.clayfish.printful.models.Configuration;
import in.clayfish.printful.models.PackingSlip;
import in.clayfish.printful.models.Response;
import in.clayfish.printful.models.StoreData;
import in.clayfish.printful.utils.LibUtils;

/**
 * See https://www.theprintful.com/docs/store
 *
 * @author shuklaalok7
 * @since 24/12/2016
 */
public class StoreInfoApiClient extends SimpleClient {
    private final static String TAG = StoreInfoApiClient.class.getSimpleName();

    private final String base64Key;
    private Configuration configuration;

    /**
     * @param apiKey
     */
    public StoreInfoApiClient(String apiKey) {
        this(apiKey, new Configuration());
    }

    /**
     * @param apiKey
     * @param configuration
     */
    public StoreInfoApiClient(String apiKey, Configuration configuration) {
        this.base64Key = LibUtils.encodeToBase64(apiKey);
        this.configuration = configuration;
    }

    @Override
    public Response<StoreData> getStoreInfo() {
        try {
            String response = LibUtils.createConnection(base64Key, "store", configuration).execute().body();
            Type type = new TypeToken<Response<StoreData>>() {
            }.getType();
            return LibUtils.gson.fromJson(response, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Response<PackingSlip> changeStorePackingSlip(PackingSlip packingSlip) {
        // These fields are required
        if (packingSlip.getEmail() == null) {
            packingSlip.setEmail("");
        }

        if (packingSlip.getPhone() == null) {
            packingSlip.setPhone("");
        }

        if (packingSlip.getMessage() == null) {
            packingSlip.setMessage("");
        }

        try {
            String response = LibUtils.createConnection(base64Key, "store/packing-slip", configuration)
                    .method(Connection.Method.POST).requestBody(LibUtils.gson.toJson(packingSlip))
                    .execute().body();
            Type type = new TypeToken<Response<PackingSlip>>() {
            }.getType();
            return LibUtils.gson.fromJson(response, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
