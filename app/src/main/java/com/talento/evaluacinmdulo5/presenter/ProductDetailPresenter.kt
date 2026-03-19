package com.talento.evaluacinmdulo5.presenter

import com.talento.evaluacinmdulo5.model.CartStorage
import com.talento.evaluacinmdulo5.model.ShoeItem
import com.talento.evaluacinmdulo5.presenter.contract.ProductDetailContract

class ProductDetailPresenter(
    private val cartStorage: CartStorage
) : ProductDetailContract.Presenter {

    private var view: ProductDetailContract.View? = null
    private var currentProduct: ShoeItem? = null

    override fun attach(view: ProductDetailContract.View) {
        this.view = view
    }

    override fun detach() {
        view = null
    }

    override fun setProduct(item: ShoeItem) {
        currentProduct = item
        view?.showProduct(item)
    }

    override fun addCurrentProductToCart() {
        val product = currentProduct
        if (product == null) {
            view?.showError("No se encontró el producto seleccionado.")
            return
        }
        cartStorage.addItem(product)
        view?.showAddToCartSuccess(product)
    }
}
