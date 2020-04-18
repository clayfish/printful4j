package `in`.clayfish.printful.enums

/**
 * See https://www.theprintful.com/docs/webhooks
 *
 * @author shuklaalok7
 * @since 1/01/2017
 */
enum class WebhookEvent {
    /**
     * Calls POST http://example.com/your-webhook-url
     * Is called when a shipment with all or part of the ordered items is shipped.
     * If the order is shipped in multiple packages, this event will be called for every package sent.
     * If some items are reshipped, a shipping notification will be sent again for the same items.
     */
    PACKAGE_SHIPPED,

    /**
     * Calls POST http://example.com/your-webhook-url
     * Is called when a confirmed order changes its status to [OrderStatus.FAILED].
     * It can happen if printfiles can not be downloaded, are not valid image files or when charging
     * of the payment card fails.
     */
    ORDER_FAILED,

    /**
     * Calls POST http://example.com/your-webhook-url
     * Is called when a confirmed order changes its status to [OrderStatus.CANCELED].
     * It can happen when a submitted order is canceled from the dashboard or through the API or
     * when the order is cancelled by the Printful staff.
     */
    ORDER_CANCELED,

    /**
     * Is called when a new product or variant is imported from store's e-commerce integration.
     *
     * @see in.clayfish.printful.clients.EComPlatformSyncApiClient
     */
    PRODUCT_SYNCED,

    /**
     * Is called when stock is updated for some of product's variants.
     * Contains product id and ids of it's discontinued variants and variants that are out of stock.
     * Variant ids that are not present should be considered as active and in stock.
     */
    STOCK_UPDATED;

    override fun toString() = name.toLowerCase()
}
