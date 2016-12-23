package in.clayfish.printful;

import android.util.Base64;

import in.clayfish.printful.clients.ProductCatalogApiClient;
import in.clayfish.printful.models.Configuration;
import in.clayfish.printful.models.Product;
import in.clayfish.printful.models.ProductInfo;
import in.clayfish.printful.models.Response;
import in.clayfish.printful.models.VariantInfo;

/**
 * @author shuklaalok7
 * @since 21/12/2016
 */
public class CompositeClient implements Client {

    private final ProductCatalogApiClient productCatalogApiClient;

    private final String base64Key;
    private Configuration configuration;

    /**
     * @param apiKey
     */
    public CompositeClient(String apiKey) {
        this(apiKey, new Configuration());
    }

    /**
     * @param apiKey
     * @param configuration
     */
    public CompositeClient(String apiKey, Configuration configuration) {
        this.base64Key = Base64.encodeToString(apiKey.getBytes(), Base64.NO_WRAP);
        this.configuration = configuration;
        this.productCatalogApiClient = new ProductCatalogApiClient(apiKey, configuration);
    }

    @Override
    public Response<Product> getAllProductList() {
        return productCatalogApiClient.getAllProductList();
    }

    @Override
    public Response<VariantInfo> getInformationAboutVariant(int variantId) {
        return productCatalogApiClient.getInformationAboutVariant(variantId);
    }

    @Override
    public Response<ProductInfo> getProductsVariantsList(int productId) {
        return productCatalogApiClient.getProductsVariantsList(productId);
    }

}
