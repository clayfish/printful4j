package in.clayfish.printful.test;

import org.junit.Before;
import org.junit.Test;

import in.clayfish.printful.Client;
import in.clayfish.printful.clients.FileLibraryApiClient;

/**
 * @author shuklaalok7
 * @since 29/12/2016
 */
public class FileLibraryApiClientTest {
    private Client client;

    @Before
    public void setup() {
        client = new FileLibraryApiClient(ProductCatalogApiClientTest.API_KEY);
    }

    @Test
    public void testGetFileInfo() {
        // TODO implement
    }

    @Test
    public void testAddANewFile() {
        // TODO implement
    }

    @Test
    public void testGetListOfFiles() {
        // TODO implement
    }

}
