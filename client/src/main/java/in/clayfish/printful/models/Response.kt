package `in`.clayfish.printful.models

import `in`.clayfish.printful.models.includable.Paging
import java.io.Serializable

/**
 * @author shuklaalok7
 * @since 21/12/2016
 */
data class Response<T>(
        /**
         * Response status code
         */
        var code: Int = 0,

        /**
         *
         */
        var errorMessage: String? = null,

        /**
         * List of data
         */
        var result: List<T>? = null,

        /**
         * Paging information
         */
        var paging: Paging? = null
) : Serializable
