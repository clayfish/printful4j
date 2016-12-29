package in.clayfish.printful.models;

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

}
