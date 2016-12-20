package in.clayfish.printful;

import java.util.List;

import in.clayfish.printful.models.Configuration;
import in.clayfish.printful.models.Order;
import in.clayfish.printful.models.Product;
import in.clayfish.printful.models.Response;

/**
 * @author shuklaalok7
 * @since 21/12/2016
 */
public interface Client {

    /**
     * @param apiKey
     * @return
     */
    static Client getClient(String apiKey) {
        return new SimpleClient(apiKey);
    }

    /**
     * @param apiKey
     * @param configuration
     * @return
     */
    static Client getClient(String apiKey, Configuration configuration) {
        return new SimpleClient(apiKey, configuration);
    }

    /**
     * @return
     */
    List<Product> getAllProducts();

    /**
     * @param order
     * @return
     */
    Response placeOrder(Order order);

}
