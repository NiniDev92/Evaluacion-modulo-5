package com.talento.evaluacinmdulo5.model

import android.content.SharedPreferences
import org.json.JSONArray
import org.json.JSONObject

class CartStorage(
    private val sharedPreferences: SharedPreferences
) {

    fun getItems(): MutableList<ShoeItem> {
        val rawItems = sharedPreferences.getString(KEY_CART_ITEMS, "[]").orEmpty()
        val jsonArray = JSONArray(rawItems)
        val items = mutableListOf<ShoeItem>()

        for (index in 0 until jsonArray.length()) {
            val itemJson = jsonArray.getJSONObject(index)
            items.add(
                ShoeItem(
                    id = itemJson.optString(KEY_ID),
                    nombre = itemJson.optString(KEY_NOMBRE),
                    urlImagen = itemJson.optString(KEY_URL_IMAGEN),
                    precio = itemJson.optDouble(KEY_PRECIO)
                )
            )
        }
        return items
    }

    fun addItem(item: ShoeItem): List<ShoeItem> {
        val items = getItems()
        items.add(item)
        saveItems(items)
        return items
    }

    fun removeItemAt(position: Int): List<ShoeItem> {
        val items = getItems()
        if (position in items.indices) {
            items.removeAt(position)
            saveItems(items)
        }
        return items
    }

    fun clear() {
        sharedPreferences.edit().putString(KEY_CART_ITEMS, "[]").apply()
    }

    private fun saveItems(items: List<ShoeItem>) {
        val jsonArray = JSONArray()
        items.forEach { item ->
            val itemJson = JSONObject()
                .put(KEY_ID, item.id)
                .put(KEY_NOMBRE, item.nombre)
                .put(KEY_URL_IMAGEN, item.urlImagen)
                .put(KEY_PRECIO, item.precio)
            jsonArray.put(itemJson)
        }
        sharedPreferences.edit().putString(KEY_CART_ITEMS, jsonArray.toString()).apply()
    }

    companion object {
        private const val KEY_CART_ITEMS = "cart_items"
        private const val KEY_ID = "id"
        private const val KEY_NOMBRE = "nombre"
        private const val KEY_URL_IMAGEN = "urlImagen"
        private const val KEY_PRECIO = "precio"
    }
}
