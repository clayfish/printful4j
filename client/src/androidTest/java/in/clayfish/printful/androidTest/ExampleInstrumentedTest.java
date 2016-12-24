package in.clayfish.printful.androidTest;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Locale;

import in.clayfish.printful.Client;
import in.clayfish.printful.CompositeClient;
import in.clayfish.printful.models.Product;
import in.clayfish.printful.models.Response;

import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * FIXME test does not go through in real device because of INTERNET permission.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    private static final String TAG = ExampleInstrumentedTest.class.getSimpleName();
    private Client client;

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("in.clayfish.printful.test", appContext.getPackageName());
    }

    @Before
    public void setup() {
        client = new CompositeClient("YOUR API KEY");
    }

    @Test
    public void testGetAllProducts() throws Exception {
        Response<Product> response = client.getAllProductList();
        Log.d(TAG, "Found products on printful-");

        for (Product product : response.getResult()) {
            Log.d(TAG, String.format(Locale.ENGLISH, "%s : %s", product.getBrand(),
                    product.getModel()));
        }

        Assert.assertTrue(response.getCode() == 200);
        Assert.assertTrue(response.getResult().size() > 0);
    }

}
