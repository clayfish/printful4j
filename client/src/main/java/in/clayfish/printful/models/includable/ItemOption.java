package in.clayfish.printful.models.includable;

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
}
