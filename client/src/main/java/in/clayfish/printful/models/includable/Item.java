package in.clayfish.printful.models.includable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

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

    @Override
    public String toString() {
        JSONStringer jsonStringer = new JSONStringer();
        try {
            jsonStringer.object();

            if (getId() > 0) {
                jsonStringer.key("id");
                jsonStringer.value(getId());
            }

            if (externalId != null) {
                jsonStringer.key("external_id");
                jsonStringer.value(externalId);
            }

            if (variantId > 0) {
                jsonStringer.key("variant_id");
                jsonStringer.value(variantId);
            }

            if (quantity > 0) {
                jsonStringer.key("quantity");
                jsonStringer.value(quantity);
            }

            if (price != null) {
                jsonStringer.key("price");
                jsonStringer.value(price);
            }

            if (retailPrice != null) {
                jsonStringer.key("retail_price");
                jsonStringer.value(retailPrice);
            }

            if (name != null) {
                jsonStringer.key("name");
                jsonStringer.value(name);
            }

            if (sku != null) {
                jsonStringer.key("sku");
                jsonStringer.value(sku);
            }

            if (product != null) {
                jsonStringer.key("product");
                jsonStringer.value(new JSONObject(product.toString()));
            }

            if (files != null) {
                jsonStringer.key("files");
                JSONArray filesArray = new JSONArray();
                for (File file : files) {
                    filesArray.put(new JSONObject(file.toString()));
                }
                jsonStringer.value(filesArray);
            }

            if (options != null) {
                jsonStringer.key("options");
                JSONArray optionsArray = new JSONArray();
                for (ItemOption option : options) {
                    optionsArray.put(new JSONObject(option.toString()));
                }
                jsonStringer.value(optionsArray);
            }

            jsonStringer.endObject();
            return jsonStringer.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return "";
    }
}
