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

        Assert.assertTrue(countriesResponse.code == 200);
        Assert.assertTrue(countriesResponse.result != null && !countriesResponse.result.isEmpty());

        for (Country country : countriesResponse.result) {
            System.out.println("-----------------------------");
            System.out.println(format(Locale.ENGLISH, "       %s (%s)", country.name, country.code));
            System.out.println("-----------------------------");

            if (country.states != null) {
                for (State state : country.states) {
                    System.out.println(format(Locale.ENGLISH, "%s (%s)", state.getName(), state.getCode()));
                }
            }
        }
    }

}
