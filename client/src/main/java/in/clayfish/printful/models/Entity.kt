package `in`.clayfish.printful.models

import java.io.Serializable

/**
 * The objects which are sent to Printful server should extend this class
 *
 * @author shuklaalok7
 * @since 21/12/2016
 */
abstract class Entity : Serializable {
    var id: Long = 0

}
