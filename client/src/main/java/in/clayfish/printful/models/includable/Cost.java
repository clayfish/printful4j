package in.clayfish.printful.models.includable;

/**
 * @author shuklaalok7
 * @since 24/12/2016
 */
public class Cost {

    /**
     * Total cost of all items
     */
    private String subtotal;

    /**
     * Discount sum
     */
    private String discount;

    /**
     * Shipping costs
     */
    private String shipping;

    /**
     * Sum of taxes (not included in the item price)
     */
    private String tax;

    /**
     * Grand Total (subtotal-discount+tax+shipping)
     */
    private String total;


    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getShipping() {
        return shipping;
    }

    public void setShipping(String shipping) {
        this.shipping = shipping;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
