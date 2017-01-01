package in.clayfish.printful.test;

import org.junit.Before;
import org.junit.Test;

import in.clayfish.printful.Client;
import in.clayfish.printful.clients.EComPlatformSyncApiClient;

/**
 * @author shuklaalok7
 * @since 1/01/2017
 */
public class EComPlatformSyncApiClientTest {

    private Client client;

    @Before
    public void setup() {
        this.client = new EComPlatformSyncApiClient(ProductCatalogApiClientTest.API_KEY);
    }

    @Test
    public void testUnlinkSyncedVariant1() {
        // TODO implement
    }

    @Test
    public void testUnlinkSyncedVariant2() {
        // TODO implement
    }

    @Test
    public void testUpdateLinkedProductAndPrintFileInfo1() {
        // TODO implement
    }

    @Test
    public void testUpdateLinkedProductAndPrintFileInfo2() {
        // TODO implement
    }

    @Test
    public void testGetInfoAboutVariant1() {
        // TODO implement
    }

    @Test
    public void testGetInfoAboutVariant2() {
        // TODO implement
    }

    @Test
    public void testUnlinkAllSyncedVariantsOfProduct1() {
        // TODO implement
    }

    @Test
    public void testUnlinkAllSyncedVariantsOfProduct2() {
        // TODO implement
    }

    @Test
    public void testGetInfoAboutSyncProductAndVariants1() {
        // TODO implement
    }

    @Test
    public void testGetInfoAboutSyncProductAndVariants2() {
        // TODO implement
    }

    @Test
    public void testGetListOfSyncProducts() {
        // TODO implement
    }

}
