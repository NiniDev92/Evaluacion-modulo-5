package com.talento.evaluacinmdulo5.presenter.contract

import com.talento.evaluacinmdulo5.model.ShoeItem

interface ProductDetailContract {
    interface View {
        fun showProduct(item: ShoeItem)
        fun showAddToCartSuccess(item: ShoeItem)
        fun showError(message: String)
    }

    interface Presenter {
        fun attach(view: View)
        fun detach()
        fun setProduct(item: ShoeItem)
        fun addCurrentProductToCart()
    }
}
