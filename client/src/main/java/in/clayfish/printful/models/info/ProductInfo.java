package in.clayfish.printful.models.info;

import java.util.List;

import in.clayfish.printful.models.Product;
import in.clayfish.printful.models.Variant;

/**
 * @author shuklaalok7
 * @since 24/12/2016
 */
public class ProductInfo {

    /**
     * Information about the selected product
     */
    private Product product;

    /**
     * Variants available for the selected product
     */
    private List<Variant> variants;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Variant> getVariants() {
        return variants;
    }

    public void setVariants(List<Variant> variants) {
        this.variants = variants;
    }


}
