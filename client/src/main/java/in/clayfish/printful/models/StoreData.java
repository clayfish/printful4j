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
