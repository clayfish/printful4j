package in.clayfish.printful;

import android.util.Base64;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Connection;
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

    private final static String TAG = SimpleClient.class.getSimpleName();

    private final String base64Key;
    private Configuration configuration;

    /**
     * @param apiKey
     */
    public SimpleClient(String apiKey) {
        this(apiKey, new Configuration());
    }

    /**
     * @param apiKey
     * @param configuration
     */
    public SimpleClient(String apiKey, Configuration configuration) {
        this.base64Key = Base64.encodeToString(apiKey.getBytes(), Base64.NO_WRAP);
        this.configuration = configuration;
    }

    @Override
    public List<Product> getAllProducts() {
        final String url = configuration.getBaseUrl() + "products";

        try {
            String response = createConnection(url).execute().body();
            JSONObject json = new JSONObject(response);
            if (json.getInt("code") == 200) {
                JSONArray productsJson = json.getJSONArray("result");
                List<Product> result = new ArrayList<>();

                for (int i = 0; i < productsJson.length(); i++) {
                    result.add(new Product(productsJson.getJSONObject(i)));
                }

                return result;
            } else {
                Log.e(TAG, "Could not connect to Printful. Server response: " + json.getInt("code"));
            }
        } catch (IOException | JSONException e) {
            Log.e(TAG, "Problem in connecting to Printful API.", e);
        }

        return null;
    }

    @Override
    public Response placeOrder(Order order) {
        return null;
    }

    /**
     * @param url
     * @return
     */
    private Connection createConnection(final String url) {
        return Jsoup.connect(url).timeout(configuration.getTimeout())
                .header("Accept", "application/json")
                .header("Accept-Encoding", "gzip, deflate, sdch, br")
                .header("Accept-Language", "en-GB,en;q=0.8,en-US;q=0.6,hi;q=0.4")
                .header("Connection", "keep-alive")
                .header("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.95 Safari/537.36")
                .header("Content-type", "application/json")
                .ignoreContentType(true).header("Authorization", "Basic " + base64Key);
    }
}
