package `in`.clayfish.printful.utils

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.TypeAdapter
import com.google.gson.TypeAdapterFactory
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.io.IOException
import java.util.*
import kotlin.reflect.KClass

/**
 * Taken from http://stackoverflow.com/questions/11271375/gson-custom-seralizer-for-one-variable-of-many-in-an-object-using-typeadapter
 *
 * @author shuklaalok7
 * @since 29/12/2016
 */
internal open class CustomTypeAdapterFactory<C : Any>(private val customizedClass: KClass<C>) : TypeAdapterFactory {

    // we use a runtime check to guarantee that 'C' and 'T' are equal
    override fun <T> create(gson: Gson, type: TypeToken<T>): TypeAdapter<T>? =
            if (type.rawType == customizedClass) customizeClassAdapter(gson, type as TypeToken<C>) as TypeAdapter<T> else null

    /**
     * @param gson
     * @param type
     * @return
     */
    private fun customizeClassAdapter(gson: Gson, type: TypeToken<C>): TypeAdapter<C> {
        val delegate = gson.getDelegateAdapter(this, type)
        val elementAdapter = gson.getAdapter(JsonElement::class.java)
        return object : TypeAdapter<C>() {
            @Throws(IOException::class)
            override fun write(out: JsonWriter, value: C) {
                val tree = delegate.toJsonTree(value)
                beforeToJson(value, tree)
                elementAdapter.write(out, tree)
            }

            @Throws(IOException::class)
            override fun read(`in`: JsonReader): C {
                val tree = elementAdapter.read(`in`)
                beforeToObject(tree)
                return delegate.fromJsonTree(tree)
            }
        }
    }

    /**
     * Override this to muck with `toSerialize` before it is written to the outgoing JSON stream.
     *
     * @param source
     * @param json
     */
    protected open fun beforeToJson(source: C?, json: JsonElement?) {
        if (json == null || source == null) {
            return
        }
        val jsonObject = json.asJsonObject
        for (field in source.javaClass.declaredFields) {
            field.isAccessible = true
            val oldFieldName = field.name
            if (LibUtils.checkUpperCase(oldFieldName) > -1) {
                val newFieldName = LibUtils.convertToPhpStyle(oldFieldName)
                try {
                    val type = field.type
                    when {
                        type.isPrimitive -> when {
                            Boolean::class.javaPrimitiveType!!.isAssignableFrom(type) -> jsonObject.addProperty(newFieldName, field[source] as Boolean)
                            Char::class.javaPrimitiveType!!.isAssignableFrom(type) -> jsonObject.addProperty(newFieldName, field[source] as Char)
                            else -> jsonObject.addProperty(newFieldName, field[source] as Number)
                        }
                        String::class.java.isAssignableFrom(type) -> jsonObject.addProperty(newFieldName, field[source] as String)
                        Number::class.java.isAssignableFrom(type) -> jsonObject.addProperty(newFieldName, field[source] as Number)
                        Char::class.java.isAssignableFrom(type) -> jsonObject.addProperty(newFieldName, field[source] as Char)
                        Boolean::class.java.isAssignableFrom(type) -> jsonObject.addProperty(newFieldName, field[source] as Boolean)
                        else -> jsonObject.add(newFieldName, jsonObject[oldFieldName])
                    }

                    jsonObject.remove(oldFieldName)
                } catch (e: IllegalAccessException) {
                    e.printStackTrace()
                }
            }
        }
    }

    /**
     * Override this to muck with `deserialized` before it parsed into the application type.
     *
     * @param json
     */
    protected open fun beforeToObject(json: JsonElement?) {
        if (json == null || json.isJsonNull) return

        val jsonObject = json.asJsonObject
        val newFieldOldFieldMap: MutableMap<String, String> = HashMap()

        for ((foundFieldName) in jsonObject.entrySet())
        // This can be handled by gson automatically
            if (foundFieldName.contains("_"))
                newFieldOldFieldMap[foundFieldName] = LibUtils.convertToCamelCase(foundFieldName)

        for ((key, value) in newFieldOldFieldMap) {
            jsonObject.add(value, jsonObject[key])
            jsonObject.remove(key)
        }
    }

}
