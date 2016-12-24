package in.clayfish.printful.models.includable;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

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

    /**
     * @param json
     */
    public Cost(JSONObject json) throws JSONException {
        if (json.has("subtotal")) {
            this.subtotal = json.getString("subtotal");
        }

        if (json.has("discount")) {
            this.discount = json.getString("discount");
        }

        if (json.has("shipping")) {
            this.shipping = json.getString("shipping");
        }

        if (json.has("tax")) {
            this.tax = json.getString("tax");
        }

        if (json.has("total")) {
            this.total = json.getString("total");
        }
    }

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

    @Override
    public String toString() {
        JSONStringer stringer = new JSONStringer();

        try {
            stringer.object();

            if (subtotal != null) {
                stringer.key("subtotal");
                stringer.value(subtotal);
            }

            if (discount != null) {
                stringer.key("discount");
                stringer.value(discount);
            }

            if (shipping != null) {
                stringer.key("shipping");
                stringer.value(shipping);
            }

            if (tax != null) {
                stringer.key("tax");
                stringer.value(tax);
            }

            if (total != null) {
                stringer.key("total");
                stringer.value(total);
            }

            stringer.endObject();
            return stringer.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return "";
    }
}
