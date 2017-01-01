package in.clayfish.printful;

import in.clayfish.printful.enums.FileStatus;
import in.clayfish.printful.enums.OrderStatus;
import in.clayfish.printful.enums.SyncStatus;
import in.clayfish.printful.models.File;
import in.clayfish.printful.models.Order;
import in.clayfish.printful.models.PackingSlip;
import in.clayfish.printful.models.Product;
import in.clayfish.printful.models.Response;
import in.clayfish.printful.models.ShippingRequest;
import in.clayfish.printful.models.StoreData;
import in.clayfish.printful.models.SyncProduct;
import in.clayfish.printful.models.SyncVariant;
import in.clayfish.printful.models.includable.Country;
import in.clayfish.printful.models.info.ProductInfo;
import in.clayfish.printful.models.info.ShippingInfo;
import in.clayfish.printful.models.info.SyncProductInfo;
import in.clayfish.printful.models.info.SyncVariantInfo;
import in.clayfish.printful.models.info.TaxAddressInfo;
import in.clayfish.printful.models.info.TaxInfo;
import in.clayfish.printful.models.info.VariantInfo;
import in.clayfish.printful.models.info.WebhookInfo;

/**
 * @author shuklaalok7
 * @since 21/12/2016
 */
public interface Client {

    /**
     * @return All the products as returned from {@code GET /orders}
     */
    Response<Product> getAllProductList();

    /**
     * @param variantId
     * @return
     */
    Response<VariantInfo> getInfoAboutVariant(int variantId);

    /**
     * @param productId
     * @return
     */
    Response<ProductInfo> getProductsVariantsList(int productId);


    /**
     * @param status Filter by order status
     * @param offset Result set offset
     * @param limit  Number of items per page (max 100)
     * @return list of order objects from your store
     */
    Response<Order> getListOfOrders(OrderStatus status, int offset, int limit);

    /**
     * Creates a new order and optionally submits it for fulfillment
     *
     * @param order          Order data
     * @param confirm        Automatically submit the newly created order for fulfillment (skip the Draft phase)
     * @param updateExisting Try to update existing order if an order with the specified external_id already exists
     * @return
     */
    Response<Order> createANewOrder(Order order, boolean confirm, boolean updateExisting);


    /**
     * Returns order data by ID.
     *
     * @param orderId Order ID (integer)
     * @return
     */
    Response<Order> getOrderData(int orderId);

    /**
     * Returns order data by External ID.
     *
     * @param externalId External ID (String)
     * @return
     */
    Response<Order> getOrderData(String externalId);

    /**
     * Cancels pending order or draft. Charged amount is returned to the store owner's credit card.
     *
     * @param orderId ORDER ID
     * @return
     */
    Response<Order> cancelAnOrder(int orderId);

    /**
     * Cancels pending order or draft. Charged amount is returned to the store owner's credit card.
     *
     * @param externalId External ID
     * @return
     */
    Response<Order> cancelAnOrder(String externalId);

    /**
     * Updates unsubmitted order and optionally submits it for the fulfillment.<br/>
     * Note that you need to post only the fields that need to be changed, not all required fields.<br/>
     * If items array is given in the update data, the items will be:
     * <ol>
     * <li>updated, if the update data contains the item id or external_id parameter that alreay exists</li>
     * <li>deleted, if the request doesn't contain the item with previously existing id</li>
     * <li>created as new if the id is not given or does not already exist</li>
     * </ol>
     *
     * @param orderId
     * @param order   Order data
     * @param confirm Automatically submit the newly created order for fulfillment (skip the Draft phase)
     * @return
     */
    Response<Order> updateOrderData(int orderId, Order order, boolean confirm);

    /**
     * Updates unsubmitted order and optionally submits it for the fulfillment.<br/>
     * Note that you need to post only the fields that need to be changed, not all required fields.<br/>
     * If items array is given in the update data, the items will be:
     * <ol>
     * <li>updated, if the update data contains the item id or external_id parameter that alreay exists</li>
     * <li>deleted, if the request doesn't contain the item with previously existing id</li>
     * <li>created as new if the id is not given or does not already exist</li>
     * </ol>
     *
     * @param externalId
     * @param order      Order data
     * @param confirm    Automatically submit the newly created order for fulfillment (skip the Draft phase)
     * @return
     */
    Response<Order> updateOrderData(String externalId, Order order, boolean confirm);

    /**
     * Approves for fulfillment an order that was saved as a draft. Store owner's credit card is charged when the order is submitted for fulfillment.
     *
     * @param orderId
     * @return
     */
    Response<Order> confirmDraftForFulfillment(int orderId);

    /**
     * Approves for fulfillment an order that was saved as a draft. Store owner's credit card is charged when the order is submitted for fulfillment.
     *
     * @param externalId
     * @return Order data
     */
    Response<Order> confirmDraftForFulfillment(String externalId);

    // FILES API

    /**
     * @param status Filter by file status
     * @param offset Result set offset
     * @param limit  Number of items per page (max 100)
     * @return List of last added files
     */
    Response<File> getListOfFiles(FileStatus status, int offset, int limit);

    /**
     * Adds a new File to the library by providing URL of the file.<p>
     * If a file with identical URL already exists, then the original file is returned. If a file does not exist, a new file is created.
     *
     * @param file File data (url parameter is required)
     * @return File data
     */
    Response<File> addANewFile(File file);

