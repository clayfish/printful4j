package in.clayfish.printful.models;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

/**
 * @author shuklaalok7
 * @since 21/12/2016
 */
public class Variant extends Entity {

    private long productId;
    private String name;
    private String size;
    private String color;
    private String colorCode;
    private String image;
    private double price;
    private boolean inStock;

    public Variant() {
    }

    public Variant(JSONObject json) throws JSONException {
        super(json);

        // TODO implement
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
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

            // TODO implement

            jsonStringer.endObject();
            return jsonStringer.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return "";
    }
}
