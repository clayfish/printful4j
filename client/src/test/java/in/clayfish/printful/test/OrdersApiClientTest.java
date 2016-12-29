package in.clayfish.printful.test;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import in.clayfish.printful.Client;
import in.clayfish.printful.clients.OrdersApiClient;
import in.clayfish.printful.enums.OrderStatus;
import in.clayfish.printful.models.Order;
import in.clayfish.printful.models.Response;
import in.clayfish.printful.models.includable.Address;
import in.clayfish.printful.models.includable.Item;

/**
 * @author shuklaalok7
 * @since 29/12/2016
 */
public class OrdersApiClientTest {

    private Client client;

    @Before
    public void setup() {
        client = new OrdersApiClient(ProductCatalogApiClientTest.API_KEY);
    }

    @Test
    public void getListOfOrders() {
        List<Order> orders = client.getListOfOrders(OrderStatus.DRAFT, 0, 0).getResult();
        System.out.println(orders.size());
    }

    @Test
    public void createANewOrder() {
        Order order = new Order();
        List<Item> items = new ArrayList<>();
        Item item1 = new Item();
        Address recipient = new Address();
        recipient.setName("John Doe");
        recipient.setAddress1("21 Baker Street");
        recipient.setCity("New York");

        item1.setPrice("76.95");
        item1.setExternalId("231");
        item1.setSku("TEST_ITEM");
        items.add(item1);

        order.setExternalId("12331");
        order.setItems(items);
        Response<Order> orderResponse = client.createANewOrder(order, false, false);

        System.out.println(orderResponse);
    }

    @Test
    public void testGetOrderData1() {
// TODO implement
    }

    @Test
    public void testGetOrderData2() {
// TODO implement
    }

    @Test
    public void testCancelAnOrder1() {
// TODO implement
    }

    @Test
    public void testCancelAnOrder2() {
// TODO implement
    }

    @Test
    public void testUpdateOrderData1() {
// TODO implement
    }

    @Test
    public void testUpdateOrderData2() {
// TODO implement
    }

    @Test
    public void testConfirmDraftForFulfillment1() {
// TODO implement
    }

    @Test
    public void testConfirmDraftForFulfillment2() {
// TODO implement
    }

}
