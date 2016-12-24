package in.clayfish.printful.clients;

import android.util.Base64;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Connection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import in.clayfish.printful.SimpleClient;
import in.clayfish.printful.enums.OrderStatus;
import in.clayfish.printful.models.Configuration;
import in.clayfish.printful.models.Order;
import in.clayfish.printful.models.Response;
import in.clayfish.printful.utils.LibUtils;

/**
 * See https://www.theprintful.com/docs/orders
 * <p>
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
        try {
            Connection connection = LibUtils.createConnection(base64Key, "orders", configuration);

            if (status != null) {
                connection.data("status", status.name().toLowerCase());
            }

            if (offset > 0) {
                connection.data("offset", String.valueOf(offset));
            }

            if (limit > 0) {
                if (limit > 100) {
                    limit = 100;
                }
                connection.data("limit", String.valueOf(limit));
            }

            return createResponseForMultipleOrders(connection.execute().body());
        } catch (IOException e) {
            Log.e(TAG, "Error occurred while getting list of orders.", e);
        }

        return null;
    }

    @Override
    public Response<Order> createANewOrder(Order order, boolean confirm, boolean updateExisting) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null.");
        }

        try {
            String response = LibUtils.createConnection(base64Key, "orders", configuration)
                    .method(Connection.Method.POST).requestBody(order.toString()).data("confirm",
                            String.valueOf(confirm), "updateExisting",
                            String.valueOf(updateExisting))
                    .execute().body();

            return createResponseForSingleOrder(response);
        } catch (IOException e) {
            Log.e(TAG, "Error occurred while creating a new order.", e);
        }

        return null;
    }

    @Override
    public Response<Order> getOrderData(int orderId) {
        try {
            String response = LibUtils.createConnection(base64Key, "orders/" + orderId, configuration).execute().body();
            return createResponseForSingleOrder(response);
        } catch (IOException e) {
            Log.e(TAG, "Error occurred while getting order data for " + orderId, e);
        }
        return null;
    }

    @Override
    public Response<Order> getOrderData(String externalId) {
        externalId = LibUtils.curateExternalId(externalId);

        try {
            String response = LibUtils.createConnection(base64Key, "orders/" + externalId, configuration).execute().body();
            return createResponseForSingleOrder(response);
        } catch (IOException e) {
            Log.e(TAG, "Error occurred while getting order data for " + externalId, e);
        }
        return null;
    }

    @Override
    public Response<Order> cancelAnOrder(int orderId) {
        try {
            String response = LibUtils.createConnection(base64Key, "orders/" + orderId, configuration)
                    .method(Connection.Method.DELETE).execute().body();
            return createResponseForSingleOrder(response);
        } catch (IOException e) {
            Log.e(TAG, "Error occurred while cancelling order " + orderId, e);
        }
        return null;
    }

    @Override
    public Response<Order> cancelAnOrder(String externalId) {
        externalId = LibUtils.curateExternalId(externalId);
        try {
            String response = LibUtils.createConnection(base64Key, "orders/" + externalId, configuration)
                    .method(Connection.Method.DELETE).execute().body();
            return createResponseForSingleOrder(response);
        } catch (IOException e) {
            Log.e(TAG, "Error occurred while cancelling order " + externalId, e);
        }
        return null;
    }

    @Override
    public Response<Order> updateOrderData(int orderId, Order order, boolean confirm) {
        try {
            String response = LibUtils.createConnection(base64Key, "orders/" + orderId, configuration)
                    .method(Connection.Method.PUT)
                    .requestBody(order.toString())
                    .data("confirm", String.valueOf(confirm))
                    .execute().body();
            return createResponseForSingleOrder(response);
        } catch (IOException e) {
            Log.e(TAG, "Error occurred while updating order " + orderId, e);
        }
        return null;
    }

    @Override
    public Response<Order> updateOrderData(String externalId, Order order, boolean confirm) {
        externalId = LibUtils.curateExternalId(externalId);
        try {
            String response = LibUtils.createConnection(base64Key, "orders/" + externalId, configuration)
                    .method(Connection.Method.PUT)
                    .requestBody(order.toString())
                    .data("confirm", String.valueOf(confirm))
                    .execute().body();
            return createResponseForSingleOrder(response);
        } catch (IOException e) {
            Log.e(TAG, "Error occurred while updating order " + externalId, e);
        }
        return null;
    }

    @Override
    public Response<Order> confirmDraftForFulfillment(int orderId) {
        try {
            String response = LibUtils.createConnection(base64Key,
                    String.format(Locale.ENGLISH, "orders/%d/confirm", orderId), configuration)
                    .method(Connection.Method.POST).execute().body();
            return createResponseForSingleOrder(response);
        } catch (IOException e) {
            Log.e(TAG, "Error occurred while confirming order " + orderId, e);
        }
        return null;
    }

    @Override
    public Response<Order> confirmDraftForFulfillment(String externalId) {
        externalId = LibUtils.curateExternalId(externalId);
        try {
            String response = LibUtils.createConnection(base64Key,
                    String.format(Locale.ENGLISH, "orders/%s/confirm", externalId), configuration)
                    .method(Connection.Method.POST).execute().body();
            return createResponseForSingleOrder(response);
        } catch (IOException e) {
            Log.e(TAG, "Error occurred while confirming order " + externalId, e);
        }
        return null;
    }

    /**
     * @param response The response obtained from API
     * @return Response (Order) object where result contains a singleton list
     */
    private Response<Order> createResponseForSingleOrder(String response) {
        try {
            JSONObject responseJson = new JSONObject(response);
            Response<Order> orderResponse = new Response<>(responseJson);

            if (orderResponse.getCode() == 200) {
                orderResponse.setResult(Collections.singletonList(new Order(responseJson.getJSONObject("result"))));
            }
            return orderResponse;
        } catch (JSONException e) {
            Log.e(TAG, "Error occurred while creating JSON from returned response.", e);
        }

        return null;
    }

    /**
     * @param response The response obtained from API
     * @return Response (Order) object where result can contain more than one Order objects
     */
    private Response<Order> createResponseForMultipleOrders(String response) {
        try {
            JSONObject jsonResponse = new JSONObject(response);
            Response<Order> orderResponse = new Response<>(jsonResponse);

            if (orderResponse.getCode() == 200) {
                List<Order> orders = new ArrayList<>();
                JSONArray ordersArray = jsonResponse.getJSONArray("result");
                for (int i = 0; i < ordersArray.length(); i++) {
                    orders.add(new Order(ordersArray.getJSONObject(i)));
                }
                orderResponse.setResult(orders);
            }

            return orderResponse;
        } catch (JSONException e) {
            Log.e(TAG, "Error occurred while creating JSON from returned response.", e);
        }

        return null;
    }

}
