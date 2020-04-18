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

import java.util.Locale;

import in.clayfish.printful.Client;
import in.clayfish.printful.clients.CountryStateCodeApiClient;
import in.clayfish.printful.models.Response;
import in.clayfish.printful.models.includable.Country;
import in.clayfish.printful.models.includable.State;

import static java.lang.String.format;

/**
 * @author shuklaalok7
 * @since 29/12/2016
 */
public class CountryStateCodeApiClientTest {

    @Test
    public void testRetrieveCountryList() {
        Client client = new CountryStateCodeApiClient(ProductCatalogApiClientTest.API_KEY);
        Response<Country> countriesResponse = client.retrieveCountryList();

        Assert.assertEquals(200, countriesResponse.getCode());
        Assert.assertTrue(countriesResponse.getResult() != null && !countriesResponse.getResult().isEmpty());

        for (Country country : countriesResponse.getResult()) {
            System.out.println("-----------------------------");
            System.out.println(format(Locale.ENGLISH, "       %s (%s)", country.getName(), country.getCode()));
            System.out.println("-----------------------------");

            if (country.getStates() != null) {
                for (State state : country.getStates()) {
                    System.out.println(format(Locale.ENGLISH, "%s (%s)", state.getName(), state.getCode()));
                }
            }
        }
    }

}
