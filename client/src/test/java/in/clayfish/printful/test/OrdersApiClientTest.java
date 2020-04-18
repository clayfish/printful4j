package in.clayfish.printful.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import in.clayfish.printful.Client;
import in.clayfish.printful.clients.OrdersApiClient;
import in.clayfish.printful.enums.OrderStatus;
import in.clayfish.printful.models.File;
import in.clayfish.printful.models.Order;
import in.clayfish.printful.models.Response;
import in.clayfish.printful.models.includable.Address;
import in.clayfish.printful.models.includable.Country;
import in.clayfish.printful.models.includable.Item;
import in.clayfish.printful.models.includable.State;

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
    public void testGetListOfOrders() {
        List<Order> orders = client.getListOfOrders(OrderStatus.DRAFT, 0, 0).result;
        System.out.println(orders.size());
    }

    @Test
    public void testCreateANewOrder() {
        Address recipient = new Address.Builder().name("Googleplex")
                .address1("1600 Amphitheatre Parkway").phone("+1 650-253-0000")
                .city("Mountain View").zip("94043").state(new State("CA", "California"))
                .country(new Country("US", "United States"))//.check()
                .build();

        File file1 = new File();
        file1.setUrl("http://68.media.tumblr.com/tumblr_m99eygsfkn1qaoylwo1_1280.jpg");

        Item item1 = new Item.Builder().variantId(4).retailPrice("76.95").externalId("231")
                .sku("TEST_ITEM").file(file1).check().build();

        String orderString = "{\"items\":[{\"files\":[{\"dpi\":0,\"height\":0,\"size\":0,\"type\":\"back\",\"url\":\"https://parsefiles.back4app.com/Hql4XPmDwP33CMFe5SQpQlJKDmJQvBDl2mqBTAd8/e3fa6e7fa13c3d7210a725cd7e5dd130_d5d2828a-74b1-4626-a463-ff512d32871e.png\",\"visible\":false,\"width\":0,\"additional_price\":0.0},{\"dpi\":0,\"height\":0,\"size\":0,\"visible\":false,\"width\":0,\"additional_price\":0.0}],\"quantity\":1,\"id\":0,\"variant_id\":4565}],\"recipient\":{\"address1\":\"637 Akdj\",\"city\":\"Montreal\",\"name\":\"Alok\",\"zip\":\"52626\",\"state_code\":\"\",\"state_name\":\"\",\"country_name\":\"CA\"},\"id\":0,\"external_id\":\"KLhUHUxYmU\",\"retail_costs\":{\"total\":\"35.0\"}}";

        Order order = new Order.Builder().externalId("12332").recipient(recipient).item(item1)
                .check().build();

        Response<Order> orderResponse = client.createANewOrder(order, false, false);

        Assert.assertTrue(orderResponse.code == 200);

        System.out.println(orderResponse.code + ": " + orderResponse.result);
    }

    @Test
    public void testGetOrderData1() {
        Response<Order> orderResponse = client.getOrderData(1899732);
        Assert.assertTrue(orderResponse.code == 200);
    }

    @Test
    public void testGetOrderData2() {
        Response<Order> orderResponse = client.getOrderData("12332");
        Assert.assertTrue(orderResponse.code == 200);
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
