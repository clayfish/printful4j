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

import java.util.Date;

import in.clayfish.printful.models.includable.Address;
import in.clayfish.printful.models.info.CardInfo;

/**
 * @author shuklaalok7
 * @since 24/12/2016
 */
public class StoreData extends Entity {

    /**
     * Store name
     */
    private String name;

    /**
     * Store website URL
     */
    private String website;

    /**
     * Custom return address (if enabled)
     */
    private Address returnAddress;

    /**
     * Address	Default billing address (if configured)
     */
    private Address billingAddress;

    /**
     * Default payment card (if configured)
     */
    private CardInfo paymentCard;

    /**
     * Packing slip information of the current store
     */
    private PackingSlip packingSlip;
    private Date created;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Address getReturnAddress() {
        return returnAddress;
    }

    public void setReturnAddress(Address returnAddress) {
        this.returnAddress = returnAddress;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public CardInfo getPaymentCard() {
        return paymentCard;
    }

    public void setPaymentCard(CardInfo paymentCard) {
        this.paymentCard = paymentCard;
    }

    public PackingSlip getPackingSlip() {
        return packingSlip;
    }

    public void setPackingSlip(PackingSlip packingSlip) {
        this.packingSlip = packingSlip;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

}
