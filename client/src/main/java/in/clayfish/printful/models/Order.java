package in.clayfish.printful.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import in.clayfish.printful.enums.OrderStatus;
import in.clayfish.printful.models.includable.Address;
import in.clayfish.printful.models.includable.Cost;
import in.clayfish.printful.models.includable.GiftData;
import in.clayfish.printful.models.includable.Item;
import in.clayfish.printful.models.includable.Shipment;

/**
 * @author shuklaalok7
 * @since 21/12/2016
 */
public class Order extends Entity {
    /**
     * Order ID from the external system
     */
    private String externalId;

    /**
     * Order status
     */
    private OrderStatus status;

    /**
     * Shipping method. Defaults to 'STANDARD'
     */
    private String shipping;

    /**
     * Time when the order was created
     */
    private Date created;

    /**
     * Time when the order was updated
     */
    private Date updated;

    /**
     * Shipping address
     */
    private Address recipient;

    /**
     * Items in the order
     */
    private List<Item> items;

    /**
     * Order costs (Printful prices, in USD)
     */
    private Cost costs;

    /**
     * Original retail costs in USD that are to be displayed on the packing slip for international
     * shipments. Retail costs are used only if every item in order contains the retail_price
     * attribute.
     */
    private Cost retailCosts;

    /**
     * Shipments already shipped for this order
     */
    private List<Shipment> shipments;

    /**
     * Optional gift message for the packing slip
     */
    private GiftData gift;

    /**
     * Custom packing slip for this order
     */
    private PackingSlip packingSlip;

    /**
     * @param json
     * @throws JSONException
     */
    public Order(JSONObject json) throws JSONException {
        super(json);

        if (json != null) {
            if (json.has("external_id")) {
                this.externalId = json.getString("external_id");
            }

            if (json.has("status")) {
                this.status = OrderStatus.find(json.getString("status"));
            }

            if (json.has("shipping")) {
                this.shipping = json.getString("shipping");
            }

            if (json.has("created")) {
                this.created = new Date(json.getLong("created"));
            }

            if (json.has("updated")) {
                this.updated = new Date(json.getLong("updated"));
            }
            if (json.has("recipient")) {
                this.recipient = new Address(json.getJSONObject("recipient"));
            }

            if (json.has("items")) {
                this.items = new ArrayList<>();
                JSONArray itemsArray = json.getJSONArray("items");
                for (int i = 0; i < itemsArray.length(); i++) {
                    this.items.add(new Item(itemsArray.getJSONObject(i)));
                }
            }

            if (json.has("costs")) {
                this.costs = new Cost(json.getJSONObject("costs"));
            }

            if (json.has("retail_costs")) {
                this.retailCosts = new Cost(json.getJSONObject("retail_costs"));
            }

            if (json.has("shipments")) {
                this.shipments = new ArrayList<>();
                JSONArray shipmentsArray = json.getJSONArray("shipments");
                for (int i = 0; i < shipmentsArray.length(); i++) {
                    this.shipments.add(new Shipment(shipmentsArray.getJSONObject(i)));
                }
            }

            if (json.has("gift")) {
                this.gift = new GiftData(json.getJSONObject("gift"));
            }

            if (json.has("packing_slip")) {
                this.packingSlip = new PackingSlip(json.getJSONObject("packing_slip"));
            }
        }
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getShipping() {
        return shipping;
    }

    public void setShipping(String shipping) {
        this.shipping = shipping;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Address getRecipient() {
        return recipient;
    }

    public void setRecipient(Address recipient) {
        this.recipient = recipient;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Cost getCosts() {
        return costs;
    }

    public void setCosts(Cost costs) {
        this.costs = costs;
    }

    public Cost getRetailCosts() {
        return retailCosts;
    }

    public void setRetailCosts(Cost retailCosts) {
        this.retailCosts = retailCosts;
    }

    public List<Shipment> getShipments() {
        return shipments;
    }

    public void setShipments(List<Shipment> shipments) {
        this.shipments = shipments;
    }

    public GiftData getGift() {
        return gift;
    }

    public void setGift(GiftData gift) {
        this.gift = gift;
    }

    public PackingSlip getPackingSlip() {
        return packingSlip;
    }

    public void setPackingSlip(PackingSlip packingSlip) {
        this.packingSlip = packingSlip;
    }

    @Override
    public String toString() {
        // This method is used to get JSON representation of this object
        JSONStringer jsonStringer = new JSONStringer();

        try {
            jsonStringer.object();

            if (getId() > 0) {
                jsonStringer.key("id");
                jsonStringer.value(getId());
            }

            if (externalId != null) {
                jsonStringer.key("external_id");
                jsonStringer.value(externalId);
            }

            if (status != null) {
                jsonStringer.key("status");
                jsonStringer.value(status.name().toLowerCase());
            }

            if (shipping != null) {
                jsonStringer.key("shipping");
                jsonStringer.value(shipping);
            }

            if (created != null) {
                jsonStringer.key("created");
                jsonStringer.value(created.getTime());
            }

            if (updated != null) {
                jsonStringer.key("updated");
                jsonStringer.value(updated);
            }

            if (recipient != null) {
                jsonStringer.key("recipient");
                jsonStringer.value(new JSONObject(recipient.toString()));
            }

            if (items != null) {
                jsonStringer.key("items");
                JSONArray itemsArray = new JSONArray();
                for (Item item : items) {
                    itemsArray.put(new JSONObject(item.toString()));
                }
                jsonStringer.value(itemsArray);
            }

            if (costs != null) {
                jsonStringer.key("costs");
                jsonStringer.value(new JSONObject(costs.toString()));
            }

            if (retailCosts != null) {
                jsonStringer.key("retail_costs");
                jsonStringer.value(new JSONObject(retailCosts.toString()));
            }

            if (shipments != null) {
                jsonStringer.key("shipments");
                JSONArray shipmentsArray = new JSONArray();
                for (Shipment shipment : shipments) {
                    shipmentsArray.put(new JSONObject(shipment.toString()));
                }
                jsonStringer.value(shipmentsArray);
            }

            if (gift != null) {
                jsonStringer.key("gift");
                jsonStringer.value(new JSONObject(gift.toString()));
            }

            if (packingSlip != null) {
                jsonStringer.key("packing_slip");
                jsonStringer.value(new JSONObject(packingSlip.toString()));
            }

            jsonStringer.endObject();
            return jsonStringer.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return "";
    }
}
