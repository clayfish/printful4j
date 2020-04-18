package `in`.clayfish.printful

import `in`.clayfish.printful.enums.FileStatus
import `in`.clayfish.printful.enums.OrderStatus
import `in`.clayfish.printful.enums.SyncStatus
import `in`.clayfish.printful.models.*
import `in`.clayfish.printful.models.includable.Country
import `in`.clayfish.printful.models.info.*

/**
 * @author shuklaalok7
 * @since 21/12/2016
 */
interface Client {
    /**
     * @return All the products as returned from `GET /orders`
     */
    val allProductList: Response<Product?>?

    /**
     * @param variantId
     * @return
     */
    fun getInfoAboutVariant(variantId: Int): Response<VariantInfo?>?

    /**
     * @param productId
     * @return
     */
    fun getProductsVariantsList(productId: Int): Response<ProductInfo?>?

    /**
     * @param status Filter by order status
     * @param offset Result set offset
     * @param limit  Number of items per page (max 100)
     * @return list of order objects from your store
     */
    fun getListOfOrders(status: OrderStatus?, offset: Int, limit: Int): Response<Order?>?

    /**
     * Creates a new order and optionally submits it for fulfillment
     *
     * @param order          Order data
     * @param confirm        Automatically submit the newly created order for fulfillment (skip the Draft phase)
     * @param updateExisting Try to update existing order if an order with the specified external_id already exists
     * @return
     */
    fun createANewOrder(order: Order?, confirm: Boolean, updateExisting: Boolean): Response<Order?>?

    /**
     * Returns order data by ID.
     *
     * @param orderId Order ID (integer)
     * @return
     */
    fun getOrderData(orderId: Int): Response<Order?>?

    /**
     * Returns order data by External ID.
     *
     * @param externalId External ID (String)
     * @return
     */
    fun getOrderData(externalId: String?): Response<Order?>?

    /**
     * Cancels pending order or draft. Charged amount is returned to the store owner's credit card.
     *
     * @param orderId ORDER ID
     * @return
     */
    fun cancelAnOrder(orderId: Int): Response<Order?>?

    /**
     * Cancels pending order or draft. Charged amount is returned to the store owner's credit card.
     *
     * @param externalId External ID
     * @return
     */
    fun cancelAnOrder(externalId: String?): Response<Order?>?

    /**
     * Updates unsubmitted order and optionally submits it for the fulfillment.<br></br>
     * Note that you need to post only the fields that need to be changed, not all required fields.<br></br>
     * If items array is given in the update data, the items will be:
     *
     *  1. updated, if the update data contains the item id or external_id parameter that alreay exists
     *  1. deleted, if the request doesn't contain the item with previously existing id
     *  1. created as new if the id is not given or does not already exist
     *
     *
     * @param orderId
     * @param order   Order data
     * @param confirm Automatically submit the newly created order for fulfillment (skip the Draft phase)
     * @return
     */
    fun updateOrderData(orderId: Int, order: Order?, confirm: Boolean): Response<Order?>?

    /**
     * Updates unsubmitted order and optionally submits it for the fulfillment.<br></br>
     * Note that you need to post only the fields that need to be changed, not all required fields.<br></br>
     * If items array is given in the update data, the items will be:
     *
     *  1. updated, if the update data contains the item id or external_id parameter that alreay exists
     *  1. deleted, if the request doesn't contain the item with previously existing id
     *  1. created as new if the id is not given or does not already exist
     *
     *
     * @param externalId
     * @param order      Order data
     * @param confirm    Automatically submit the newly created order for fulfillment (skip the Draft phase)
     * @return
     */
    fun updateOrderData(externalId: String?, order: Order?, confirm: Boolean): Response<Order?>?

    /**
     * Approves for fulfillment an order that was saved as a draft. Store owner's credit card is charged when the order is submitted for fulfillment.
     *
     * @param orderId
     * @return
     */
    fun confirmDraftForFulfillment(orderId: Int): Response<Order?>?

    /**
     * Approves for fulfillment an order that was saved as a draft. Store owner's credit card is charged when the order is submitted for fulfillment.
     *
     * @param externalId
     * @return Order data
     */
    fun confirmDraftForFulfillment(externalId: String?): Response<Order?>?
    // FILES API
    /**
     * @param status Filter by file status
     * @param offset Result set offset
     * @param limit  Number of items per page (max 100)
     * @return List of last added files
     */
    fun getListOfFiles(status: FileStatus?, offset: Int, limit: Int): Response<File?>?

    /**
     * Adds a new File to the library by providing URL of the file.
     *
     *
     * If a file with identical URL already exists, then the original file is returned. If a file does not exist, a new file is created.
     *
     * @param file File data (url parameter is required)
     * @return File data
     */
    fun addANewFile(file: File?): Response<File?>?

    /**
     * @param fileId ID of the file
     * @return
     */
    fun getFileInfo(fileId: Int): Response<File?>?

    /**
     * Returns available shipping options and rates for the given list of products.
     *
     * @param request Order information
     * @return List of shipping rate information
     */
    fun calculateShippingRates(request: ShippingRequest?): Response<ShippingInfo?>?

