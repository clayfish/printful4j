package in.clayfish.printful.models.includable;

/**
 * @author shuklaalok7
 * @since 24/12/2016
 */
public class ProductVariant {

    private int variantId;
    private int productId;

    /**
     * URL of a sample image for this variant
     */
    private String image;

    /**
     * Display name of this variant
     */
    private String name;

    public int getVariantId() {
        return variantId;
    }

    public void setVariantId(int variantId) {
        this.variantId = variantId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
