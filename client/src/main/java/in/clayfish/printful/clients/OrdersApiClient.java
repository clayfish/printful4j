package in.clayfish.printful.clients;

import android.util.Base64;

import in.clayfish.printful.SimpleClient;
import in.clayfish.printful.enums.OrderStatus;
import in.clayfish.printful.models.Configuration;
import in.clayfish.printful.models.Order;
import in.clayfish.printful.models.Response;

/**
 * See https://www.theprintful.com/docs/orders
 * <p>
 * TODO implement methods
 *
 * @author shuklaalok7
 * @since 24/12/2016
 */
public class OrdersApiClient extends SimpleClient {

    private final static String TAG = OrdersApiClient.class.getSimpleName();

    private final String base64Key;
    private Configuration configuration;

    /**
     * @param apiKey
     */
    public OrdersApiClient(String apiKey) {
        this(apiKey, new Configuration());
    }

    /**
     * @param apiKey
     * @param configuration
     */
    public OrdersApiClient(String apiKey, Configuration configuration) {
        this.base64Key = Base64.encodeToString(apiKey.getBytes(), Base64.NO_WRAP);
        this.configuration = configuration;
    }

    @Override
    public Response<Order> getListOfOrders(OrderStatus status, int offset, int limit) {
        return super.getListOfOrders(status, offset, limit);
    }

    @Override
    public Response<Order> createANewOrder(Order order, boolean confirm, boolean updateExisting) {
        return super.createANewOrder(order, confirm, updateExisting);
    }

    @Override
    public Response<Order> getOrderData(int orderId) {
        return super.getOrderData(orderId);
    }

    @Override
    public Response<Order> getOrderData(String externalId) {
        return super.getOrderData(externalId);
    }

    @Override
    public Response<Order> cancelAnOrder(int orderId) {
        return super.cancelAnOrder(orderId);
    }

    @Override
    public Response<Order> cancelAnOrder(String externalId) {
        return super.cancelAnOrder(externalId);
    }

    @Override
    public Response<Order> updateOrderData(int orderId, Order order, boolean confirm) {
        return super.updateOrderData(orderId, order, confirm);
    }

    @Override
    public Response<Order> updateOrderData(String externalId, Order order, boolean confirm) {
        return super.updateOrderData(externalId, order, confirm);
    }

    @Override
    public Response<Order> confirmDraftForFulfillment(int orderId) {
        return super.confirmDraftForFulfillment(orderId);
    }

    @Override
    public Response<Order> confirmDraftForFulfillment(String externalId) {
        return super.confirmDraftForFulfillment(externalId);
    }
}
