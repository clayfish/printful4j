package in.clayfish.printful.test;

import com.google.gson.JsonObject;

import org.junit.Assert;
import org.junit.Test;

import in.clayfish.printful.utils.LibUtils;

/**
 * @author shuklaalok7
 * @since 29/12/2016
 */
public class LibUtilsTest {

    @Test
    public void testGetFromJson() throws Exception {
        JsonObject json = new JsonObject();
        json.addProperty("integer", 34);
        json.addProperty("string", "John Doe");
        json.addProperty("bool", true);

        JsonObject embeddedObject = new JsonObject();
        embeddedObject.addProperty("integer", 34);
        embeddedObject.addProperty("string", "John Doe");
        embeddedObject.addProperty("bool", true);

        json.add("object", embeddedObject);

        Assert.assertEquals("John Doe", LibUtils.getFromJson(json, "string", String.class));
        Assert.assertEquals(34, (int) LibUtils.getFromJson(json, "integer", Integer.class));
        Assert.assertTrue(LibUtils.getFromJson(json, "bool", Boolean.class));
        Assert.assertEquals(embeddedObject, LibUtils.getFromJson(json, "object", JsonObject.class));
    }

    @Test
    public void testConvertToPhpStyle() {
        String out = LibUtils.convertToPhpStyle("colorCode");
        Assert.assertEquals("color_code", out);

        out = LibUtils.convertToPhpStyle("colorC");
        Assert.assertEquals("color_c", out);
    }

    @Test
    public void testConvertToCamelCase() {
        String out = LibUtils.convertToCamelCase("color_1ode");
        Assert.assertEquals("color1ode", out);

        out = LibUtils.convertToCamelCase("additional_price");
        Assert.assertEquals("additionalPrice", out);
    }

}
