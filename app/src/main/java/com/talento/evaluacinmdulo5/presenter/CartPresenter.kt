package com.talento.evaluacinmdulo5.presenter

import com.talento.evaluacinmdulo5.model.CartStorage
import com.talento.evaluacinmdulo5.presenter.contract.CartContract

class CartPresenter(
    private val cartStorage: CartStorage
) : CartContract.Presenter {

    private var view: CartContract.View? = null

    override fun attach(view: CartContract.View) {
        this.view = view
    }

    override fun detach() {
        view = null
    }

    override fun loadCart() {
        val items = cartStorage.getItems()
        if (items.isEmpty()) {
            view?.showEmptyCart()
            return
        }
        view?.showCartItems(items, items.sumOf { it.precio })
    }

    override fun removeItem(position: Int) {
        val updatedItems = cartStorage.removeItemAt(position)
        if (updatedItems.isEmpty()) {
            view?.showEmptyCart()
            view?.showMessage("Producto eliminado. Tu carrito quedó vacío.")
            return
        }
        view?.showCartItems(updatedItems, updatedItems.sumOf { it.precio })
        view?.showMessage("Producto eliminado del carrito.")
    }

    override fun clearCart() {
        cartStorage.clear()
        view?.showEmptyCart()
        view?.showMessage("Se vació todo el carrito.")
    }
}
