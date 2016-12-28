package in.clayfish.printful.models;

import java.util.List;

import in.clayfish.printful.enums.ProductType;
import in.clayfish.printful.models.includable.OptionType;


/**
 * @author shuklaalok7
 * @since 21/12/2016
 */
public class Product extends Entity {

    /**
     * Product type identifier
     */
    private ProductType type;
    private String brand;
    private String model;
    private String image;
    private int variantCount;
    //    private List<Variant> variants;
    private List<File> files;
    private List<OptionType> allowedOptions;
//    private Map<String, ImageSize> dimensions;

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


    public List<OptionType> getAllowedOptions() {
        return allowedOptions;
    }

    public void setAllowedOptions(List<OptionType> allowedOptions) {
        this.allowedOptions = allowedOptions;
    }


}
