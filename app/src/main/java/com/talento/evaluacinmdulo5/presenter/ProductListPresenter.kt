package com.talento.evaluacinmdulo5.presenter

import com.talento.evaluacinmdulo5.model.LocalProductRepository
import com.talento.evaluacinmdulo5.presenter.contract.ProductListContract

class ProductListPresenter(
    private val repository: LocalProductRepository
) : ProductListContract.Presenter {

    private var view: ProductListContract.View? = null

    override fun attach(view: ProductListContract.View) {
        this.view = view
    }

    override fun detach() {
        view = null
    }

    override fun loadProducts() {
        val products = repository.returnShoeList()
        if (products.isEmpty()) {
            view?.showEmptyProducts()
        } else {
            view?.showProducts(products)
        }
    }
}