    /**
     * @param fileId ID of the file
     * @return
     */
    Response<File> getFileInfo(int fileId);

    /**
     * Returns available shipping options and rates for the given list of products.
     *
     * @param request Order information
     * @return List of shipping rate information
     */
    Response<ShippingInfo> calculateShippingRates(ShippingRequest request);

    /**
     * Returns list of countries and states that are accepted by the Printful.
     *
     * @return List of countries
     */
    Response<Country> retrieveCountryList();

    /**
     * @return a list of countries and states that need Printful sales tax calculation.
     */
    Response<Country> retrieveStateListThatRequiresTaxCalculation();

    /**
     * Calculates sales tax rate for given address if required
     *
     * @param taxAddressInfo Tax address information
     * @return Sales tax rate for given address
     */
    Response<TaxInfo> calculateTaxRate(TaxAddressInfo taxAddressInfo);

    /**
     * @return configured webhook URL and list of webhook event types enabled for the store
     */
    Response<WebhookInfo> getWebhookConfig();

    /**
     * Allows to enable webhook URL for the store and select webhook event types that will be sent to this URL.<p>
     * Note that only one webhook URL can be active for a store, so calling this method disables all existing webhook configuration.<p>
     * Method returns current webhook configuration after the update.
     *
     * @param webhookInfo Webhook data
     * @return Webhook info
     */
    Response<WebhookInfo> setupWebhookConfig(WebhookInfo webhookInfo);

    /**
     * Removes the webhook URL and selected event types from the store.<p>
     * Method returns current webhook configuration after the update.
     *
     * @return Webhook info
     */
    Response<WebhookInfo> disableWebhookSupport();

    /**
     * Returns basic information about the currently authorized Printful store.
     *
     * @return Store information
     */
    Response<StoreData> getStoreInfo();

    /**
     * Modifies packing slip information of the currently authorized Printful store.
     *
     * @param packingSlip packing slip data
     * @return Packing slip data
     */
    Response<PackingSlip> changeStorePackingSlip(PackingSlip packingSlip);

    /**
     * @param status Filter by item status (synced/unsynced/all). If only some of the variants are
     *               synced,the product is returned by both unsynced and synced filters.
     * @param offset Result set offset
     * @param limit  Number of items per page (max 100)
     * @return list of Sync Product objects from your store
     */
    Response<SyncProduct> getListOfSyncProducts(SyncStatus status, int offset, int limit);

    /**
     * @param id Sync Product ID
     * @return information about a specific sync product and a list of sync variants for this product.
     */
    Response<SyncProductInfo> getInfoAboutSyncProductAndVariants(long id);

    /**
     * @param externalId External Sync Product ID (It will be prefixed with @)
     * @return information about a specific sync product and a list of sync variants for this product.
     */
    Response<SyncProductInfo> getInfoAboutSyncProductAndVariants(String externalId);

    /**
     * Deletes configuration information (variant_id, print files and options) and disables
     * automatic order importing for all synced variants of this Sync Product.
     *
     * @param id Sync Product ID
     * @return Sync Product data and array of Sync Variants
     */
    Response<SyncProductInfo> unlinkAllSyncedVariantsOfProduct(long id);

    /**
     * Deletes configuration information (variant_id, print files and options) and disables
     * automatic order importing for all synced variants of this Sync Product.
     *
     * @param externalId External Sync Product ID (It will be prefixed with @)
     * @return Sync Product data and array of Sync Variants
     */
    Response<SyncProductInfo> unlinkAllSyncedVariantsOfProduct(String externalId);

    /**
     * @param id Sync Variant ID
     * @return information about a Sync Variant
     */
    Response<SyncVariantInfo> getInfoAboutVariant(long id);

    /**
     * @param externalId External Sync Variant ID (It will be prefixed with @)
     * @return information about a Sync Variant
     */
    Response<SyncVariantInfo> getInfoAboutVariant(String externalId);

    /**
     * Allows to configure selected Sync Variant with correct product, print files and additional options.
     *
     * @param variantId   Sync Variant ID
     * @param syncVariant Sync Variant configuration data
     * @return Sync Variant data and Sync Product data
     */
    Response<SyncVariantInfo> updateLinkedProductAndPrintFileInfo(long variantId, SyncVariant syncVariant);

    /**
     * Allows to configure selected Sync Variant with correct product, print files and additional options.
     *
     * @param externalVariantId External Sync Variant ID (It will be prefixed with @)
     * @param syncVariant       Sync Variant configuration data
     * @return Sync Variant data and Sync Product data
     */
    Response<SyncVariantInfo> updateLinkedProductAndPrintFileInfo(String externalVariantId, SyncVariant syncVariant);

    /**
     * Deletes configuration information (variant_id, print files and options) and disables
     * automatic order importing for this Sync Variant.
     *
     * @param variantId Sync Variant ID
     * @return Sync Variant data and Sync Product data
     */
    Response<SyncVariantInfo> unlinkSyncedVariant(long variantId);

    /**
     * Deletes configuration information (variant_id, print files and options) and disables
     * automatic order importing for this Sync Variant.
     *
     * @param externalVariantId External Sync Variant ID (It will be prefixed with @)
     * @return Sync Variant data and Sync Product data
     */
    Response<SyncVariantInfo> unlinkSyncedVariant(String externalVariantId);

}
