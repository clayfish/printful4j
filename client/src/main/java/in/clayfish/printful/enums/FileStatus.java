package in.clayfish.printful.enums;

/**
 * @author shuklaalok7
 * @since 24/12/2016
 */
public enum FileStatus {
    /**
     * After you add a file, it is saved with the status waiting and downloaded and processed later
     */
    WAITING,

    /**
     * the status is changed to ok if the file was loaded successfully and was a valid image file
     */
    OK,

    /**
     * if the process did not succeed
     */
    FAIL;


    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
