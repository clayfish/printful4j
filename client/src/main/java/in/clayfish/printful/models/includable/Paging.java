package in.clayfish.printful.models.includable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author shuklaalok7
 * @since 24/12/2016.
 */
public class Paging {
    /**
     * Total items available
     */
    private int total;

    /**
     * Items skipped from the beginning
     */
    private int offset;

    /**
     * Number of items per page
     */
    private int limit;

    public Paging() {
    }

    public Paging(JSONObject json) throws JSONException {
        if (json.has("total")) {
            this.total = json.getInt("total");
        }

        if (json.has("offset")) {
            this.offset = json.getInt("offset");
        }

        if (json.has("limit")) {
            this.limit = json.getInt("limit");
        }
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
