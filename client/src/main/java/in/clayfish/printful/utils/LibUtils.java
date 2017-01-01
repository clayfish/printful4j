package in.clayfish.printful.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import in.clayfish.printful.models.Configuration;
import in.clayfish.printful.models.Response;
import in.clayfish.printful.models.StoreData;
import in.clayfish.printful.models.SyncProduct;
import in.clayfish.printful.models.SyncVariant;
import in.clayfish.printful.models.Variant;
import in.clayfish.printful.models.includable.ImageSize;
import in.clayfish.printful.models.includable.Item;
import in.clayfish.printful.models.includable.ProductVariant;
import in.clayfish.printful.models.includable.Shipment;
import in.clayfish.printful.models.info.AddressInfo;
import in.clayfish.printful.models.info.ItemInfo;
import in.clayfish.printful.models.info.SyncProductInfo;
import in.clayfish.printful.models.info.SyncVariantInfo;
import in.clayfish.printful.models.info.TaxAddressInfo;
import in.clayfish.printful.models.info.TaxInfo;

/**
 * @author shuklaalok7
 * @since 24/12/2016
 */
public abstract class LibUtils {
    public static Gson gson = new GsonBuilder()
//            .enableComplexMapKeySerialization()

            .registerTypeAdapter(Date.class, new GsonAdapters.DateSerializer())
            .registerTypeAdapter(Response.class, new GsonAdapters.ResponseDeserializer())
            .registerTypeAdapter(ImageSize.class, new GsonAdapters.ImageSizeSerializer())

            .registerTypeAdapterFactory(new GsonAdapters.FileAdapterFactory())
            .registerTypeAdapterFactory(new GsonAdapters.OrderAdapterFactory())
            .registerTypeAdapterFactory(new GsonAdapters.AddressAdapterFactory())
            .registerTypeAdapterFactory(new GsonAdapters.ProductAdapterFactory())
            .registerTypeAdapterFactory(new GsonAdapters.PackingSlipAdapterFactory())

            .registerTypeAdapterFactory(new CustomTypeAdapterFactory<>(Item.class))
            .registerTypeAdapterFactory(new CustomTypeAdapterFactory<>(TaxInfo.class))
            .registerTypeAdapterFactory(new CustomTypeAdapterFactory<>(Variant.class))
            .registerTypeAdapterFactory(new CustomTypeAdapterFactory<>(ItemInfo.class))
            .registerTypeAdapterFactory(new CustomTypeAdapterFactory<>(Shipment.class))
            .registerTypeAdapterFactory(new CustomTypeAdapterFactory<>(StoreData.class))
            .registerTypeAdapterFactory(new CustomTypeAdapterFactory<>(SyncVariant.class))
            .registerTypeAdapterFactory(new CustomTypeAdapterFactory<>(SyncProduct.class))
            .registerTypeAdapterFactory(new CustomTypeAdapterFactory<>(AddressInfo.class))
            .registerTypeAdapterFactory(new CustomTypeAdapterFactory<>(ProductVariant.class))
            .registerTypeAdapterFactory(new CustomTypeAdapterFactory<>(TaxAddressInfo.class))
            .registerTypeAdapterFactory(new CustomTypeAdapterFactory<>(SyncProductInfo.class))
            .registerTypeAdapterFactory(new CustomTypeAdapterFactory<>(SyncVariantInfo.class))
            .create();

    /**
     * @param base64Key The Base64 encoded API key
     * @param uri       URI to connect
     * @param config    Configuration object
     * @return A Connection object
     */
    public static Connection createConnection(final String base64Key, final String uri,
                                              final Configuration config) {
        return Jsoup.connect(config.getBaseUrl() + uri).timeout(config.getTimeout())
                .header("Accept", "application/json")
                .header("Accept-Encoding", "gzip, deflate, sdch, br")
                .header("Accept-Language", "en-GB,en;q=0.8,en-US;q=0.6,hi;q=0.4")
                .header("Connection", "keep-alive")
                .header("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.95 Safari/537.36")
                .header("Content-type", "application/json")
                .header("Authorization", "Basic " + base64Key)
                .ignoreHttpErrors(true).ignoreContentType(true);
    }

