package com.talento.evaluacinmdulo5.ui.navigation

import com.talento.evaluacinmdulo5.model.ShoeItem

interface AppNavigator {
    fun showProductDetail(item: ShoeItem)
    fun showCart()
    fun goBack()
}
