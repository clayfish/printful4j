package in.clayfish.printful.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import in.clayfish.printful.enums.ProductType;
import in.clayfish.printful.models.includable.ImageSize;


/**
 * @author shuklaalok7
 * @since 21/12/2016
 */
public class Product extends Entity {

    private ProductType type;
    private String brand;
    private String model;
    private String image;
    private int variantCount;
    private List<Variant> variants;
    private List<File> files;
    private List<?> options;
    private Map<String, ImageSize> dimensions;

    public Product() {
    }

    public Product(JSONObject json) throws JSONException {
        super(json);


    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getVariantCount() {
        return variantCount;
    }

    public void setVariantCount(int variantCount) {
        this.variantCount = variantCount;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public List<?> getOptions() {
        return options;
    }

    public void setOptions(List<?> options) {
        this.options = options;
    }

    public Map<String, ImageSize> getDimensions() {
        return dimensions;
    }

    public void setDimensions(Map<String, ImageSize> dimensions) {
        this.dimensions = dimensions;
    }
}