    /**
     * @param externalId The external_id
     * @return external_id with leading @
     */
    public static String curateExternalId(String externalId) {

        externalId = externalId.trim();
        if (!externalId.startsWith("@")) {
            externalId = "@" + externalId;
        }
        return externalId;
    }

    /**
     * @param s The string to check
     * @return The index of first UPPERCASE character or -1
     */
    public static int checkUpperCase(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (Character.isUpperCase(s.charAt(i))) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Converts {@code productId} to {@code product_id}
     *
     * @param s input key, ex colorCode
     * @return output camelCased key, ex color_code
     * @see #convertToCamelCase(String)
     */
    public static String convertToPhpStyle(final String s) {
        int u = checkUpperCase(s);
        if (u == -1) {
            return s;
        }

        String out = s.substring(0, u) + "_" + s.substring(u, u + 1).toLowerCase();

        if (u < s.length() - 1) {
            out += s.substring(u + 1, s.length());
        }

        return convertToPhpStyle(out);
    }

    /**
     * Converts {@code product_id} to {@code productId}
     *
     * @param s input key, ex color_code
     * @return output camelCased key, ex colorCode
     * @see #convertToPhpStyle(String)
     */
    public static String convertToCamelCase(String s) {
        if (s.contains("_")) {
            String[] parts = s.split("_");
            for (int i = 1; i < parts.length; i++) {
                parts[i] = parts[i].substring(0, 1).toUpperCase() + parts[i].substring(1, parts[i].length());
            }
            String out = "";
            for (String part : parts) {
                out += part;
            }
            return out;
        }

        return s;
    }

    /**
     * @param jsonObject JsonObject from which the field is to get
     * @param key        The key to find in the given jsonObject
     * @param tClass     class of the found value
     * @param <T>        class parameter
     * @return Found object type-casted to tClass
     */
    public static <T> T getFromJson(JsonObject jsonObject, String key, Class<? extends T> tClass) {
        if (jsonObject.has(key)) {
            JsonElement obj = jsonObject.get(key);

            if (!obj.isJsonNull()) {
                if (tClass.isAssignableFrom(String.class)) {
                    return tClass.cast(obj.getAsString());
                }
                if (tClass.isAssignableFrom(Byte.class)) {
                    return tClass.cast(obj.getAsByte());
                }
                if (tClass.isAssignableFrom(Short.class)) {
                    return tClass.cast(obj.getAsShort());
                }

                if (tClass.isAssignableFrom(Integer.class)) {
                    return tClass.cast(obj.getAsInt());
                }

                if (tClass.isAssignableFrom(Long.class)) {
                    return tClass.cast(obj.getAsLong());
                }
                if (tClass.isAssignableFrom(Float.class)) {
                    return tClass.cast(obj.getAsFloat());
                }
                if (tClass.isAssignableFrom(Double.class)) {
                    return tClass.cast(obj.getAsDouble());
                }
                if (tClass.isAssignableFrom(BigInteger.class)) {
                    return tClass.cast(obj.getAsBigInteger());
                }
                if (tClass.isAssignableFrom(BigDecimal.class)) {
                    return tClass.cast(obj.getAsBigDecimal());
                }
                if (tClass.isAssignableFrom(Number.class)) {
                    return tClass.cast(obj.getAsNumber());
                }

                if (tClass.isAssignableFrom(Boolean.class)) {
                    return tClass.cast(obj.getAsBoolean());
                }

                if (tClass.isAssignableFrom(JsonPrimitive.class)) {
                    return tClass.cast(obj.getAsJsonPrimitive());
                }

                if (tClass.isAssignableFrom(JsonObject.class)) {
                    return tClass.cast(obj.getAsJsonObject());
                }

                if (tClass.isAssignableFrom(JsonArray.class)) {
                    return tClass.cast(obj.getAsJsonArray());
                }

                if (tClass.isAssignableFrom(JsonElement.class)) {
                    return tClass.cast(obj);
                }
            }
        }
        return null;
    }
}
