package in.clayfish.printful.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import in.clayfish.printful.enums.ProductType;
import in.clayfish.printful.models.File;
import in.clayfish.printful.models.Product;
import in.clayfish.printful.models.Response;
import in.clayfish.printful.models.Variant;
import in.clayfish.printful.models.includable.ImageSize;
import in.clayfish.printful.models.includable.OptionType;
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
    static class ImageSizeAdapter implements JsonDeserializer<ImageSize>, JsonSerializer<ImageSize> {

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
    static class ProductAdapterFactory extends CustomTypeAdapterFactory<Product> {
        ProductAdapterFactory() {
            super(Product.class);
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
    static class ResponseAdapter implements JsonDeserializer<Response> {

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
                try {
                    response.setErrorMessage(json.get("result").getAsString());
                } catch (Exception ignored) {
                    if (response.getCode() == 200) {
                        // result is a list of objects
                        List<Object> result = new ArrayList<>();
                        JsonArray resultArray = json.get("result").getAsJsonArray();

                        for (JsonElement element : resultArray) {
                            // TODO Check things here. check typeOfT
                            Type[] types = ((ParameterizedType) typeOfT).getActualTypeArguments();
                            result.add(context.deserialize(element, types[0]));
                        }

                        response.setResult(result);
                    }
                }
            }

            return response;
        }
    }

    /**
     * @author shuklaalok7
     * @since 29/12/2016
     */
    static class ProductAdapter implements JsonDeserializer<Product>, JsonSerializer<Product> {

        @Override
        public Product deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            if (json == null) {
                return null;
            }

            Product product = new Product();
            final JsonObject jsonObject = json.getAsJsonObject();

            if (jsonObject.has("id")) {
                try {
                    long id = jsonObject.get("id").getAsLong();
                    product.setId(id);
                } catch (Exception ignored) {
                    // It may be a string id, leave it
                }
            }

            if (jsonObject.has("type")) {
                product.setType(ProductType.find(jsonObject.get("type").getAsString()));
            }

            product.setBrand(LibUtils.getFromJson(jsonObject, "brand", String.class));

            if (jsonObject.has("model")) {
                product.setModel(jsonObject.get("model").getAsString());
            }

            if (jsonObject.has("image")) {
                product.setImage(jsonObject.get("image").getAsString());
            }

            if (jsonObject.has("variant_code")) {
                product.setVariantCount(jsonObject.get("variant_code").getAsInt());
            }

            if (jsonObject.has("allowed_files") && jsonObject.get("allowed_files").isJsonArray()) {
                List<File> files = new ArrayList<>();
                JsonArray filesArray = jsonObject.get("allowed_files").getAsJsonArray();

                for (JsonElement element : filesArray) {
                    files.add(context.deserialize(element, File.class));
                }
                product.setFiles(files);
            }

            if (jsonObject.has("allowed_options") && jsonObject.get("allowed_options").isJsonArray()) {
                List<OptionType> optionTypes = new ArrayList<>();
                JsonArray optionsArray = jsonObject.get("allowed_options").getAsJsonArray();

                for (JsonElement element : optionsArray) {
                    optionTypes.add(context.deserialize(element, OptionType.class));
                }
                product.setAllowedOptions(optionTypes);
            }

            return product;
        }

        @Override
        public JsonElement serialize(Product product, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject json = new JsonObject();

            if (product.getId() > 0) {
                json.addProperty("id", product.getId());
            }

            if (product.getType() != null) {
                json.addProperty("type", product.getType().name().replace("_", "-"));
            }

            if (product.getBrand() != null) {
                json.addProperty("brand", product.getBrand());
            }

            if (product.getModel() != null) {
                json.addProperty("model", product.getModel());
            }

            if (product.getImage() != null) {
                json.addProperty("image", product.getImage());
            }

            if (product.getVariantCount() > 0) {
                json.addProperty("variant_count", product.getVariantCount());
            }

            if (product.getFiles() != null) {
                JsonArray filesArray = new JsonArray();
                for (File file : product.getFiles()) {
                    filesArray.add(context.serialize(file));
                }
                json.add("allowed_files", filesArray);
            }

            if (product.getAllowedOptions() != null) {
                JsonArray optionsArray = new JsonArray();
                for (OptionType optionType : product.getAllowedOptions()) {
                    optionsArray.add(context.serialize(optionType));
                }
                json.add("allowed_options", optionsArray);
            }

            return json;
        }
    }

}
