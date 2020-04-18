package `in`.clayfish.printful.models.includable

/**
 * @author shuklaalok7 (alok@clay.fish)
 * @since 2016-12-24
 */
data class Cost(
        /**
         * Total cost of all items
         */
        var subtotal: String? = null,

        /**
         * Discount sum
         */
        var discount: String? = null,

        /**
         * Shipping costs
         */
        var shipping: String? = null,

        /**
         * Sum of taxes (not included in the item price)
         */
        var tax: String? = null,

        /**
         * Grand Total (subtotal-discount+tax+shipping)
         */
        var total: String? = null
)
