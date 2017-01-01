package in.clayfish.printful.enums;

/**
 * @author shuklaalok7
 * @since 1/01/2017
 */
public enum SyncStatus {
    SYNCED, UNCSYNCED, ALL;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
