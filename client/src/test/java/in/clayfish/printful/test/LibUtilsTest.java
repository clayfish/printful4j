/*
 * MIT License
 *
 * Copyright (c) 2016-2019 ClayFish Technologies LLP
 * Copyright (c) 2020 ClayFish Technologies
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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
