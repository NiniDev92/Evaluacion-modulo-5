package com.talento.evaluacinmdulo5.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.talento.evaluacinmdulo5.R
import com.talento.evaluacinmdulo5.databinding.FragmentProductDetailBinding
import com.talento.evaluacinmdulo5.model.CartStorage
import com.talento.evaluacinmdulo5.model.ShoeItem
import com.talento.evaluacinmdulo5.presenter.ProductDetailPresenter
import com.talento.evaluacinmdulo5.presenter.contract.ProductDetailContract
import com.talento.evaluacinmdulo5.ui.navigation.AppNavigator
import java.text.NumberFormat
import java.util.Locale

class ProductDetailFragment : Fragment(R.layout.fragment_product_detail), ProductDetailContract.View {

    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!

    private val presenter: ProductDetailContract.Presenter by lazy {
        ProductDetailPresenter(
            CartStorage(
                requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            )
        )
    }

    private val currencyFormatter = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("es-CL"))
    private var selectedProduct: ShoeItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        selectedProduct = arguments?.readShoeItem(ARG_PRODUCT)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProductDetailBinding.bind(view)
        presenter.attach(this)

        binding.buttonBack.setOnClickListener {
            (activity as? AppNavigator)?.goBack()
        }
        binding.buttonOpenCart.setOnClickListener {
            (activity as? AppNavigator)?.showCart()
        }
        binding.buttonAddToCart.setOnClickListener {
            presenter.addCurrentProductToCart()
        }

        val product = selectedProduct
        if (product == null) {
            showError(getString(R.string.error_product_not_found))
            return
        }
        presenter.setProduct(product)
    }

    override fun showProduct(item: ShoeItem) {
        binding.textProductName.text = item.nombre
        binding.textProductPrice.text = currencyFormatter.format(item.precio)
        binding.textProductDescription.text = getString(
            R.string.product_description_template,
            item.nombre
        )
        Glide.with(binding.imageProduct)
            .load(item.urlImagen)
            .placeholder(R.drawable.bg_shoe_placeholder)
            .error(R.drawable.bg_shoe_placeholder)
            .centerCrop()
            .into(binding.imageProduct)
    }

    override fun showAddToCartSuccess(item: ShoeItem) {
        Snackbar.make(
            binding.root,
            getString(R.string.added_to_cart_message, item.nombre),
            Snackbar.LENGTH_LONG
        ).show()
    }

    override fun showError(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        presenter.detach()
        _binding = null
        super.onDestroyView()
    }

    private fun Bundle.readShoeItem(key: String): ShoeItem? {
        return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            getSerializable(key, ShoeItem::class.java)
        } else {
            @Suppress("DEPRECATION")
            getSerializable(key) as? ShoeItem
        }
    }

    companion object {
        const val TAG = "ProductDetailFragment"
        private const val ARG_PRODUCT = "arg_product"
        private const val PREFS_NAME = "shoes_tap_prefs"

        fun newInstance(product: ShoeItem): ProductDetailFragment {
            return ProductDetailFragment().apply {
                arguments = Bundle().apply { putSerializable(ARG_PRODUCT, product) }
            }
        }
    }
}
