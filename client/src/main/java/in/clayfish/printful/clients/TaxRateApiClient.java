package in.clayfish.printful.clients;

import android.util.Base64;

import in.clayfish.printful.SimpleClient;
import in.clayfish.printful.models.Configuration;
import in.clayfish.printful.models.Response;
import in.clayfish.printful.models.includable.Country;
import in.clayfish.printful.models.info.TaxAddressInfo;
import in.clayfish.printful.models.info.TaxInfo;

/**
 * See https://www.theprintful.com/docs/tax
 *
 * @author shuklaalok7
 * @since 24/12/2016
 */
public class TaxRateApiClient extends SimpleClient {
    private final static String TAG = TaxRateApiClient.class.getSimpleName();

    private final String base64Key;
    private Configuration configuration;

    /**
     * @param apiKey
     */
    public TaxRateApiClient(String apiKey) {
        this(apiKey, new Configuration());
    }

    /**
     * @param apiKey
     * @param configuration
     */
    public TaxRateApiClient(String apiKey, Configuration configuration) {
        this.base64Key = Base64.encodeToString(apiKey.getBytes(), Base64.NO_WRAP);
        this.configuration = configuration;
    }

    @Override
    public Response<Country> retrieveStateListThatRequiresTaxCalculation() {
        return super.retrieveStateListThatRequiresTaxCalculation();
    }

    public Response<TaxInfo> calculateTaxRate(TaxAddressInfo taxAddressInfo) {
        return super.calculateTaxRate(taxAddressInfo);
    }

}
