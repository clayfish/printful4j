package in.clayfish.printful.clients;

import org.apache.commons.codec.binary.Base64;

import in.clayfish.printful.SimpleClient;
import in.clayfish.printful.models.Configuration;
import in.clayfish.printful.models.PackingSlip;
import in.clayfish.printful.models.StoreData;

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
        this.base64Key = Base64.encodeBase64String(apiKey.getBytes());
        this.configuration = configuration;
    }

    @Override
    public StoreData getStoreInfo() {
        return super.getStoreInfo();
    }

    @Override
    public PackingSlip changeStorePackingSlip(PackingSlip packingSlip) {
        return super.changeStorePackingSlip(packingSlip);
    }

}
