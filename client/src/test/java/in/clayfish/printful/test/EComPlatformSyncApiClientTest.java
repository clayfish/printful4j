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
