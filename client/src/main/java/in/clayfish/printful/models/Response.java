package in.clayfish.printful.models;

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
