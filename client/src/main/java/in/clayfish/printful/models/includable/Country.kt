package `in`.clayfish.printful.models.includable

import java.io.Serializable

/**
 * @author shuklaalok7
 * @since 24/12/2016
 */
data class Country(
        /**
         * Country code
         */
        var code: String? = null,

        /**
         * Country name
         */
        var name: String? = null,

        /**
         * List of states
         */
        var states: List<State>? = null
) : Serializable {

    constructor() : this(null, null, null)
    constructor(code: String?, name: String?) : this(code, name, listOf())

}
