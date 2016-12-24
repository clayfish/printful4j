package in.clayfish.printful.models;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.Serializable;
import java.util.List;

import in.clayfish.printful.models.info.AddressInfo;
import in.clayfish.printful.models.info.ItemInfo;

/**
 * @author shuklaalok7
 * @since 24/12/2016
 */
public class ShippingRequest implements Serializable {

    /**
     * Recipient location information
     */
    private AddressInfo recipient;

    /**
     * List of order items
     */
    private List<ItemInfo> items;

    /**
     * 3 letter currency code (optional), required if the rates need to be converted to another currency instead of USD
     */
    private String currency;

    public ShippingRequest() {
    }

    public ShippingRequest(JSONObject json) {
        // TODO implement
    }

    public AddressInfo getRecipient() {
        return recipient;
    }

    public void setRecipient(AddressInfo recipient) {
        this.recipient = recipient;
    }

    public List<ItemInfo> getItems() {
        return items;
    }

    public void setItems(List<ItemInfo> items) {
        this.items = items;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        JSONStringer stringer = new JSONStringer();

        try {
            stringer.object();

            // TODO implement

            stringer.endObject();
            return stringer.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return "";
    }
}
