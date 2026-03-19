package com.talento.evaluacinmdulo5.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.talento.evaluacinmdulo5.R
import com.talento.evaluacinmdulo5.databinding.FragmentCartBinding
import com.talento.evaluacinmdulo5.model.CartStorage
import com.talento.evaluacinmdulo5.model.ShoeItem
import com.talento.evaluacinmdulo5.presenter.CartPresenter
import com.talento.evaluacinmdulo5.presenter.contract.CartContract
import com.talento.evaluacinmdulo5.ui.adapter.CartAdapter
import com.talento.evaluacinmdulo5.ui.navigation.AppNavigator
import java.text.NumberFormat
import java.util.Locale

class CartFragment : Fragment(R.layout.fragment_cart), CartContract.View {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    private val presenter: CartContract.Presenter by lazy {
        CartPresenter(
            CartStorage(
                requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            )
        )
    }

    private val adapter: CartAdapter by lazy {
        CartAdapter { position ->
            presenter.removeItem(position)
        }
    }

    private val currencyFormatter = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("es-CL"))

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCartBinding.bind(view)

        binding.recyclerCart.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerCart.adapter = adapter

        binding.buttonBack.setOnClickListener {
            (activity as? AppNavigator)?.goBack()
        }
        binding.buttonContinueShopping.setOnClickListener {
            (activity as? AppNavigator)?.goBack()
        }
        binding.buttonClearCart.setOnClickListener {
            presenter.clearCart()
        }

        presenter.attach(this)
        presenter.loadCart()
    }

    override fun showCartItems(items: List<ShoeItem>, total: Double) {
        binding.recyclerCart.visibility = View.VISIBLE
        binding.textEmptyCart.visibility = View.GONE
        binding.buttonClearCart.isEnabled = true
        binding.textTotalAmount.text = currencyFormatter.format(total)
        adapter.submitList(items)
    }

    override fun showEmptyCart() {
        adapter.submitList(emptyList())
        binding.recyclerCart.visibility = View.GONE
        binding.textEmptyCart.visibility = View.VISIBLE
        binding.buttonClearCart.isEnabled = false
        binding.textTotalAmount.text = currencyFormatter.format(0)
    }

    override fun showMessage(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        presenter.detach()
        _binding = null
        super.onDestroyView()
    }

    companion object {
        const val TAG = "CartFragment"
        private const val PREFS_NAME = "shoes_tap_prefs"

        fun newInstance(): CartFragment = CartFragment()
    }
}
