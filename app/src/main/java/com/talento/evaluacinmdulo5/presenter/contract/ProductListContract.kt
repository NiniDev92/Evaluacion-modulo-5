package com.talento.evaluacinmdulo5.presenter.contract

import com.talento.evaluacinmdulo5.model.ShoeItem

interface ProductListContract {
    interface View {
        fun showProducts(products: List<ShoeItem>)
        fun showEmptyProducts()
    }

    interface Presenter {
        fun attach(view: View)
        fun detach()
        fun loadProducts()
    }
}
