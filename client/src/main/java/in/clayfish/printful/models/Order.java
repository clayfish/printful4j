package in.clayfish.printful.models;

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

}
