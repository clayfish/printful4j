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
import org.junit.Test;

import java.util.Collections;
import java.util.Locale;

import in.clayfish.printful.Client;
import in.clayfish.printful.clients.ShippingRateApiClient;
import in.clayfish.printful.models.Response;
import in.clayfish.printful.models.ShippingRequest;
import in.clayfish.printful.models.info.AddressInfo;
import in.clayfish.printful.models.info.ItemInfo;
import in.clayfish.printful.models.info.ShippingInfo;

/**
 * @author shuklaalok7
 * @since 29/12/2016
 */
public class ShippingRateApiClientTest {

    @Test
    public void testCalculateShippingRates() {
        Client client = new ShippingRateApiClient(ProductCatalogApiClientTest.API_KEY);
        ShippingRequest shippingRequest = new ShippingRequest();

        AddressInfo recipient = new AddressInfo();
        recipient.setAddress1("1600, Amphitheatre Parkway, Mountain View");
        recipient.setCity("California");
        recipient.setCountryCode("US");
        recipient.setStateCode("CA");
        recipient.setZip("94043");
        shippingRequest.setRecipient(recipient);

        ItemInfo itemInfo = new ItemInfo();
        itemInfo.setQuantity(1);
        itemInfo.setVariantId("8004");
        shippingRequest.setItems(Collections.singletonList(itemInfo));

        Response<ShippingInfo> response = client.calculateShippingRates(shippingRequest);

        Assert.assertEquals(200, response.getCode());
        Assert.assertNotNull(response.getResult());

        for (ShippingInfo shippingInfo : response.getResult()) {
            System.out.println(String.format(Locale.ENGLISH, "%s: %s %s - %s", shippingInfo.getId(),
                    shippingInfo.getCurrency(), shippingInfo.getRate(), shippingInfo.getName()));
        }
    }

}
