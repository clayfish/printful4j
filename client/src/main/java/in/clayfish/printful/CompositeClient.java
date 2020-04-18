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
import in.clayfish.printful.enums.SyncStatus;
import in.clayfish.printful.models.Configuration;
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
     * @param apiKey        YOUR_API_KEY
     * @param configuration configuration object
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
    public Response<VariantInfo> getInfoAboutVariant(int variantId) {
        return productCatalogApiClient.getInfoAboutVariant(variantId);
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
    public Response<StoreData> getStoreInfo() {
        return storeInfoApiClient.getStoreInfo();
    }

    @Override
    public Response<PackingSlip> changeStorePackingSlip(PackingSlip packingSlip) {
        return storeInfoApiClient.changeStorePackingSlip(packingSlip);
    }

    @Override
    public Response<SyncProduct> getListOfSyncProducts(SyncStatus status, int offset, int limit) {
        return eComPlatformSyncApiClient.getListOfSyncProducts(status, offset, limit);
    }

    @Override
    public Response<SyncProductInfo> getInfoAboutSyncProductAndVariants(long id) {
        return eComPlatformSyncApiClient.getInfoAboutSyncProductAndVariants(id);
    }

    @Override
    public Response<SyncProductInfo> getInfoAboutSyncProductAndVariants(String externalId) {
        return eComPlatformSyncApiClient.getInfoAboutSyncProductAndVariants(externalId);
    }

    @Override
    public Response<SyncProductInfo> unlinkAllSyncedVariantsOfProduct(long id) {
        return eComPlatformSyncApiClient.unlinkAllSyncedVariantsOfProduct(id);
    }

    @Override
    public Response<SyncProductInfo> unlinkAllSyncedVariantsOfProduct(String externalId) {
        return eComPlatformSyncApiClient.unlinkAllSyncedVariantsOfProduct(externalId);
    }

    @Override
    public Response<SyncVariantInfo> getInfoAboutVariant(long id) {
        return eComPlatformSyncApiClient.getInfoAboutVariant(id);
    }

    @Override
    public Response<SyncVariantInfo> getInfoAboutVariant(String externalId) {
        return eComPlatformSyncApiClient.getInfoAboutVariant(externalId);
    }

    @Override
    public Response<SyncVariantInfo> updateLinkedProductAndPrintFileInfo(long variantId, SyncVariant syncVariant) {
        return eComPlatformSyncApiClient.updateLinkedProductAndPrintFileInfo(variantId, syncVariant);
    }

    @Override
    public Response<SyncVariantInfo> updateLinkedProductAndPrintFileInfo(String externalVariantId, SyncVariant syncVariant) {
        return eComPlatformSyncApiClient.updateLinkedProductAndPrintFileInfo(externalVariantId, syncVariant);
    }

    @Override
    public Response<SyncVariantInfo> unlinkSyncedVariant(long variantId) {
        return eComPlatformSyncApiClient.unlinkSyncedVariant(variantId);
    }

    @Override
    public Response<SyncVariantInfo> unlinkSyncedVariant(String externalVariantId) {
        return eComPlatformSyncApiClient.unlinkSyncedVariant(externalVariantId);
    }

}
