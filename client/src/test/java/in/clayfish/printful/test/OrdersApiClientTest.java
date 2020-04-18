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
        List<Order> orders = client.getListOfOrders(OrderStatus.DRAFT, 0, 0).getResult();
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

        Assert.assertEquals(200, orderResponse.getCode());

        System.out.println(orderResponse.getCode() + ": " + orderResponse.getResult());
    }

    @Test
    public void testGetOrderData1() {
        Response<Order> orderResponse = client.getOrderData(1899732);
        Assert.assertEquals(200, orderResponse.getCode());
    }

    @Test
    public void testGetOrderData2() {
        Response<Order> orderResponse = client.getOrderData("12332");
        Assert.assertEquals(200, orderResponse.getCode());
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
