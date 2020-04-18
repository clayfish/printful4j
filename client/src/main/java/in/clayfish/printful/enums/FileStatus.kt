package `in`.clayfish.printful.enums

/**
 * @author shuklaalok7
 * @since 24/12/2016
 */
enum class FileStatus {
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

    override fun toString() = name.toLowerCase()

    companion object {
        /**
         * @param searchTerm The term to search status for
         * @return Found FileStatus
         */
        fun find(searchTerm: String?) = if (searchTerm == null || searchTerm.isEmpty()) null
        else valueOf(searchTerm.trim { it <= ' ' }.toUpperCase())
    }
}
