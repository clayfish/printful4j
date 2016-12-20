package in.clayfish.printful;

import android.util.Base64;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import in.clayfish.printful.models.Configuration;
import in.clayfish.printful.models.Order;
import in.clayfish.printful.models.Product;
import in.clayfish.printful.models.Response;

/**
 * @author shuklaalok7
 * @since 21/12/2016
 */
public class SimpleClient implements Client {

    private final String base64Key;
    private Configuration configuration;

    SimpleClient(String apiKey) {
        this(apiKey, new Configuration());
    }

    SimpleClient(String apiKey, Configuration configuration) {
        this.base64Key = Base64.encodeToString(apiKey.getBytes(), Base64.DEFAULT);
        this.configuration = configuration;
    }

    @Override
    public List<Product> getAllProducts() {
        final String url = configuration.getBaseUrl() + "products";

        try {
            String response = Jsoup.connect(url).ignoreContentType(true).header("Authentication", "Basic " + base64Key).execute().body();
            JSONObject json = new JSONObject(response);
            if (json.getInt("code") == 200) {
                JSONArray productsJson = json.getJSONArray("result");
                List<Product> result = new ArrayList<>();

                for (int i = 0; i < productsJson.length(); i++) {
                    result.add(new Product(productsJson.getJSONObject(i)));
                }

                return result;
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Response placeOrder(Order order) {
        return null;
    }
}
