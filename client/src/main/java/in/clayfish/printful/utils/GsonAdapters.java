package in.clayfish.printful.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import in.clayfish.printful.enums.FileStatus;
import in.clayfish.printful.enums.OrderStatus;
import in.clayfish.printful.enums.ProductType;
import in.clayfish.printful.models.File;
import in.clayfish.printful.models.Order;
import in.clayfish.printful.models.PackingSlip;
import in.clayfish.printful.models.Product;
import in.clayfish.printful.models.Response;
import in.clayfish.printful.models.includable.Address;
import in.clayfish.printful.models.includable.ImageSize;
import in.clayfish.printful.models.includable.Paging;

/**
 * @author shuklaalok7
 * @since 29/12/2016
 */
abstract class GsonAdapters {

    /**
     * @author shuklaalok7
     * @since 29/12/2016
     */
    static class DateSerializer implements JsonDeserializer<Date>, JsonSerializer<Date> {

        @Override
        public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            if (json == null || json.isJsonNull()) {
                return null;
            }
            return new Date(json.getAsLong() * 1000);
        }

        @Override
        public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src.getTime());
        }
    }

    /**
     * @author shuklaalok7
     * @since 29/12/2016
     */
    static class ImageSizeSerializer implements JsonDeserializer<ImageSize>, JsonSerializer<ImageSize> {

        @Override
        public ImageSize deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            String imageSizeObject = jsonElement.getAsString();

            return new ImageSize(Integer.parseInt(imageSizeObject.split("x")[0].trim()),
                    Integer.parseInt(imageSizeObject.split("x")[1].trim()));
        }

        @Override
        public JsonElement serialize(ImageSize imageSize, Type typeOfSrc, JsonSerializationContext context) {
            String imageSizeString = String.format("%d x %d", imageSize.getWidth(), imageSize.getHeight());
            return new JsonPrimitive(imageSizeString);
        }
    }

    /**
     * It tweaks the fields of the model
     *
     * @author shuklaalok7
     * @since 29/12/2016
     */
    static class PackingSlipAdapterFactory extends CustomTypeAdapterFactory<PackingSlip> {
        PackingSlipAdapterFactory() {
            super(PackingSlip.class);
        }

        @Override
        protected void beforeToObject(JsonElement json) {
            super.beforeToObject(json);

            if (json != null && !json.isJsonNull()) {
                JsonObject jsonObject = json.getAsJsonObject();
                if (jsonObject.has("packingSlip")) {
                    JsonObject packingSlip = LibUtils.getFromJson(jsonObject, "packingSlip", JsonObject.class);
                    jsonObject.remove("packingSlip");

                    jsonObject.addProperty("email", LibUtils.getFromJson(packingSlip, "email", String.class));
                    jsonObject.addProperty("phone", LibUtils.getFromJson(packingSlip, "phone", String.class));
                    jsonObject.addProperty("message", LibUtils.getFromJson(packingSlip, "message", String.class));
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
    static class OrderAdapterFactory extends CustomTypeAdapterFactory<Order> {
        OrderAdapterFactory() {
            super(Order.class);
        }

        @Override
        protected void beforeToJson(Order source, JsonElement json) {
            super.beforeToJson(source, json);

            if (source.getStatus() != null) {
                json.getAsJsonObject().addProperty("status", source.getStatus().toString());
            }
        }

        @Override
        protected void beforeToObject(JsonElement json) {
            super.beforeToObject(json);

            JsonObject jsonObject = json.getAsJsonObject();
            OrderStatus orderStatus = OrderStatus.find(LibUtils.getFromJson(jsonObject, "status", String.class));
            if (orderStatus != null) {
                jsonObject.addProperty("status", orderStatus.name());
            }
        }
    }

    /**
     * It tweaks the fields of the model
     *
     * @author shuklaalok7
     * @since 29/12/2016
     */
    static class AddressAdapterFactory extends CustomTypeAdapterFactory<Address> {
        AddressAdapterFactory() {
            super(Address.class);
        }

        @Override
        protected void beforeToObject(JsonElement json) {
            super.beforeToObject(json);

            if (json == null || json.isJsonNull()) {
                return;
            }

            JsonObject jsonObject = json.getAsJsonObject();
            JsonObject stateObject = new JsonObject();
            JsonObject countryObject = new JsonObject();

            stateObject.addProperty("code", LibUtils.getFromJson(jsonObject, "stateCode", String.class));
            stateObject.addProperty("name", LibUtils.getFromJson(jsonObject, "stateName", String.class));

            countryObject.addProperty("code", LibUtils.getFromJson(jsonObject, "countryCode", String.class));
            countryObject.addProperty("name", LibUtils.getFromJson(jsonObject, "countryName", String.class));

            jsonObject.add("state", stateObject);
            jsonObject.add("country", countryObject);
        }

        @Override
        protected void beforeToJson(Address source, JsonElement json) {
            super.beforeToJson(source, json);

            if (json == null || source == null) {
                return;
            }

            JsonObject jsonObject = json.getAsJsonObject();

            if (source.getState() != null) {
                jsonObject.remove("state");
                jsonObject.addProperty("state_code", source.getState().getCode());
                jsonObject.addProperty("state_name", source.getState().getName());
            }
            if (source.getCountry() != null) {
                jsonObject.remove("country");
                jsonObject.addProperty("country_code", source.getCountry().getCode());
                jsonObject.addProperty("country_name", source.getCountry().getName());
            }
        }
    }

    /**
     * It tweaks the fields of the model
     *
     * @author shuklaalok7
     * @since 29/12/2016
     */
    static class ProductAdapterFactory extends CustomTypeAdapterFactory<Product> {
        ProductAdapterFactory() {
            super(Product.class);
        }

        @Override
        protected void beforeToJson(Product source, JsonElement json) {
            super.beforeToJson(source, json);

            if (source.getType() != null) {
                json.getAsJsonObject().addProperty("type", source.getType().toString());
            }
        }

        @Override
        protected void beforeToObject(JsonElement json) {
            super.beforeToObject(json);
            JsonObject jsonObject = json.getAsJsonObject();

            ProductType productType = ProductType.find(LibUtils.getFromJson(jsonObject, "type", String.class));
            if (productType != null) {
                jsonObject.addProperty("status", productType.name());
            }
        }
    }

    /**
     * It tweaks the fields of the model
     *
     * @author shuklaalok7
     * @since 29/12/2016
     */
    static class FileAdapterFactory extends CustomTypeAdapterFactory<File> {
        FileAdapterFactory() {
            super(File.class);
        }

        @Override
        protected void beforeToJson(File source, JsonElement json) {
            super.beforeToJson(source, json);

            JsonObject jsonObject = json.getAsJsonObject();
            jsonObject.remove("string_id");
            jsonObject.addProperty("id", source.getStringId());

            if (source.getStatus() != null) {
                jsonObject.addProperty("status", source.getStatus().toString());
            }
        }

        @Override
        protected void beforeToObject(JsonElement json) {
            super.beforeToObject(json);

            JsonObject jsonObject = json.getAsJsonObject();
            jsonObject.addProperty("stringId", LibUtils.getFromJson(jsonObject, "id", String.class));
            jsonObject.remove("id");

            FileStatus fileStatus = FileStatus.find(LibUtils.getFromJson(jsonObject, "status", String.class));
            if (fileStatus != null) {
                jsonObject.addProperty("status", fileStatus.name());
            }
        }
    }

    /**
     * @author shuklaalok7
     * @since 29/12/2016
     */
    static class ResponseDeserializer implements JsonDeserializer<Response> {

        @Override
        public Response deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            Response<Object> response = new Response<>();

            final JsonObject json = jsonElement.getAsJsonObject();

            if (json.has("code")) {
                response.setCode(json.get("code").getAsInt());
            }

            if (json.has("paging")) {
                response.setPaging(context.<Paging>deserialize(json.get("paging"), Paging.class));
            }

            if (json.has("result")) {
                JsonElement resultElement = json.get("result");

                if (response.getCode() != 200) {
                    if (resultElement.isJsonPrimitive()) {
                        response.setErrorMessage(resultElement.getAsString());
                    }
                } else if (resultElement.isJsonArray()) {
                    if (response.getCode() == 200) {
                        // result is a list of objects
                        List<Object> result = new ArrayList<>();
                        JsonArray resultArray = resultElement.getAsJsonArray();

                        for (JsonElement element : resultArray) {
                            Type[] types = ((ParameterizedType) typeOfT).getActualTypeArguments();
                            result.add(context.deserialize(element, types[0]));
                        }

                        response.setResult(result);
                    }
                } else if (resultElement.isJsonObject()) {
                    Type[] types = ((ParameterizedType) typeOfT).getActualTypeArguments();
                    List<Object> result = Collections.singletonList(context.deserialize(resultElement, types[0]));
                    response.setResult(result);
                }
            }

            return response;
        }
    }

}
