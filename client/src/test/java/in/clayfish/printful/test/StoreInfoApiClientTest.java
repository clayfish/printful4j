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

import java.util.Locale;

import in.clayfish.printful.Client;
import in.clayfish.printful.clients.StoreInfoApiClient;
import in.clayfish.printful.models.PackingSlip;
import in.clayfish.printful.models.Response;
import in.clayfish.printful.models.StoreData;

import static java.lang.String.format;

/**
 * @author shuklaalok7
 * @since 29/12/2016
 */
public class StoreInfoApiClientTest {

    private Client client;

    @Before
    public void setup() {
        this.client = new StoreInfoApiClient(ProductCatalogApiClientTest.API_KEY);
    }

    @Test
    public void testGetStoreInfo() {
        Response<StoreData> response = client.getStoreInfo();

        Assert.assertEquals(200, response.getCode());
        Assert.assertNotNull(response.getResult());
        Assert.assertEquals(1, response.getResult().size());

        StoreData storeData = response.getResult().get(0);
        Assert.assertNotNull(storeData);

        System.out.println(format(Locale.ENGLISH, "%s (%s)\nFirst used on: %s", storeData.getName(),
                storeData.getWebsite(), storeData.getCreated().toString()));
    }

    @Test
    public void testChangeStorePackingSlip() {
        PackingSlip packingSlip = new PackingSlip();

        packingSlip.setEmail("info@clay.fish");
        packingSlip.setPhone("");
        packingSlip.setMessage("We, at ClayFish Technologies, are always here to help you. Please contact us for any query or problem.");

        Response<PackingSlip> packingSlipResponse = client.changeStorePackingSlip(packingSlip);

        Assert.assertEquals(200, packingSlipResponse.getCode());
        Assert.assertNotNull(packingSlipResponse.getResult());
        Assert.assertEquals(1, packingSlipResponse.getResult().size());

        packingSlip = packingSlipResponse.getResult().get(0);
        Assert.assertNotNull(packingSlip);

        Assert.assertEquals("info@clay.fish", packingSlip.getEmail());
        Assert.assertNull(packingSlip.getPhone());
        Assert.assertEquals("We, at ClayFish Technologies, are always here to help you. Please contact us for any query or problem.", packingSlip.getMessage());

        System.out.println(format(Locale.ENGLISH, "%s\t%s\n%s", packingSlip.getEmail(),
                packingSlip.getPhone(), packingSlip.getMessage()));
    }

}
