package in.clayfish.printful.models.includable;

import org.json.JSONObject;

/**
 * @author shuklaalok7
 * @since 25/12/2016
 */
public class OptionType {

    /**
     * Option identifier - use this to specify the option when creating an order
     */
    private String id;

    /**
     * Display name
     */
    private String title;

    /**
     * Data type of this option (currently only 'bool' is supported)
     */
    private String type;

    /**
     * Additional price when this option is used
     */
    private String additionalPrice;

    public OptionType() {
    }

    public OptionType(JSONObject json) {
        // TODO implement
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAdditionalPrice() {
        return additionalPrice;
    }

    public void setAdditionalPrice(String additionalPrice) {
        this.additionalPrice = additionalPrice;
    }

    @Override
    public String toString() {
        // TODO implement
        return super.toString();
    }
}
