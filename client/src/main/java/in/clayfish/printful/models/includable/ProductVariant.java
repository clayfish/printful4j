package in.clayfish.printful.models.includable;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

/**
 * @author shuklaalok7
 * @since 24/12/2016
 */
public class ProductVariant {

    private int variantId;
    private int productId;

    /**
     * URL of a sample image for this variant
     */
    private String image;

    /**
     * Display name of this variant
     */
    private String name;

    public ProductVariant() {
    }

    public ProductVariant(JSONObject json) throws JSONException {
        if (json.has("variant_id")) {
            this.variantId = json.getInt("variant_id");
        }

        if (json.has("product_id")) {
            this.productId = json.getInt("product_id");
        }

        if (json.has("name")) {
            this.name = json.getString("name");
        }

        if (json.has("image")) {
            this.image = json.getString("image");
        }
    }

    public int getVariantId() {
        return variantId;
    }

    public void setVariantId(int variantId) {
        this.variantId = variantId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        JSONStringer jsonStringer = new JSONStringer();

        try {
            jsonStringer.object();

            if (variantId > 0) {
                jsonStringer.key("variant_id");
                jsonStringer.value(variantId);
            }

            if (productId > 0) {
                jsonStringer.key("product_id");
                jsonStringer.value(productId);
            }

            if (name != null) {
                jsonStringer.key("name");
                jsonStringer.value(name);
            }

            if (image != null) {
                jsonStringer.key("image");
                jsonStringer.value(image);
            }

            jsonStringer.endObject();
            return jsonStringer.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return "";
    }
}
