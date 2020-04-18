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
        Assert.assertTrue(response.code == 200);
        Assert.assertNotNull(response.result);

        for (Country country : response.result) {
            System.out.println("--------------------------------------------------");
            System.out.println(format(Locale.ENGLISH, "\t\t\t%s (%s)", country.name,
                    country.code));
            System.out.println("--------------------------------------------------");

            for (State state : country.states) {
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
        Assert.assertTrue(response.code == 200);
        Assert.assertNotNull(response.result);

        Assert.assertTrue(response.result.size() == 1);

        TaxInfo taxInfo = response.result.get(0);

        Assert.assertNotNull(taxInfo);

        System.out.println(format(Locale.ENGLISH, "Tax: %.2f%% (required: %b)\tShipping taxed: %b",
                taxInfo.getRate() * 100, taxInfo.getRequired(), taxInfo.getShippingTaxable()));
    }

}
