package in.clayfish.printful.models.includable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.ArrayList;
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

    public Shipment(JSONObject json) throws JSONException {
        super(json);

        if (json.has("carrier")) {
            this.carrier = json.getString("carrier");
        }

        if (json.has("service")) {
            this.service = json.getString("service");
        }

        if (json.has("tracking_number")) {
            this.trackingNumber = json.getString("tracking_number");
        }

        if (json.has("tracking_url")) {
            this.trackingUrl = json.getString("tracking_url");
        }

        if (json.has("created")) {
            this.created = new Date(json.getLong("created"));
        }

        if (json.has("ship_date")) {
            this.shipDate = json.getString("ship_date");
        }

        if (json.has("reshipment")) {
            this.reshipment = json.getBoolean("reshipment");
        }

        if (json.has("items")) {
            this.items = new ArrayList<>();
            JSONArray shipmentItemsArray = json.getJSONArray("items");
            for (int i = 0; i < shipmentItemsArray.length(); i++) {
                this.items.add(new Item(shipmentItemsArray.getJSONObject(i)));
            }
        }
    }

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

    @Override
    public String toString() {
        JSONStringer stringer = new JSONStringer();

        try {
            stringer.object();

            if (getId() > 0) {
                stringer.key("id");
                stringer.value(getId());
            }

            if (carrier != null) {
                stringer.key("carrier");
                stringer.value(carrier);
            }

            if (service != null) {
                stringer.key("service");
                stringer.value(service);
            }

            if (trackingNumber != null) {
                stringer.key("tracking_number");
                stringer.value(trackingNumber);
            }

            if (trackingUrl != null) {
                stringer.key("tracking_url");
                stringer.value(trackingUrl);
            }

            if (created != null) {
                stringer.key("created");
                stringer.value(created.getTime());
            }

            if (shipDate != null) {
                stringer.key("ship_date");
                stringer.value(shipDate);
            }

            stringer.key("reshipment");
            stringer.value(reshipment);

            if (items != null) {
                stringer.key("shipments");
                JSONArray itemsArray = new JSONArray();
                for (Item item : items) {
                    itemsArray.put(new JSONObject(item.toString()));
                }
                stringer.value(itemsArray);
            }

            stringer.endObject();
            return stringer.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return "";
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

        public Item(JSONObject json) throws JSONException {
            super(json);
            if (json.has("quantity")) {
                this.quantity = json.getInt("quantity");
            }
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        @Override
        public String toString() {
            JSONStringer stringer = new JSONStringer();

            try {
                stringer.object();

                if (getId() > 0) {
                    stringer.key("id");
                    stringer.value(getId());
                }

                if (quantity > 0) {
                    stringer.key("quantity");
                    stringer.value(quantity);
                }

                stringer.endObject();
                return stringer.toString();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return "";
        }
    }

}
