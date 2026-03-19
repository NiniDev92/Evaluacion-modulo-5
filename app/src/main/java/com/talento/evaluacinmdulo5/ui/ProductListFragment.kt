package com.talento.evaluacinmdulo5.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.talento.evaluacinmdulo5.R
import com.talento.evaluacinmdulo5.databinding.FragmentProductListBinding
import com.talento.evaluacinmdulo5.model.LocalProductRepository
import com.talento.evaluacinmdulo5.model.ShoeItem
import com.talento.evaluacinmdulo5.presenter.ProductListPresenter
import com.talento.evaluacinmdulo5.presenter.contract.ProductListContract
import com.talento.evaluacinmdulo5.ui.adapter.ShoeAdapter
import com.talento.evaluacinmdulo5.ui.navigation.AppNavigator

class ProductListFragment : Fragment(R.layout.fragment_product_list), ProductListContract.View {

    private var _binding: FragmentProductListBinding? = null
    private val binding get() = _binding!!

    private val presenter: ProductListContract.Presenter by lazy {
        ProductListPresenter(LocalProductRepository())
    }

    private val adapter: ShoeAdapter by lazy {
        ShoeAdapter { item ->
            (activity as? AppNavigator)?.showProductDetail(item)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProductListBinding.bind(view)

        binding.recyclerShoes.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerShoes.adapter = adapter
        binding.buttonOpenCart.setOnClickListener {
            (activity as? AppNavigator)?.showCart()
        }

        presenter.attach(this)
        presenter.loadProducts()
    }

    override fun showProducts(products: List<ShoeItem>) {
        binding.recyclerShoes.visibility = View.VISIBLE
        binding.textEmpty.visibility = View.GONE
        adapter.submitList(products)
    }

    override fun showEmptyProducts() {
        binding.recyclerShoes.visibility = View.GONE
        binding.textEmpty.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        presenter.detach()
        _binding = null
        super.onDestroyView()
    }

    companion object {
        fun newInstance(): ProductListFragment = ProductListFragment()
    }
}
