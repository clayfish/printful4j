package `in`.clayfish.printful.enums

/**
 * @author shuklaalok7
 * @since 21/12/2016
 */
enum class ProductType {
    POSTER, FRAMED_POSTER, CANVAS, T_SHIRT, MUG, EMBROIDERY, CUT_SEW, SUBLIMATION, PHONE_CASE;

    override fun toString() = name.replace("_", "-")

    companion object {
        /**
         * Convenience method to convert string to ProductType using loose matching
         *
         * @param searchTerm Term to search
         * @return Found ProductType object
         */
        fun find(searchTerm: String?): ProductType? {
            if (searchTerm.isNullOrBlank()) return null

            val searchTerm1 = searchTerm.replace("-", "").trim { it <= ' ' }.toUpperCase()

            return values().firstOrNull {
                it.name.replace("_", "").equals(searchTerm1, true) ||
                        it.name.replace("_", "").contains(searchTerm1) || searchTerm1.contains(it.name.replace("_", ""))
            }
        }
    }
}
