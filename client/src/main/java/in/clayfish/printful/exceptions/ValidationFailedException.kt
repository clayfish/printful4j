package `in`.clayfish.printful.exceptions

/**
 * It is thrown from builder objects where the validation of the model has failed.
 *
 * @author shuklaalok7 (alok@clay.fish)
 * @since v0.3.0 2017-01-02
 *
 * @see in.clayfish.printful.models.Order.Builder Order Builder
 * @see in.clayfish.printful.models.includable.Item.Builder Item Builder
 * @see in.clayfish.printful.models.includable.Address.Builder Address Builder
 */
class ValidationFailedException : RuntimeException()
