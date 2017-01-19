package in.clayfish.printful.models;

import in.clayfish.printful.exceptions.ValidationFailedException;

/**
 * @author shuklaalok7
 * @since 18/01/2017
 */
public interface Builder<T> {

    /**
     * @return Built object of type T
     * @throws ValidationFailedException If {@link #check()} has been called and built object fails
     *                                   basic validation constraints
     */
    T build() throws ValidationFailedException;

    /**
     * Once call to this method is chained, {@link #build()} can throw
     * {@link ValidationFailedException} because it validates the objects after building it.
     *
     * @return Builder object
     */
    Builder<T> check();

}
