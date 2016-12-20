package in.clayfish.printful.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * The objects which are sent to Printful server should extend this class
 *
 * @author shuklaalok7
 * @since 21/12/2016
 */
public abstract class Entity implements Serializable {

    private long id;

    public Entity() {
    }

    public Entity(JSONObject json) throws JSONException {
        if (json != null) {
            this.id = json.getLong("id");
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
