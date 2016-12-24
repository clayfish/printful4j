package in.clayfish.printful.models.includable;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

/**
 * @author shuklaalok7
 * @since 24/12/2016
 */
public class GiftData {

    /**
     * Gift message title
     */
    private String subject;

    /**
     * Gift message text
     */
    private String message;

    /**
     * @param json
     */
    public GiftData(JSONObject json) throws JSONException {
        if (json.has("subject")) {
            this.message = json.getString("subject");
        }

        if (json.has("message")) {
            this.message = json.getString("message");
        }
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
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

            if (subject != null) {
                stringer.key("subject");
                stringer.value(subject);
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
