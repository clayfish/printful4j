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
import in.clayfish.printful.clients.TaxRateApiClient;
import in.clayfish.printful.models.Response;
import in.clayfish.printful.models.includable.Country;
import in.clayfish.printful.models.includable.State;
import in.clayfish.printful.models.info.TaxAddressInfo;
import in.clayfish.printful.models.info.TaxInfo;

import static java.lang.String.format;

/**
 * @author shuklaalok7
 * @since 29/12/2016
 */
public class TaxRateApiClientTest {

    private Client client;

    @Before
    public void setup() {
        client = new TaxRateApiClient(ProductCatalogApiClientTest.API_KEY);
    }

    @Test
    public void testRetrieveStateListThatRequiresTaxCalculation() {
        Response<Country> response = client.retrieveStateListThatRequiresTaxCalculation();

        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getCode());
        Assert.assertNotNull(response.getResult());

        for (Country country : response.getResult()) {
            System.out.println("--------------------------------------------------");
            System.out.println(format(Locale.ENGLISH, "\t\t\t%s (%s)", country.getName(),
                    country.getCode()));
            System.out.println("--------------------------------------------------");

            for (State state : country.getStates()) {
                System.out.println(format(Locale.ENGLISH, "%s (%s)", state.getName(), state.getCode()));
            }
        }
    }

    @Test
    public void testCalculateTaxRate() {
        TaxAddressInfo taxAddressInfo = new TaxAddressInfo();
        taxAddressInfo.setCity("California");
        taxAddressInfo.setStateCode("CA");
        taxAddressInfo.setCountryCode("US");
        taxAddressInfo.setZip("94043");

        Response<TaxInfo> response = client.calculateTaxRate(taxAddressInfo);

        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getCode());
        Assert.assertNotNull(response.getResult());

        Assert.assertEquals(1, response.getResult().size());

        TaxInfo taxInfo = response.getResult().get(0);

        Assert.assertNotNull(taxInfo);

        System.out.println(format(Locale.ENGLISH, "Tax: %.2f%% (required: %b)\tShipping taxed: %b",
                taxInfo.getRate() * 100, taxInfo.getRequired(), taxInfo.getShippingTaxable()));
    }

}
