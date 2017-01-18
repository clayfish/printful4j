package in.clayfish.printful.models.includable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import in.clayfish.printful.models.Entity;
import in.clayfish.printful.models.File;

/**
 * @author shuklaalok7
 * @since 24/12/2016
 */
public class Item extends Entity {

    /**
     * Line item ID from the external system
     */
    private String externalId;

    /**
     * Variant ID of the item ordered. See Products API
     */
    private int variantId;

    /**
     * Number of items ordered
     */
    private int quantity;

    /**
     * Printful price of the item
     */
    private String price;

    /**
     * Original retail price of the item to be displayed on the packing slip
     */
    private String retailPrice;

    /**
     * Display name of the item. If not given, a name from the Printful system will be displayed on the packing slip
     */
    private String name;

    /**
     * Short information about the Product and Variant
     */
    private ProductVariant product;

    /**
     * Attached printfiles / preview images
     */
    private List<File> files;

    /**
     * Additional options for this product
     */
    private List<ItemOption> options;

    /**
     * Product identifier (SKU) from the external system
     */
    private String sku;

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public int getVariantId() {
        return variantId;
    }

    public void setVariantId(int variantId) {
        this.variantId = variantId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(String retailPrice) {
        this.retailPrice = retailPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    /**
     * @author shuklaalok7
     * @since 2/01/2017
     */
    public static class Builder implements in.clayfish.printful.models.Builder<Item> {
        private Item item;
        private boolean checkValidity;

        public Builder() {
            this.item = new Item();
            this.item.quantity = 1;
        }

        @Override
        public Builder check() {
            this.checkValidity = true;
            return this;
        }

//        public Builder id(long id) {
//            this.item.setId(id);
//            return this;
//        }

        public Builder externalId(String externalId) {
            this.item.externalId = externalId;
            return this;
        }

        public Builder variantId(int variantId) {
            this.item.variantId = variantId;
            return this;
        }

        public Builder retailPrice(String retailPrice) {
            this.item.retailPrice = retailPrice;
            return this;
        }

        public Builder quantity(int quantity) {
            this.item.quantity = quantity;
            return this;
        }

        public Builder name(String name) {
            this.item.name = name;
            return this;
        }

        public Builder sku(String sku) {
            this.item.sku = sku;
            return this;
        }

        public Builder file(File file) {
            if (this.item.files == null) {
                this.item.files = new ArrayList<>();
            }
            this.item.files.add(file);
            return this;
        }

        public Builder files(Collection<File> files) {
            if (this.item.files == null) {
                this.item.files = new ArrayList<>();
            }
            this.item.files.addAll(files);
            return this;
        }

        @Override
        public Item build() {
            if (checkValidity) {
                checkValidity();
            }
            return item;
        }

        /**
         *
         */
        private void checkValidity() {
            // TODO Implement
        }
    }

}
