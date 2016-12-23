package in.clayfish.printful;

import in.clayfish.printful.clients.CountryStateCodeApiClient;
import in.clayfish.printful.clients.EComPlatformSyncApiClient;
import in.clayfish.printful.clients.FileLibraryApiClient;
import in.clayfish.printful.clients.OrdersApiClient;
import in.clayfish.printful.clients.ProductCatalogApiClient;
import in.clayfish.printful.clients.ShippingRateApiClient;
import in.clayfish.printful.clients.StoreInfoApiClient;
import in.clayfish.printful.clients.TaxRateApiClient;
import in.clayfish.printful.clients.WebhookApiClient;
import in.clayfish.printful.enums.FileStatus;
import in.clayfish.printful.enums.OrderStatus;
import in.clayfish.printful.models.Configuration;
import in.clayfish.printful.models.File;
import in.clayfish.printful.models.Order;
import in.clayfish.printful.models.PackingSlip;
import in.clayfish.printful.models.Product;
import in.clayfish.printful.models.Response;
import in.clayfish.printful.models.ShippingRequest;
import in.clayfish.printful.models.StoreData;
import in.clayfish.printful.models.includable.Country;
import in.clayfish.printful.models.info.ProductInfo;
import in.clayfish.printful.models.info.ShippingInfo;
import in.clayfish.printful.models.info.TaxAddressInfo;
import in.clayfish.printful.models.info.TaxInfo;
import in.clayfish.printful.models.info.VariantInfo;
import in.clayfish.printful.models.info.WebhookInfo;

/**
 * @author shuklaalok7
 * @since 21/12/2016
 */
public class CompositeClient implements Client {

    private final CountryStateCodeApiClient countryStateCodeApiClient;
    private final EComPlatformSyncApiClient eComPlatformSyncApiClient;
    private final FileLibraryApiClient fileLibraryApiClient;
    private final OrdersApiClient ordersApiClient;
    private final ProductCatalogApiClient productCatalogApiClient;
    private final ShippingRateApiClient shippingRateApiClient;
    private final StoreInfoApiClient storeInfoApiClient;
    private final TaxRateApiClient taxRateApiClient;
    private final WebhookApiClient webhookApiClient;


    /**
     * @param apiKey
     */
    public CompositeClient(String apiKey) {
        this(apiKey, new Configuration());
    }

    /**
     * @param apiKey
     * @param configuration
     */
    public CompositeClient(String apiKey, Configuration configuration) {
        this.countryStateCodeApiClient = new CountryStateCodeApiClient(apiKey, configuration);
        this.eComPlatformSyncApiClient = new EComPlatformSyncApiClient(apiKey, configuration);
        this.fileLibraryApiClient = new FileLibraryApiClient(apiKey, configuration);
        this.ordersApiClient = new OrdersApiClient(apiKey, configuration);
        this.productCatalogApiClient = new ProductCatalogApiClient(apiKey, configuration);
        this.shippingRateApiClient = new ShippingRateApiClient(apiKey, configuration);
        this.storeInfoApiClient = new StoreInfoApiClient(apiKey, configuration);
        this.taxRateApiClient = new TaxRateApiClient(apiKey, configuration);
        this.webhookApiClient = new WebhookApiClient(apiKey, configuration);
    }

    @Override
    public Response<Product> getAllProductList() {
        return productCatalogApiClient.getAllProductList();
    }

    @Override
    public Response<VariantInfo> getInformationAboutVariant(int variantId) {
        return productCatalogApiClient.getInformationAboutVariant(variantId);
    }

    @Override
    public Response<ProductInfo> getProductsVariantsList(int productId) {
        return productCatalogApiClient.getProductsVariantsList(productId);
    }

    @Override
    public Response<Order> getListOfOrders(OrderStatus status, int offset, int limit) {
        return ordersApiClient.getListOfOrders(status, offset, limit);
    }

    @Override
    public Response<Order> createANewOrder(Order order, boolean confirm, boolean updateExisting) {
        return ordersApiClient.createANewOrder(order, confirm, updateExisting);
    }

    @Override
    public Response<Order> getOrderData(int orderId) {
        return ordersApiClient.getOrderData(orderId);
    }

    @Override
    public Response<Order> getOrderData(String externalId) {
        return ordersApiClient.getOrderData(externalId);
    }

    @Override
    public Response<Order> cancelAnOrder(int orderId) {
        return ordersApiClient.cancelAnOrder(orderId);
    }

    @Override
    public Response<Order> cancelAnOrder(String externalId) {
        return ordersApiClient.cancelAnOrder(externalId);
    }

    @Override
    public Response<Order> updateOrderData(int orderId, Order order, boolean confirm) {
        return ordersApiClient.updateOrderData(orderId, order, confirm);
    }

    @Override
    public Response<Order> updateOrderData(String externalId, Order order, boolean confirm) {
        return ordersApiClient.updateOrderData(externalId, order, confirm);
    }

    @Override
    public Response<Order> confirmDraftForFulfillment(int orderId) {
        return ordersApiClient.confirmDraftForFulfillment(orderId);
    }

    @Override
    public Response<Order> confirmDraftForFulfillment(String externalId) {
        return ordersApiClient.confirmDraftForFulfillment(externalId);
    }

    @Override
    public Response<File> getListOfFiles(FileStatus status, int offset, int limit) {
        return fileLibraryApiClient.getListOfFiles(status, offset, limit);
    }

    @Override
    public Response<File> addANewFile(File file) {
        return fileLibraryApiClient.addANewFile(file);
    }

    @Override
    public Response<File> getFileInfo(int fileId) {
        return fileLibraryApiClient.getFileInfo(fileId);
    }

    @Override
    public Response<ShippingInfo> calculateShippingRates(ShippingRequest request) {
        return shippingRateApiClient.calculateShippingRates(request);
    }

    @Override
    public Response<Country> retrieveCountryList() {
        return countryStateCodeApiClient.retrieveCountryList();
    }

    @Override
    public Response<Country> retrieveStateListThatRequiresTaxCalculation() {
        return taxRateApiClient.retrieveStateListThatRequiresTaxCalculation();
    }

    @Override
    public Response<TaxInfo> calculateTaxRate(TaxAddressInfo taxAddressInfo) {
        return taxRateApiClient.calculateTaxRate(taxAddressInfo);
    }

    @Override
    public Response<WebhookInfo> getWebhookConfig() {
        return webhookApiClient.getWebhookConfig();
    }

    @Override
    public Response<WebhookInfo> setupWebhookConfig(WebhookInfo webhookInfo) {
        return webhookApiClient.setupWebhookConfig(webhookInfo);
    }

    @Override
    public Response<WebhookInfo> disableWebhookSupport() {
        return webhookApiClient.disableWebhookSupport();
    }

    @Override
    public StoreData getStoreInfo() {
        return storeInfoApiClient.getStoreInfo();
    }

    @Override
    public PackingSlip changeStorePackingSlip(PackingSlip packingSlip) {
        return storeInfoApiClient.changeStorePackingSlip(packingSlip);
    }

}
