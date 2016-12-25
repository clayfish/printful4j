package in.clayfish.printful.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import in.clayfish.printful.enums.ProductType;
import in.clayfish.printful.models.includable.ImageSize;
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
    private List<Variant> variants;
    private List<File> files;
    private List<OptionType> allowedOptions;
    private Map<String, ImageSize> dimensions;

    /**
     *
     */
    public Product() {
    }

    /**
     * @param json
     * @throws JSONException
     */
    public Product(JSONObject json) throws JSONException {
        super(json);

        if (json.has("type")) {
            this.type = ProductType.find(json.getString("type"));
        }

        if (json.has("brand")) {
            this.brand = json.getString("brand");
        }

        if (json.has("model")) {
            this.model = json.getString("model");
        }

        if (json.has("image")) {
            this.image = json.getString("image");
        }

        if (json.has("variant_code")) {
            this.variantCount = json.getInt("variant_code");
        }

        if (json.has("allowed_files")) {
            this.files = new ArrayList<>();
            JSONArray filesArray = json.getJSONArray("allowed_files");

            for (int i = 0; i < filesArray.length(); i++) {
                this.files.add(new File(filesArray.getJSONObject(i)));
            }
        }

        if (json.has("allowed_options")) {
            this.allowedOptions = new ArrayList<>();
            JSONArray jsonArray = json.getJSONArray("allowed_options");

            for (int i = 0; i < jsonArray.length(); i++) {
                this.allowedOptions.add(new OptionType(jsonArray.getJSONObject(i)));
            }
        }
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

    public Map<String, ImageSize> getDimensions() {
        return dimensions;
    }

    public void setDimensions(Map<String, ImageSize> dimensions) {
        this.dimensions = dimensions;
    }

    public List<OptionType> getAllowedOptions() {
        return allowedOptions;
    }

    public void setAllowedOptions(List<OptionType> allowedOptions) {
        this.allowedOptions = allowedOptions;
    }

    public List<Variant> getVariants() {
        return variants;
    }

    public void setVariants(List<Variant> variants) {
        this.variants = variants;
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

            if (type != null) {
                jsonStringer.key("type");
                jsonStringer.value(type.name().replace("_", "-"));
            }

            if (brand != null) {
                jsonStringer.key("brand");
                jsonStringer.value(brand);
            }

            if (model != null) {
                jsonStringer.key("model");
                jsonStringer.value(model);
            }

            if (image != null) {
                jsonStringer.key("image");
                jsonStringer.value(image);
            }

            if (variantCount > 0) {
                jsonStringer.key("variant_count");
                jsonStringer.value(variantCount);
            }

            if (files != null) {
                jsonStringer.key("allowed_files");
                JSONArray allowedOptionsArray = new JSONArray();
                for (File file : files) {
                    allowedOptionsArray.put(new JSONObject(file.toString()));
                }
                jsonStringer.value(allowedOptionsArray);
            }

            if (allowedOptions != null) {
                jsonStringer.key("allowed_options");
                JSONArray allowedOptionsArray = new JSONArray();
                for (OptionType optionType : allowedOptions) {
                    allowedOptionsArray.put(new JSONObject(optionType.toString()));
                }
                jsonStringer.value(allowedOptionsArray);
            }

            jsonStringer.endObject();
            return jsonStringer.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return "";
    }
}