    /**
     * Returns list of countries and states that are accepted by the Printful.
     *
     * @return List of countries
     */
    fun retrieveCountryList(): Response<Country?>?

    /**
     * @return a list of countries and states that need Printful sales tax calculation.
     */
    fun retrieveStateListThatRequiresTaxCalculation(): Response<Country?>?

    /**
     * Calculates sales tax rate for given address if required
     *
     * @param taxAddressInfo Tax address information
     * @return Sales tax rate for given address
     */
    fun calculateTaxRate(taxAddressInfo: TaxAddressInfo?): Response<TaxInfo?>?

    /**
     * @return configured webhook URL and list of webhook event types enabled for the store
     */
    val webhookConfig: Response<WebhookInfo?>?

    /**
     * Allows to enable webhook URL for the store and select webhook event types that will be sent to this URL.
     *
     *
     * Note that only one webhook URL can be active for a store, so calling this method disables all existing webhook configuration.
     *
     *
     * Method returns current webhook configuration after the update.
     *
     * @param webhookInfo Webhook data
     * @return Webhook info
     */
    fun setupWebhookConfig(webhookInfo: WebhookInfo?): Response<WebhookInfo?>?

    /**
     * Removes the webhook URL and selected event types from the store.
     *
     *
     * Method returns current webhook configuration after the update.
     *
     * @return Webhook info
     */
    fun disableWebhookSupport(): Response<WebhookInfo?>?

    /**
     * Returns basic information about the currently authorized Printful store.
     *
     * @return Store information
     */
    val storeInfo: Response<StoreData?>?

    /**
     * Modifies packing slip information of the currently authorized Printful store.
     *
     * @param packingSlip packing slip data
     * @return Packing slip data
     */
    fun changeStorePackingSlip(packingSlip: PackingSlip?): Response<PackingSlip?>?

    /**
     * @param status Filter by item status (synced/unsynced/all). If only some of the variants are
     * synced,the product is returned by both unsynced and synced filters.
     * @param offset Result set offset
     * @param limit  Number of items per page (max 100)
     * @return list of Sync Product objects from your store
     */
    fun getListOfSyncProducts(status: SyncStatus?, offset: Int, limit: Int): Response<SyncProduct?>?

    /**
     * @param id Sync Product ID
     * @return information about a specific sync product and a list of sync variants for this product.
     */
    fun getInfoAboutSyncProductAndVariants(id: Long): Response<SyncProductInfo?>?

    /**
     * @param externalId External Sync Product ID (It will be prefixed with @)
     * @return information about a specific sync product and a list of sync variants for this product.
     */
    fun getInfoAboutSyncProductAndVariants(externalId: String?): Response<SyncProductInfo?>?

    /**
     * Deletes configuration information (variant_id, print files and options) and disables
     * automatic order importing for all synced variants of this Sync Product.
     *
     * @param id Sync Product ID
     * @return Sync Product data and array of Sync Variants
     */
    fun unlinkAllSyncedVariantsOfProduct(id: Long): Response<SyncProductInfo?>?

    /**
     * Deletes configuration information (variant_id, print files and options) and disables
     * automatic order importing for all synced variants of this Sync Product.
     *
     * @param externalId External Sync Product ID (It will be prefixed with @)
     * @return Sync Product data and array of Sync Variants
     */
    fun unlinkAllSyncedVariantsOfProduct(externalId: String?): Response<SyncProductInfo?>?

    /**
     * @param id Sync Variant ID
     * @return information about a Sync Variant
     */
    fun getInfoAboutVariant(id: Long): Response<SyncVariantInfo?>?

    /**
     * @param externalId External Sync Variant ID (It will be prefixed with @)
     * @return information about a Sync Variant
     */
    fun getInfoAboutVariant(externalId: String?): Response<SyncVariantInfo?>?

    /**
     * Allows to configure selected Sync Variant with correct product, print files and additional options.
     *
     * @param variantId   Sync Variant ID
     * @param syncVariant Sync Variant configuration data
     * @return Sync Variant data and Sync Product data
     */
    fun updateLinkedProductAndPrintFileInfo(variantId: Long, syncVariant: SyncVariant?): Response<SyncVariantInfo?>?

    /**
     * Allows to configure selected Sync Variant with correct product, print files and additional options.
     *
     * @param externalVariantId External Sync Variant ID (It will be prefixed with @)
     * @param syncVariant       Sync Variant configuration data
     * @return Sync Variant data and Sync Product data
     */
    fun updateLinkedProductAndPrintFileInfo(externalVariantId: String?, syncVariant: SyncVariant?): Response<SyncVariantInfo?>?

    /**
     * Deletes configuration information (variant_id, print files and options) and disables
     * automatic order importing for this Sync Variant.
     *
     * @param variantId Sync Variant ID
     * @return Sync Variant data and Sync Product data
     */
    fun unlinkSyncedVariant(variantId: Long): Response<SyncVariantInfo?>?

    /**
     * Deletes configuration information (variant_id, print files and options) and disables
     * automatic order importing for this Sync Variant.
     *
     * @param externalVariantId External Sync Variant ID (It will be prefixed with @)
     * @return Sync Variant data and Sync Product data
     */
    fun unlinkSyncedVariant(externalVariantId: String?): Response<SyncVariantInfo?>?
}
