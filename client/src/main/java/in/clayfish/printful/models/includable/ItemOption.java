package in.clayfish.printful.models.includable;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

/**
 * @author shuklaalok7
 * @since 24/12/2016
 */
public class ItemOption {
    /**
     * Option ID
     */
    private String id;
    /**
     * Option value
     */
    private Object value;

    public ItemOption(JSONObject json) throws JSONException {
        if (json.has("id")) {
            this.id = json.getString("id");
        }

        if (json.has("value")) {
            this.value = json.get("value");
        }
    }

    public ItemOption() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        JSONStringer stringer = new JSONStringer();
        try {
            stringer.object();

            if (id != null) {
                stringer.key("id");
                stringer.value(id);
            }

            if (value != null) {
                stringer.key("value");
                stringer.value(value);
            }

            stringer.endObject();
            return stringer.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return stringer.toString();
    }
}
