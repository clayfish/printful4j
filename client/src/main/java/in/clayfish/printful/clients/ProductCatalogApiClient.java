package in.clayfish.printful.clients;

import android.util.Base64;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import in.clayfish.printful.SimpleClient;
import in.clayfish.printful.models.Configuration;
import in.clayfish.printful.models.Product;
import in.clayfish.printful.models.ProductInfo;
import in.clayfish.printful.models.Response;
import in.clayfish.printful.models.VariantInfo;
import in.clayfish.printful.utils.LibUtils;

/**
 * See https://www.theprintful.com/docs/products
 *
 * @author shuklaalok7
 * @since 24/12/2016
 */
public class ProductCatalogApiClient extends SimpleClient {

    private final static String TAG = ProductCatalogApiClient.class.getSimpleName();

    private final String base64Key;
    private Configuration configuration;

    /**
     * @param apiKey
     */
    public ProductCatalogApiClient(String apiKey) {
        this(apiKey, new Configuration());
    }

    /**
     * @param apiKey
     * @param configuration
     */
    public ProductCatalogApiClient(String apiKey, Configuration configuration) {
        this.base64Key = Base64.encodeToString(apiKey.getBytes(), Base64.NO_WRAP);
        this.configuration = configuration;
    }

    @Override
    public Response<Product> getAllProductList() {
        final String url = configuration.getBaseUrl() + "products";

        try {
            String response = LibUtils.createConnection(base64Key, url, configuration).execute().body();
            JSONObject json = new JSONObject(response);
            Response<Product> response1 = new Response<>();
            response1.setCode(json.getInt("code"));

            if (response1.getCode() == 200) {
                JSONArray productsJson = json.getJSONArray("result");
                List<Product> result = new ArrayList<>();

                for (int i = 0; i < productsJson.length(); i++) {
                    result.add(new Product(productsJson.getJSONObject(i)));
                }

                response1.setResult(result);
                return response1;
            } else {
                response1.setErrorMessage(json.getString("result"));
            }
        } catch (IOException | JSONException e) {
            Log.e(TAG, "Problem in connecting to Printful API.", e);
        }

        return null;
    }

    @Override
    public Response<VariantInfo> getInformationAboutVariant(int variantId) {
        // TODO implement
        return super.getInformationAboutVariant(variantId);
    }

    @Override
    public Response<ProductInfo> getProductsVariantsList(int productId) {
        // TODO implement
        return super.getProductsVariantsList(productId);
    }
}
