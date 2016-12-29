package in.clayfish.printful.enums;

/**
 * See https://www.theprintful.com/docs/orders
 *
 * @author shuklaalok7
 * @since 24/12/2016
 */
public enum OrderStatus {
    /**
     * The order is created but is not yet submitted for fulfillment. You still can edit it and
     * confirm later.
     */
    DRAFT,

    /**
     * The order has been submitted for fulfillment, but is not yet accepted for fulfillment. You
     * can still cancel the order if you need.
     */
    PENDING,

    /**
     * Order was submitted for fulfillment but was returned for review because of an error (problem
     * with address, missing printfiles, charging has failed, etc.).
     */
    FAILED,

    /**
     * The order has been canceled and can no longer be processed. If the order was charged then
     * the amount has been returned to your credit card.
     */
    CANCELED,

    /**
     * The order is being fulfilled and can no longer be cancelled or modified. Contact customer
     * support if there are any issues with the order at this point.
     */
    IN_PROCESS,

    /**
     * The order has encountered a problem during the fulfillment that needs to be resolved
     * together with Printful customer service before fulfillment can continue.
     */
    ON_HOLD,

    /**
     * The order is partially fulfilled (some items are shipped already, the rest will follow)
     */
    PARTIAL,

    /**
     * All items have been shipped successfully
     */
    FULFILLED;


    /**
     * This is the easiest way to loosely find an OrderStatus object for a string
     *
     * @param term Term to search status with
     * @return Found OrderStatus
     */
    public static OrderStatus find(String term) {
        return OrderStatus.valueOf(term.trim().toUpperCase());
    }

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
