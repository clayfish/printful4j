package `in`.clayfish.printful.enums

/**
 * @author shuklaalok7
 * @since 1/01/2017
 */
enum class SyncStatus {
    SYNCED, UNCSYNCED, ALL;

    override fun toString() = name.toLowerCase()
}
