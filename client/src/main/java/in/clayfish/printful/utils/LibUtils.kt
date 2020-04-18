package `in`.clayfish.printful.utils

import `in`.clayfish.printful.models.*
import `in`.clayfish.printful.models.includable.ImageSize
import `in`.clayfish.printful.models.includable.Item
import `in`.clayfish.printful.models.includable.ProductVariant
import `in`.clayfish.printful.models.includable.Shipment
import `in`.clayfish.printful.models.info.*
import com.google.gson.*
import org.apache.commons.codec.binary.Base64
import org.jsoup.Connection
import org.jsoup.Jsoup
import java.math.BigDecimal
import java.math.BigInteger
import java.util.*

/**
 * @author shuklaalok7
 * @since 24/12/2016
 */
object LibUtils {
    @JvmField
    var gson = GsonBuilder() //            .enableComplexMapKeySerialization()
            .registerTypeAdapter(Date::class.java, GsonAdaptors.DateSerializer())
            .registerTypeAdapter(Response::class.java, GsonAdaptors.ResponseDeserializer())
            .registerTypeAdapter(ImageSize::class.java, GsonAdaptors.ImageSizeSerializer())
            .registerTypeAdapterFactory(GsonAdaptors.FileAdapterFactory())
            .registerTypeAdapterFactory(GsonAdaptors.OrderAdapterFactory())
            .registerTypeAdapterFactory(GsonAdaptors.AddressAdapterFactory())
            .registerTypeAdapterFactory(GsonAdaptors.ProductAdapterFactory())
            .registerTypeAdapterFactory(GsonAdaptors.PackingSlipAdapterFactory())
            .registerTypeAdapterFactory(CustomTypeAdapterFactory(Item::class))
            .registerTypeAdapterFactory(CustomTypeAdapterFactory(TaxInfo::class))
            .registerTypeAdapterFactory(CustomTypeAdapterFactory(Variant::class))
            .registerTypeAdapterFactory(CustomTypeAdapterFactory(ItemInfo::class))
            .registerTypeAdapterFactory(CustomTypeAdapterFactory(Shipment::class))
            .registerTypeAdapterFactory(CustomTypeAdapterFactory(StoreData::class))
            .registerTypeAdapterFactory(CustomTypeAdapterFactory(SyncVariant::class))
            .registerTypeAdapterFactory(CustomTypeAdapterFactory(SyncProduct::class))
            .registerTypeAdapterFactory(CustomTypeAdapterFactory(AddressInfo::class))
            .registerTypeAdapterFactory(CustomTypeAdapterFactory(ProductVariant::class))
            .registerTypeAdapterFactory(CustomTypeAdapterFactory(TaxAddressInfo::class))
            .registerTypeAdapterFactory(CustomTypeAdapterFactory(SyncProductInfo::class))
            .registerTypeAdapterFactory(CustomTypeAdapterFactory(SyncVariantInfo::class))
            .create()

    /**
     * @param base64Key The Base64 encoded API key
     * @param uri       URI to connect
     * @param config    Configuration object
     * @return A Connection object
     */
    @JvmStatic
    fun createConnection(base64Key: String, uri: String,
                         config: Configuration): Connection = Jsoup.connect(config.baseUrl + uri).timeout(config.timeout)
            .header("Accept", "application/json")
            .header("Accept-Encoding", "gzip, deflate, sdch, br")
            .header("Accept-Language", "en-GB,en;q=0.8,en-US;q=0.6,hi;q=0.4")
            .header("Connection", "keep-alive")
            .header("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.95 Safari/537.36")
            .header("Content-type", "application/json")
            .header("Authorization", "Basic $base64Key")
            .ignoreHttpErrors(true).ignoreContentType(true)

    /**
     * @param externalId The external_id
     * @return external_id with leading @
     */
    @JvmStatic
    fun curateExternalId(externalId: String): String {
        var externalId1 = externalId.trim { it <= ' ' }
        if (!externalId1.startsWith("@")) externalId1 = "@$externalId"
        return externalId1
    }

    /**
     * @param s The string to check
     * @return The index of first UPPERCASE character or -1
     */
    fun checkUpperCase(s: String): Int {
        for (i in s.indices)
            if (Character.isUpperCase(s[i])) return i

        return -1
    }

    /**
     * Converts `productId` to `product_id`
     *
     * @param s input key, ex colorCode
     * @return output camelCased key, ex color_code
     * @see .convertToCamelCase
     */
    @JvmStatic
    fun convertToPhpStyle(s: String): String {
        val u = checkUpperCase(s)
        if (u == -1) return s

        var out = s.substring(0, u) + "_" + s.substring(u, u + 1).toLowerCase()
        if (u < s.length - 1) out += s.substring(u + 1, s.length)
        return convertToPhpStyle(out)
    }

    /**
     * Converts `product_id` to `productId`
     *
     * @param s input key, ex color_code
     * @return output camelCased key, ex colorCode
     * @see .convertToPhpStyle
     */
    @JvmStatic
    fun convertToCamelCase(s: String): String {
        if (s.contains("_")) {
            val parts = s.split("_")
            return parts[0] + parts.drop(1).joinToString("") { it.substring(0, 1).toUpperCase() + it.substring(1, it.length) }
        }
        return s
    }

    /**
     * @param jsonObject JsonObject from which the field is to get
     * @param key        The key to find in the given jsonObject
     * @param tClass     class of the found value
     * @param <T>        class parameter
     * @return Found object type-casted to tClass
     */
    fun <T> getFromJson(jsonObject: JsonObject, key: String?, tClass: Class<out T>): T? {
        return if (jsonObject.has(key)) {
            val obj = jsonObject[key]
            if (!obj.isJsonNull)
                when {
                    tClass.isAssignableFrom(String::class.java) -> tClass.cast(obj.asString)
                    tClass.isAssignableFrom(Byte::class.java) -> tClass.cast(obj.asByte)
                    tClass.isAssignableFrom(Short::class.java) -> tClass.cast(obj.asShort)
                    tClass.isAssignableFrom(Int::class.java) -> tClass.cast(obj.asInt)
                    tClass.isAssignableFrom(Long::class.java) -> tClass.cast(obj.asLong)
                    tClass.isAssignableFrom(Float::class.java) -> tClass.cast(obj.asFloat)
                    tClass.isAssignableFrom(Double::class.java) -> tClass.cast(obj.asDouble)
                    tClass.isAssignableFrom(BigInteger::class.java) -> tClass.cast(obj.asBigInteger)
                    tClass.isAssignableFrom(BigDecimal::class.java) -> tClass.cast(obj.asBigDecimal)
                    tClass.isAssignableFrom(Number::class.java) -> tClass.cast(obj.asNumber)
                    tClass.isAssignableFrom(Boolean::class.java) -> tClass.cast(obj.asBoolean)
                    tClass.isAssignableFrom(JsonPrimitive::class.java) -> tClass.cast(obj.asJsonPrimitive)
                    tClass.isAssignableFrom(JsonObject::class.java) -> tClass.cast(obj.asJsonObject)
                    tClass.isAssignableFrom(JsonArray::class.java) -> tClass.cast(obj.asJsonArray)
                    tClass.isAssignableFrom(JsonElement::class.java) -> tClass.cast(obj)
                    else -> null
                } else null
        } else null
    }

    /**
     * @param str The string to encode
     * @return Base64 encoded string
     */
    @JvmStatic
    fun encodeToBase64(str: String) = String(Base64.encodeBase64(str.toByteArray()))
}