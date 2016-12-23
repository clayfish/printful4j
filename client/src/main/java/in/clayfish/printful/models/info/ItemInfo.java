package in.clayfish.printful.models.info;

/**
 * @author shuklaalok7
 * @since 24/12/2016
 */
public class ItemInfo {

    /**
     * Variant ID of the item ordered
     */
    private String variantId;

    /**
     * Number of items ordered
     */
    private int quantity;

    public String getVariantId() {
        return variantId;
    }

    public void setVariantId(String variantId) {
        this.variantId = variantId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
