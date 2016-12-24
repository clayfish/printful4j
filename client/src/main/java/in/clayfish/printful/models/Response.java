package in.clayfish.printful.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

import in.clayfish.printful.models.includable.Paging;

/**
 * @author shuklaalok7
 * @since 21/12/2016
 */
public class Response<T> implements Serializable {
    /**
     * Response status code
     */
    private int code;

    /**
     *
     */
    private String errorMessage;

    /**
     * List of data
     */
    private List<T> result;

    /**
     * Paging information
     */
    private Paging paging;

    public Response() {
    }

    /**
     * It strips off and sets code, paging and errorMessage
     *
     * @param json
     */
    public Response(JSONObject json) throws JSONException {
        if (json.has("code")) {
            this.code = json.getInt("code");
        }

        if (json.has("paging")) {
            this.paging = new Paging(json.getJSONObject("paging"));
        }

        if (json.has("result")) {
            if (json.get("result") instanceof String) {
                this.errorMessage = json.getString("result");
            }
        }
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
