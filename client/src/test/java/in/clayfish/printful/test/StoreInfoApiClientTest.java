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
        Response<StoreData> response = client.storeInfo;

        Assert.assertTrue(response.code == 200);
        Assert.assertNotNull(response.result);
        Assert.assertTrue(response.result.size() == 1);

        StoreData storeData = response.result.get(0);
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

        Assert.assertTrue(packingSlipResponse.code == 200);
        Assert.assertNotNull(packingSlipResponse.result);
        Assert.assertTrue(packingSlipResponse.result.size() == 1);

        packingSlip = packingSlipResponse.result.get(0);
        Assert.assertNotNull(packingSlip);

        Assert.assertEquals("info@clay.fish", packingSlip.getEmail());
        Assert.assertNull(packingSlip.getPhone());
        Assert.assertEquals("We, at ClayFish Technologies, are always here to help you. Please contact us for any query or problem.", packingSlip.getMessage());

        System.out.println(format(Locale.ENGLISH, "%s\t%s\n%s", packingSlip.getEmail(),
                packingSlip.getPhone(), packingSlip.getMessage()));
    }

}
