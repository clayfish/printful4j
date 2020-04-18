package `in`.clayfish.printful.models

import `in`.clayfish.printful.exceptions.ValidationFailedException

/**
 * @author shuklaalok7 (alok@clay.fish)
 * @since 2017-01-18
 */
interface Builder<T> {
    /**
     * @return Built object of type T
     * @throws ValidationFailedException If [.check] has been called and built object fails basic
     * validation constraints
     */
    @Throws(ValidationFailedException::class)
    fun build(): T

    /**
     * Once call to this method is chained, [.build] can throw
     * [ValidationFailedException] because it validates the objects after building it.
     *
     * @return Builder object
     */
    fun check(): Builder<T>?
}
