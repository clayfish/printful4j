package in.clayfish.printful.models;

/**
 * @author shuklaalok7
 * @since 29/12/2016
 */
public class SyncProduct {
    /**
     * Sync Product ID
     */
    private int id;

    /**
     * Product ID from the E-commerce platform
     */
    private String externalId;

    /**
     * Product name
     */
    private String name;

    /**
     * Total number of Sync Variants belonging to this product
     */
    private int variants;

    /**
     * Number of synced Sync Variants belonging to this product
     */
    private int synced;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVariants() {
        return variants;
    }

    public void setVariants(int variants) {
        this.variants = variants;
    }

    public int getSynced() {
        return synced;
    }

    public void setSynced(int synced) {
        this.synced = synced;
    }
}
