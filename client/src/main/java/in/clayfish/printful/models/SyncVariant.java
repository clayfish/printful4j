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

package in.clayfish.printful.models;

import java.util.List;

import in.clayfish.printful.models.includable.ItemOption;
import in.clayfish.printful.models.includable.ProductVariant;

/**
 * @author shuklaalok7
 * @since 29/12/2016
 */
public class SyncVariant extends Entity {

    /**
     * Variant ID from the E-commerce platform
     */
    private String externalId;

    /**
     * Sync Product ID that this variant belongs to
     */
    private int syncProductId;

    /**
     * Sync Variant name
     */
    private String name;

    /**
     * Indicates if this Sync Variant is properly linked with Printful product
     */
    private boolean synced;

    /**
     * Printful Variant ID that this Sync Variant is synced to
     */
    private int variantId;

    /**
     * Short information about the Printful Product and Variant that this Sync Variant is synced to
     */
    private ProductVariant product;

    /**
     * Attached printfiles / preview images
     */
    private List<File> files;
    /**
     * Additional options for the configured product/variant
     */
    private List<ItemOption> options;

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public int getSyncProductId() {
        return syncProductId;
    }

    public void setSyncProductId(int syncProductId) {
        this.syncProductId = syncProductId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSynced() {
        return synced;
    }

    public void setSynced(boolean synced) {
        this.synced = synced;
    }

    public int getVariantId() {
        return variantId;
    }

    public void setVariantId(int variantId) {
        this.variantId = variantId;
    }

    public ProductVariant getProduct() {
        return product;
    }

    public void setProduct(ProductVariant product) {
        this.product = product;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public List<ItemOption> getOptions() {
        return options;
    }

    public void setOptions(List<ItemOption> options) {
        this.options = options;
    }
}
