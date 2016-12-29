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

import in.clayfish.printful.enums.OrderStatus;
import in.clayfish.printful.enums.ProductType;
import in.clayfish.printful.models.File;
import in.clayfish.printful.models.Order;
import in.clayfish.printful.models.PackingSlip;
import in.clayfish.printful.models.Product;
import in.clayfish.printful.models.Response;
import in.clayfish.printful.models.StoreData;
import in.clayfish.printful.models.Variant;
import in.clayfish.printful.models.includable.Address;
import in.clayfish.printful.models.includable.ImageSize;
import in.clayfish.printful.models.includable.Item;
import in.clayfish.printful.models.includable.Paging;
import in.clayfish.printful.models.includable.ProductVariant;
import in.clayfish.printful.models.includable.Shipment;
import in.clayfish.printful.models.info.AddressInfo;
import in.clayfish.printful.models.info.ItemInfo;

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
    static class ImageSizeSearializer implements JsonDeserializer<ImageSize>, JsonSerializer<ImageSize> {

        @Override
        public ImageSize deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

            return null;
        }

        @Override
        public JsonElement serialize(ImageSize imageSize, Type typeOfSrc, JsonSerializationContext context) {
            return null;
        }
    }

    /**
     * It tweaks the fields of the model
     *
     * @author shuklaalok7
     * @since 29/12/2016
     */
    static class VariantAdapterFactory extends CustomTypeAdapterFactory<Variant> {
        VariantAdapterFactory() {
            super(Variant.class);
        }
    }

    /**
     * It tweaks the fields of the model
     *
     * @author shuklaalok7
     * @since 29/12/2016
     */
    static class StoreDataAdapterFactory extends CustomTypeAdapterFactory<StoreData> {
        StoreDataAdapterFactory() {
            super(StoreData.class);
        }
    }

    /**
     * It tweaks the fields of the model
     *
     * @author shuklaalok7
     * @since 29/12/2016
     */
    static class ItemAdapterFactory extends CustomTypeAdapterFactory<Item> {
        ItemAdapterFactory() {
            super(Item.class);
        }
    }

    /**
     * It tweaks the fields of the model
     *
     * @author shuklaalok7
     * @since 29/12/2016
     */
    static class ProductVariantAdapterFactory extends CustomTypeAdapterFactory<ProductVariant> {
        ProductVariantAdapterFactory() {
            super(ProductVariant.class);
        }
    }

    /**
     * It tweaks the fields of the model
     *
     * @author shuklaalok7
     * @since 29/12/2016
     */
    static class ShipmentAdapterFactory extends CustomTypeAdapterFactory<Shipment> {
        ShipmentAdapterFactory() {
            super(Shipment.class);
        }
    }

    /**
     * It tweaks the fields of the model
     *
     * @author shuklaalok7
     * @since 29/12/2016
     */
    static class AddressInfoAdapterFactory extends CustomTypeAdapterFactory<AddressInfo> {
        AddressInfoAdapterFactory() {
            super(AddressInfo.class);
        }
    }

    /**
     * It tweaks the fields of the model
     *
     * @author shuklaalok7
     * @since 29/12/2016
     */
    static class ItemInfoAdapterFactory extends CustomTypeAdapterFactory<ItemInfo> {
        ItemInfoAdapterFactory() {
            super(ItemInfo.class);
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

            json.getAsJsonObject().addProperty("status", source.getStatus().toString());
        }

        @Override
        protected void beforeToObject(JsonElement json) {
            super.beforeToObject(json);

            JsonObject jsonObject = json.getAsJsonObject();
            OrderStatus orderStatus = OrderStatus.find(jsonObject.get("status").getAsString());
            jsonObject.addProperty("status", orderStatus.name());
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

            stateObject.addProperty("code", jsonObject.get("stateCode").getAsString());
            stateObject.addProperty("name", jsonObject.get("stateName").getAsString());

            countryObject.addProperty("code", jsonObject.get("countryCode").getAsString());
            countryObject.addProperty("name", jsonObject.get("countryName").getAsString());

            jsonObject.add("state", stateObject);
            jsonObject.add("country", countryObject);
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

            json.getAsJsonObject().addProperty("type", source.getType().toString());
        }

        @Override
        protected void beforeToObject(JsonElement json) {
            super.beforeToObject(json);
            JsonObject jsonObject = json.getAsJsonObject();
            ProductType type = ProductType.find(jsonObject.get("type").getAsString());
            jsonObject.addProperty("type", type.name());
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
        }

        @Override
        protected void beforeToObject(JsonElement json) {
            super.beforeToObject(json);

            JsonObject jsonObject = json.getAsJsonObject();
            jsonObject.addProperty("stringId", jsonObject.get("id").getAsString());
            jsonObject.remove("id");
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
                response.setPaging(context.deserialize(json.get("paging"), Paging.class));
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
