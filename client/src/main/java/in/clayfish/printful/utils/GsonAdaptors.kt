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

package `in`.clayfish.printful.utils

import `in`.clayfish.printful.enums.FileStatus
import `in`.clayfish.printful.enums.OrderStatus
import `in`.clayfish.printful.enums.ProductType
import `in`.clayfish.printful.models.*
import `in`.clayfish.printful.models.includable.Address
import `in`.clayfish.printful.models.includable.ImageSize
import `in`.clayfish.printful.models.includable.Paging
import com.google.gson.*
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.util.*

/**
 * @author shuklaalok7
 * @since 2016-12-29
 */
internal object GsonAdaptors {
    /**
     * @author shuklaalok7
     * @since 29/12/2016
     */
    internal class DateSerializer : JsonDeserializer<Date?>, JsonSerializer<Date> {
        @Throws(JsonParseException::class)
        override fun deserialize(json: JsonElement?, typeOfT: Type, context: JsonDeserializationContext): Date? =
                if (json == null || json.isJsonNull) null else Date(json.asLong * 1000)

        override fun serialize(src: Date, typeOfSrc: Type, context: JsonSerializationContext): JsonElement = JsonPrimitive(src.time)
    }

    /**
     * @author shuklaalok7
     * @since 29/12/2016
     */
    internal class ImageSizeSerializer : JsonDeserializer<ImageSize>, JsonSerializer<ImageSize> {
        @Throws(JsonParseException::class)
        override fun deserialize(jsonElement: JsonElement, typeOfT: Type, context: JsonDeserializationContext): ImageSize {
            val imageSizeObject = jsonElement.asString
            return ImageSize(imageSizeObject.split("x").toTypedArray()[0].trim { it <= ' ' }.toInt(), imageSizeObject.split("x").toTypedArray()[1].trim { it <= ' ' }.toInt())
        }

        override fun serialize(imageSize: ImageSize, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
            val imageSizeString = String.format("%d x %d", imageSize.width, imageSize.height)
            return JsonPrimitive(imageSizeString)
        }
    }

    /**
     * It tweaks the fields of the model
     *
     * @author shuklaalok7
     * @since 29/12/2016
     */
    internal class PackingSlipAdapterFactory : CustomTypeAdapterFactory<PackingSlip>(PackingSlip::class) {
        override fun beforeToObject(json: JsonElement?) {
            super.beforeToObject(json)
            if (json != null && !json.isJsonNull) {
                val jsonObject = json.asJsonObject
                if (jsonObject.has("packingSlip")) {
                    val packingSlip = LibUtils.getFromJson(jsonObject, "packingSlip", JsonObject::class.java)
                    if (packingSlip != null)
                        jsonObject.apply {
                            remove("packingSlip")
                            addProperty("email", LibUtils.getFromJson(packingSlip, "email", String::class.java))
                            addProperty("phone", LibUtils.getFromJson(packingSlip, "phone", String::class.java))
                            addProperty("message", LibUtils.getFromJson(packingSlip, "message", String::class.java))
                        }
                }
            }
        }
    }

    /**
     * It tweaks the fields of the model
     *
     * @author shuklaalok7
     * @since 29/12/2016
     */
    internal class OrderAdapterFactory : CustomTypeAdapterFactory<Order>(Order::class) {
        override fun beforeToJson(source: Order?, json: JsonElement?) {
            super.beforeToJson(source, json)
            if (source?.status != null)
                json!!.asJsonObject.addProperty("status", source.status.toString())
        }

        override fun beforeToObject(json: JsonElement?) {
            super.beforeToObject(json)
            val jsonObject = json!!.asJsonObject
            val orderStatus = OrderStatus.find(LibUtils.getFromJson(jsonObject, "status", String::class.java))
            if (orderStatus != null)
                jsonObject.addProperty("status", orderStatus.name)
        }
    }

