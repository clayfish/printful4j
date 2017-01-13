package in.clayfish.printful.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Locale;

import in.clayfish.printful.Client;
import in.clayfish.printful.clients.ProductCatalogApiClient;
import in.clayfish.printful.models.Product;
import in.clayfish.printful.models.Variant;
import in.clayfish.printful.models.info.ProductInfo;
import in.clayfish.printful.models.info.VariantInfo;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ProductCatalogApiClientTest {
    static String API_KEY = "YOUR_API_KEY";
    private Client client;

    @Before
    public void setup() {
        client = new ProductCatalogApiClient(API_KEY);
    }

    @Test
    public void testGetAllProductList() {
        List<Product> products = client.getAllProductList().getResult();
        Assert.assertTrue(products != null && !products.isEmpty());

        for (Product product : products) {
            System.out.println(product.getId());
//            System.out.println(String.format(Locale.ENGLISH, "%s %s (id=%d, totalVariants=%d)",
//                    product.getBrand(), product.getModel(), product.getId(),
//                    product.getVariantCount()));
        }
    }

    @Test
    public void testGetInformationAboutVariant() {
        List<VariantInfo> variantInfoList = client.getInfoAboutVariant(1).getResult();
        Assert.assertTrue(variantInfoList != null && !variantInfoList.isEmpty());

//        for (VariantInfo variantInfo : variantInfoList) {
//            System.out.println(String.format(Locale.ENGLISH, "%d: %s %s (totalVariants=%d)",
//                    variantInfo.getProduct().getId(), variantInfo.));
//        }
    }

    @Test
    public void testGetProductsVariantsList() {
        List<ProductInfo> productInfoList = client.getProductsVariantsList(1).getResult();
        Assert.assertTrue(productInfoList != null && !productInfoList.isEmpty());

        Product product = productInfoList.get(0).getProduct();
        System.out.println(String.format(Locale.ENGLISH, "%d: %s %s (totalVariants=%d)",
                product.getId(), product.getType(), product.getModel(), product.getVariantCount()));

        for (ProductInfo productInfo : productInfoList) {
            for (Variant variant : productInfo.getVariants()) {

                System.out.println(String.format(Locale.ENGLISH, "%d,%s,%s,%s,%s,%s,%.2f,%b",
                        variant.getId(), variant.getName(), variant.getSize(), variant.getColor(),
                        variant.getColorCode(), variant.getImage(), variant.getPrice(),
                        variant.isInStock()));
            }
        }
    }

}
