package in.clayfish.printful.models.includable;

import java.util.Date;
import java.util.List;

import in.clayfish.printful.models.Entity;

/**
 * @author shuklaalok7
 * @since 24/12/2016
 */
public class Shipment extends Entity {

    /**
     * Carrier name
     */
    private String carrier;

    /**
     * Delivery service name
     */
    private String service;

    /**
     * Shipment tracking number
     */
    private String trackingNumber;

    /**
     * Shipment tracking URL
     */
    private String trackingUrl;

    /**
     * Shipping time
     */
    private Date created;

    /**
     * Ship date
     */
    private String shipDate;

    /**
     * Whether this is a reshipment
     */
    private boolean reshipment;

    /**
     * items in this shipment
     */
    private List<Item> items;

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getTrackingUrl() {
        return trackingUrl;
    }

    public void setTrackingUrl(String trackingUrl) {
        this.trackingUrl = trackingUrl;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getShipDate() {
        return shipDate;
    }

    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }

    public boolean isReshipment() {
        return reshipment;
    }

    public void setReshipment(boolean reshipment) {
        this.reshipment = reshipment;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    /**
     * @author shuklaalok7
     * @since 24/12/2016
     */
    public static class Item extends Entity {
        /**
         * Quantity of items in this shipment
         */
        private int quantity;

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }

}