    /**
     * It tweaks the fields of the model
     *
     * @author shuklaalok7
     * @since 29/12/2016
     */
    internal class AddressAdapterFactory : CustomTypeAdapterFactory<Address>(Address::class) {
        override fun beforeToObject(json: JsonElement?) {
            super.beforeToObject(json)
            if (json == null || json.isJsonNull) {
                return
            }
            val jsonObject = json.asJsonObject
            val stateObject = JsonObject()
            val countryObject = JsonObject()
            stateObject.addProperty("code", LibUtils.getFromJson(jsonObject, "stateCode", String::class.java))
            stateObject.addProperty("name", LibUtils.getFromJson(jsonObject, "stateName", String::class.java))
            countryObject.addProperty("code", LibUtils.getFromJson(jsonObject, "countryCode", String::class.java))
            countryObject.addProperty("name", LibUtils.getFromJson(jsonObject, "countryName", String::class.java))
            jsonObject.add("state", stateObject)
            jsonObject.add("country", countryObject)
        }

        override fun beforeToJson(source: Address?, json: JsonElement?) {
            super.beforeToJson(source, json)
            if (json == null || source == null)
                return

            val jsonObject = json.asJsonObject
            if (source.state != null)
                jsonObject.apply {
                    remove("state")
                    addProperty("state_code", source.state.code)
                    addProperty("state_name", source.state.name)
                }

            if (source.country != null)
                jsonObject.apply {
                    remove("country")
                    addProperty("country_code", source.country.code)
                    addProperty("country_name", source.country.name)
                }
        }
    }

    /**
     * It tweaks the fields of the model
     *
     * @author shuklaalok7 (alok@clay.fish)
     * @since 2016-12-29
     */
    internal class ProductAdapterFactory : CustomTypeAdapterFactory<Product>(Product::class) {
        override fun beforeToJson(source: Product?, json: JsonElement?) {
            super.beforeToJson(source, json)
            if (source?.type != null)
                json!!.asJsonObject.addProperty("type", source.type.toString())
        }

        override fun beforeToObject(json: JsonElement?) {
            super.beforeToObject(json)
            val jsonObject = json!!.asJsonObject
            val productType = ProductType.find(LibUtils.getFromJson(jsonObject, "type", String::class.java))
            if (productType != null) jsonObject.addProperty("status", productType.name)
        }
    }

    /**
     * It tweaks the fields of the model
     *
     * @author shuklaalok7
     * @since 29/12/2016
     */
    internal class FileAdapterFactory : CustomTypeAdapterFactory<File>(File::class) {
        override fun beforeToJson(source: File?, json: JsonElement?) {
            super.beforeToJson(source, json)
            val jsonObject = json!!.asJsonObject
            jsonObject.remove("string_id")
            jsonObject.addProperty("id", source!!.stringId)
            if (source.status != null) jsonObject.addProperty("status", source.status.toString())
        }

        override fun beforeToObject(json: JsonElement?) {
            super.beforeToObject(json)
            val jsonObject = json!!.asJsonObject
            jsonObject.addProperty("stringId", LibUtils.getFromJson(jsonObject, "id", String::class.java))
            jsonObject.remove("id")
            val fileStatus = FileStatus.find(LibUtils.getFromJson(jsonObject, "status", String::class.java))
            if (fileStatus != null) jsonObject.addProperty("status", fileStatus.name)
        }
    }

    /**
     * @author shuklaalok7
     * @since 29/12/2016
     */
    internal class ResponseDeserializer : JsonDeserializer<Response<*>> {
        @Throws(JsonParseException::class)
        override fun deserialize(jsonElement: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Response<*> {
            val response = Response<Any>()
            val json = jsonElement.asJsonObject
            if (json.has("code"))
                response.code = json["code"].asInt

            if (json.has("paging"))
                response.paging = context.deserialize(json["paging"], Paging::class.java)

            if (json.has("result")) {
                val resultElement = json["result"]
                if (response.code != 200) {
                    if (resultElement.isJsonPrimitive)
                        response.errorMessage = resultElement.asString
                } else if (resultElement.isJsonArray) {
                    if (response.code == 200) {
                        // result is a list of objects
                        val result: MutableList<Any> = ArrayList()
                        val resultArray = resultElement.asJsonArray
                        for (element in resultArray) {
                            val types = (typeOfT as ParameterizedType).actualTypeArguments
                            result.add(context.deserialize(element, types[0]))
                        }
                        response.result = result
                    }
                } else if (resultElement.isJsonObject) {
                    val types = (typeOfT as ParameterizedType).actualTypeArguments
                    val result = listOf(context.deserialize<Any>(resultElement, types[0]))
                    response.result = result
                }
            }
            return response
        }
    }
}
