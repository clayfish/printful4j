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

package in.clayfish.printful.models.info;

import in.clayfish.printful.models.SyncProduct;
import in.clayfish.printful.models.SyncVariant;

/**
 * @author shuklaalok7
 * @since 1/01/2017
 */
public class SyncVariantInfo {
    /**
     * Information about the selected Sync Variant
     */
    private SyncVariant syncVariant;

    /**
     * Information about the Sync Product that the Sync Variant belongs to
     */
    private SyncProduct syncProduct;

    public SyncVariant getSyncVariant() {
        return syncVariant;
    }

    public void setSyncVariant(SyncVariant syncVariant) {
        this.syncVariant = syncVariant;
    }

    public SyncProduct getSyncProduct() {
        return syncProduct;
    }

    public void setSyncProduct(SyncProduct syncProduct) {
        this.syncProduct = syncProduct;
    }
}
