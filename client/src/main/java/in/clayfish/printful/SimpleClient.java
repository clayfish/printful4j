package in.clayfish.printful;

import in.clayfish.printful.enums.FileStatus;
import in.clayfish.printful.enums.OrderStatus;
import in.clayfish.printful.enums.SyncStatus;
import in.clayfish.printful.exceptions.NotImplementedException;
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
 * @since 24/12/2016
 */
public abstract class SimpleClient implements Client {
    @Override
    public Response<Product> getAllProductList() {
        throw new NotImplementedException();
    }

    @Override
    public Response<VariantInfo> getInfoAboutVariant(int variantId) {
        throw new NotImplementedException();
    }

    @Override
    public Response<ProductInfo> getProductsVariantsList(int productId) {
        throw new NotImplementedException();
    }

    @Override
    public Response<Order> getListOfOrders(OrderStatus status, int offset, int limit) {
        throw new NotImplementedException();
    }

    @Override
    public Response<Order> createANewOrder(Order order, boolean confirm, boolean updateExisting) {
        throw new NotImplementedException();
    }

    @Override
    public Response<Order> getOrderData(int orderId) {
        throw new NotImplementedException();
    }

    @Override
    public Response<Order> getOrderData(String externalId) {
        throw new NotImplementedException();
    }

    @Override
    public Response<Order> cancelAnOrder(int orderId) {
        throw new NotImplementedException();
    }

    @Override
    public Response<Order> cancelAnOrder(String externalId) {
        throw new NotImplementedException();
    }

    @Override
    public Response<Order> updateOrderData(int orderId, Order order, boolean confirm) {
        throw new NotImplementedException();
    }

    @Override
    public Response<Order> updateOrderData(String externalId, Order order, boolean confirm) {
        throw new NotImplementedException();
    }

    @Override
    public Response<Order> confirmDraftForFulfillment(int orderId) {
        throw new NotImplementedException();
    }

    @Override
    public Response<Order> confirmDraftForFulfillment(String externalId) {
        throw new NotImplementedException();
    }

    @Override
    public Response<File> getListOfFiles(FileStatus status, int offset, int limit) {
        throw new NotImplementedException();
    }

    @Override
    public Response<File> addANewFile(File file) {
        throw new NotImplementedException();
    }

    @Override
    public Response<File> getFileInfo(int fileId) {
        throw new NotImplementedException();
    }

    @Override
    public Response<ShippingInfo> calculateShippingRates(ShippingRequest request) {
        throw new NotImplementedException();
    }

    @Override
    public Response<Country> retrieveCountryList() {
        throw new NotImplementedException();
    }

    @Override
    public Response<Country> retrieveStateListThatRequiresTaxCalculation() {
        throw new NotImplementedException();
    }

    @Override
    public Response<TaxInfo> calculateTaxRate(TaxAddressInfo taxAddressInfo) {
        throw new NotImplementedException();
    }

    @Override
    public Response<WebhookInfo> getWebhookConfig() {
        throw new NotImplementedException();
    }

    @Override
    public Response<WebhookInfo> setupWebhookConfig(WebhookInfo webhookInfo) {
        throw new NotImplementedException();
    }

    @Override
    public Response<WebhookInfo> disableWebhookSupport() {
        throw new NotImplementedException();
    }

    @Override
    public Response<StoreData> getStoreInfo() {
        throw new NotImplementedException();
    }

    @Override
    public Response<PackingSlip> changeStorePackingSlip(PackingSlip packingSlip) {
        throw new NotImplementedException();
    }

    @Override
    public Response<SyncProduct> getListOfSyncProducts(SyncStatus status, int offset, int limit) {
        throw new NotImplementedException();
    }

    @Override
    public Response<SyncProductInfo> getInfoAboutSyncProductAndVariants(long id) {
        throw new NotImplementedException();
    }

    @Override
    public Response<SyncProductInfo> getInfoAboutSyncProductAndVariants(String externalId) {
        throw new NotImplementedException();
    }

    @Override
    public Response<SyncProductInfo> unlinkAllSyncedVariantsOfProduct(long id) {
        throw new NotImplementedException();
    }

    @Override
    public Response<SyncProductInfo> unlinkAllSyncedVariantsOfProduct(String externalId) {
        throw new NotImplementedException();
    }

    @Override
    public Response<SyncVariantInfo> getInfoAboutVariant(long id) {
        throw new NotImplementedException();
    }

    @Override
    public Response<SyncVariantInfo> getInfoAboutVariant(String externalId) {
        throw new NotImplementedException();
    }

    @Override
    public Response<SyncVariantInfo> updateLinkedProductAndPrintFileInfo(long variantId, SyncVariant syncVariant) {
        throw new NotImplementedException();
    }

    @Override
    public Response<SyncVariantInfo> updateLinkedProductAndPrintFileInfo(String externalVariantId, SyncVariant syncVariant) {
        throw new NotImplementedException();
    }

    @Override
    public Response<SyncVariantInfo> unlinkSyncedVariant(long variantId) {
        throw new NotImplementedException();
    }

    @Override
    public Response<SyncVariantInfo> unlinkSyncedVariant(String externalVariantId) {
        throw new NotImplementedException();
    }

}
