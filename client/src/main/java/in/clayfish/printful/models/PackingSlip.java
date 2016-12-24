package in.clayfish.printful.models;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

/**
 * @author shuklaalok7
 * @since 24/12/2016
 */
public class PackingSlip {

    /**
     * Customer service email
     */
    private String email;

    /**
     * Customer service phone (111-222-3333)
     */
    private String phone;

    /**
     * Custom packing slip message
     */
    private String message;

    public PackingSlip() {
    }

    /**
     * @param json
     */
    public PackingSlip(JSONObject json) throws JSONException {
        if (json.has("email")) {
            this.email = json.getString("email");
        }

        if (json.has("phone")) {
            this.phone = json.getString("phone");
        }

        if (json.has("message")) {
            this.message = json.getString("message");
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        JSONStringer stringer = new JSONStringer();

        try {
            stringer.object();

            if (email != null) {
                stringer.key("email");
                stringer.value(email);
            }

            if (phone != null) {
                stringer.key("phone");
                stringer.value(phone);
            }

            if (message != null) {
                stringer.key("message");
                stringer.value(message);
            }

            stringer.endObject();
            return stringer.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return "";
    }
}
