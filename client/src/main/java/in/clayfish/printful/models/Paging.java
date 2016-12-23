package in.clayfish.printful.models;

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
