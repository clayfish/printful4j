package in.clayfish.printful.clients;

import android.util.Log;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;

import in.clayfish.printful.SimpleClient;
import in.clayfish.printful.models.Configuration;
import in.clayfish.printful.models.Product;
import in.clayfish.printful.models.Response;
import in.clayfish.printful.models.info.ProductInfo;
import in.clayfish.printful.models.info.VariantInfo;
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
        this.base64Key = LibUtils.encodeToBase64(apiKey);
        this.configuration = configuration;
    }

    @Override
    public Response<Product> getAllProductList() {
        try {
            String response = LibUtils.createConnection(base64Key, "products", configuration)
                    .execute().body();
            Type type = new TypeToken<Response<Product>>() {
            }.getType();
            return LibUtils.gson.fromJson(response, type);
        } catch (IOException e) {
            Log.e(TAG, "Problem in connecting to Printful API.", e);
        }

        return null;
    }

    @Override
    public Response<VariantInfo> getInfoAboutVariant(int variantId) {
        try {
            String response = LibUtils.createConnection(base64Key, "products/variant/" + variantId,
                    configuration).execute().body();
            Type type = new TypeToken<Response<VariantInfo>>() {
            }.getType();
            return LibUtils.gson.fromJson(response, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Response<ProductInfo> getProductsVariantsList(int productId) {
        try {
            String response = LibUtils.createConnection(base64Key, "products/" + productId,
                    configuration).execute().body();
            Type type = new TypeToken<Response<ProductInfo>>() {
            }.getType();
            return LibUtils.gson.fromJson(response, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
