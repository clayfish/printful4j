package in.clayfish.printful.models;

/**
 * @author shuklaalok7
 * @since 18/01/2017
 */
public interface Builder<T> {

    /**
     * @return
     */
    T build();

    /**
     * @return Builder object
     */
    Builder<T> check();

}
