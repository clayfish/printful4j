package in.clayfish.printful;

import java.util.List;

import in.clayfish.printful.models.Order;
import in.clayfish.printful.models.Product;
import in.clayfish.printful.models.Response;

/**
 * @author shuklaalok7
 * @since 21/12/2016
 */
public interface Client {

//    /**
//     * @param apiKey
//     * @return
//     */
//    static Client getClient(String apiKey) {
//        return new SimpleClient(apiKey);
//    }
//
//    /**
//     * @param apiKey
//     * @param configuration
//     * @return
//     */
//    static Client getClient(String apiKey, Configuration configuration) {
//        return new SimpleClient(apiKey, configuration);
//    }

    /**
     * @return All the products as returned from {@code GET /orders}
     */
    List<Product> getAllProducts();

    /**
     * @param order    The order object
     * @return Response from {@code POST /orders}
     */
    Response placeOrder(Order order);

}
