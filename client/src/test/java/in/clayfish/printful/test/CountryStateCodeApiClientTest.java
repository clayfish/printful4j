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

        Assert.assertTrue(countriesResponse.getCode() == 200);
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
