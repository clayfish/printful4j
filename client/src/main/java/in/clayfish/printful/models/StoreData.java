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

}
