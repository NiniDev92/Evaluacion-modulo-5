package com.talento.evaluacinmdulo5.presenter.contract

import com.talento.evaluacinmdulo5.model.ShoeItem

interface CartContract {
    interface View {
        fun showCartItems(items: List<ShoeItem>, total: Double)
        fun showEmptyCart()
        fun showMessage(message: String)
    }

    interface Presenter {
        fun attach(view: View)
        fun detach()
        fun loadCart()
        fun removeItem(position: Int)
        fun clearCart()
    }
}
