package in.clayfish.printful.utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shuklaalok7
 * @since 29/12/2016
 */
class CustomTypeAdapterFactory<C> implements TypeAdapterFactory {

    private final Class<C> customizedClass;

    public CustomTypeAdapterFactory(Class<C> customizedClass) {
        this.customizedClass = customizedClass;
    }

    @SuppressWarnings("unchecked") // we use a runtime check to guarantee that 'C' and 'T' are equal
    public final <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        return type.getRawType() == customizedClass
                ? (TypeAdapter<T>) customizeClassAdapter(gson, (TypeToken<C>) type)
                : null;
    }

    /**
     * @param gson
     * @param type
     * @return
     */
    private TypeAdapter<C> customizeClassAdapter(Gson gson, TypeToken<C> type) {
        final TypeAdapter<C> delegate = gson.getDelegateAdapter(this, type);
        final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
        return new TypeAdapter<C>() {
            @Override
            public void write(JsonWriter out, C value) throws IOException {
                JsonElement tree = delegate.toJsonTree(value);
                beforeToJson(value, tree);
                elementAdapter.write(out, tree);
            }

            @Override
            public C read(JsonReader in) throws IOException {
                JsonElement tree = elementAdapter.read(in);
                beforeToObject(tree);
                return delegate.fromJsonTree(tree);
            }
        };
    }

    /**
     * Override this to muck with {@code toSerialize} before it is written to the outgoing JSON stream.
     *
     * @param source
     * @param json
     */
    protected void beforeToJson(C source, JsonElement json) {
        JsonObject jsonObject = json.getAsJsonObject();
        for (Field field : source.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            String oldFieldName = field.getName();

            if (LibUtils.checkUpperCase(oldFieldName) > -1) {
                String newFieldName = LibUtils.convertToPhpStyle(oldFieldName);
                try {
                    Class<?> type = field.getType();

                    if (String.class.isAssignableFrom(type)) {
                        jsonObject.addProperty(newFieldName, (String) field.get(source));
                    } else if (Number.class.isAssignableFrom(type)) {
                        jsonObject.addProperty(newFieldName, (Number) field.get(source));
                    } else if (Character.class.isAssignableFrom(type)) {
                        jsonObject.addProperty(newFieldName, (Character) field.get(source));
                    } else if (Boolean.class.isAssignableFrom(type)) {
                        jsonObject.addProperty(newFieldName, (Boolean) field.get(source));
                    } else {
                        throw new IllegalArgumentException("Cannot serialize it, please use a custom serializer.");
                    }
                    jsonObject.remove(oldFieldName);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Override this to muck with {@code deserialized} before it parsed into the application type.
     *
     * @param json
     */
    protected void beforeToObject(JsonElement json) {
        JsonObject jsonObject = json.getAsJsonObject();
        Map<String, String> newFieldOldFieldMap = new HashMap<>();
        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            String foundFieldName = entry.getKey();
            if (foundFieldName.contains("_")) {
                String newFieldName = LibUtils.convertToCamelCase(foundFieldName);
                newFieldOldFieldMap.put(foundFieldName, newFieldName);
            }
        }

        for (Map.Entry<String, String> entry : newFieldOldFieldMap.entrySet()) {
            jsonObject.add(entry.getValue(), jsonObject.get(entry.getKey()));
            jsonObject.remove(entry.getKey());
        }
    }
}
