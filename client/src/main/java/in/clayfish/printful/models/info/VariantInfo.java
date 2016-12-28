package in.clayfish.printful.models.info;

import in.clayfish.printful.models.Product;
import in.clayfish.printful.models.Variant;

/**
 * @author shuklaalok7
 * @since 24/12/2016
 */
public class VariantInfo {

    /**
     * Information about the selected Variant
     */
    private Variant variant;

    /**
     * Information about the Product that the Variant belongs to
     */
    private Product product;

    public Variant getVariant() {
        return variant;
    }

    public void setVariant(Variant variant) {
        this.variant = variant;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


}
