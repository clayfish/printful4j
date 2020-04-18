/*
 * MIT License
 *
 * Copyright (c) 2016-2019 ClayFish Technologies LLP
 * Copyright (c) 2020 ClayFish Technologies
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package in.clayfish.printful.models;

import java.util.ArrayList;
import java.util.Collection;
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

    /**
     * Builds an order
     */
    public static class Builder implements in.clayfish.printful.models.Builder<Order> {
        private Order order;
        private boolean checkValidity;

        public Builder() {
            this.order = new Order();
        }

        @Override
        public Builder check() {
            this.checkValidity = true;
            return this;
        }

        /**
         * @param id ID of the order
         * @return Builder object
         */
        public Builder id(long id) {
            this.order.setId(id);
            return this;
        }

        /**
         * @param externalId
         * @return Builder object
         */
        public Builder externalId(String externalId) {
            this.order.externalId = externalId;
            return this;
        }

        /**
         * @param recipient
         * @return Builder object
         */
        public Builder recipient(Address recipient) {
            this.order.recipient = recipient;
            return this;
        }

        /**
         * @param status
         * @return Builder object
         */
        public Builder status(OrderStatus status) {
            this.order.status = status;
            return this;
        }

        /**
         * @param costs
         * @return Builder object
         */
        public Builder costs(Cost costs) {
            this.order.costs = costs;
            return this;
        }

        /**
         * @param retailCosts
         * @return Builder object
         */
        public Builder retailCosts(Cost retailCosts) {
            this.order.retailCosts = retailCosts;
            return this;
        }

        /**
         * @param item Item to add in this order
         * @return Builder object
         */
        public Builder item(Item item) {
            if (order.items == null) {
                order.items = new ArrayList<>();
            }
            order.items.add(item);
            return this;
        }

        /**
         * @param items Items to add in this order (it does not replace other items added before it)
         * @return Builder object
         */
        public Builder items(Collection<Item> items) {
            if (order.items == null) {
                order.items = new ArrayList<>();
            }
            order.items.addAll(items);
            return this;
        }

        /**
         * @param gift GiftData to add
         * @return Builder object
         */
        public Builder gift(GiftData gift) {
            this.order.gift = gift;
            return this;
        }

        @Override
        public Order build() {
            if (checkValidity) {
                checkValidity();
            }
            return order;
        }

        /**
         *
         */
        private void checkValidity() {
            // TODO implement
        }

    }

}
