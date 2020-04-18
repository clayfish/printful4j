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

        Assert.assertTrue(response.code == 200);
        Assert.assertTrue(response.result != null);

        for (ShippingInfo shippingInfo : response.result) {
            System.out.println(String.format(Locale.ENGLISH, "%s: %s %s - %s", shippingInfo.getId(),
                    shippingInfo.getCurrency(), shippingInfo.getRate(), shippingInfo.getName()));
        }
    }

}
