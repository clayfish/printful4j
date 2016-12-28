package in.clayfish.printful.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Locale;

import in.clayfish.printful.Client;
import in.clayfish.printful.clients.ProductCatalogApiClient;
import in.clayfish.printful.models.Product;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class ProductCatalogApiClientTest {
    private static String API_KEY = "YOUR_API_KEY";

    @Test
    public void testGetAllProductList() {
        Client client = new ProductCatalogApiClient(API_KEY);
        List<Product> products = client.getAllProductList().getResult();
        for (Product product : products) {
            System.out.println(String.format(Locale.ENGLISH, "%s %s (id=%d, totalVariants=%d)",
                    product.getBrand(), product.getModel(), product.getId(),
                    product.getVariantCount()));
        }
    }

}
