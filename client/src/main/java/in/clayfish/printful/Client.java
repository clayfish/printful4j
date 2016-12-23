package in.clayfish.printful;

import in.clayfish.printful.enums.OrderStatus;
import in.clayfish.printful.models.Order;
import in.clayfish.printful.models.Product;
import in.clayfish.printful.models.ProductInfo;
import in.clayfish.printful.models.Response;
import in.clayfish.printful.models.VariantInfo;

/**
 * @author shuklaalok7
 * @since 21/12/2016
 */
public interface Client {

//    /**
//     * @param apiKey
//     * @return
//     */
//    static Client getClient(String apiKey) {
//        return new CompositeClient(apiKey);
//    }
//
//    /**
//     * @param apiKey
//     * @param configuration
//     * @return
//     */
//    static Client getClient(String apiKey, Configuration configuration) {
//        return new CompositeClient(apiKey, configuration);
//    }

    /**
     * @return All the products as returned from {@code GET /orders}
     */
    Response<Product> getAllProductList();

    /**
     * @param variantId
     * @return
     */
    Response<VariantInfo> getInformationAboutVariant(int variantId);

    /**
     * @param productId
     * @return
     */
    Response<ProductInfo> getProductsVariantsList(int productId);


    /**
     * @param status
     * @param offset
     * @param limit
     * @return
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

}
