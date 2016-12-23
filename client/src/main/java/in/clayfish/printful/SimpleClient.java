package in.clayfish.printful;

import in.clayfish.printful.enums.OrderStatus;
import in.clayfish.printful.exceptions.NotImplementedException;
import in.clayfish.printful.models.Order;
import in.clayfish.printful.models.Product;
import in.clayfish.printful.models.ProductInfo;
import in.clayfish.printful.models.Response;
import in.clayfish.printful.models.VariantInfo;

/**
 * @author shuklaalok7
 * @since 24/12/2016
 */
public class SimpleClient implements Client {
    @Override
    public Response<Product> getAllProductList() {
        throw new NotImplementedException();
    }

    @Override
    public Response<VariantInfo> getInformationAboutVariant(int variantId) {
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

}
