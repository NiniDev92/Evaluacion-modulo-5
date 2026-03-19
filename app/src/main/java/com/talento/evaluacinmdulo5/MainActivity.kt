package com.talento.evaluacinmdulo5

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.talento.evaluacinmdulo5.databinding.ActivityMainBinding
import com.talento.evaluacinmdulo5.model.ShoeItem
import com.talento.evaluacinmdulo5.ui.CartFragment
import com.talento.evaluacinmdulo5.ui.ProductDetailFragment
import com.talento.evaluacinmdulo5.ui.ProductListFragment
import com.talento.evaluacinmdulo5.ui.navigation.AppNavigator

class MainActivity : AppCompatActivity(), AppNavigator {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(binding.fragmentContainer.id, ProductListFragment.newInstance())
                .commit()
        }
    }

    override fun showProductDetail(item: ShoeItem) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, ProductDetailFragment.newInstance(item))
            .addToBackStack(ProductDetailFragment.TAG)
            .commit()
    }

    override fun showCart() {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, CartFragment.newInstance())
            .addToBackStack(CartFragment.TAG)
            .commit()
    }

    override fun goBack() {
        onBackPressedDispatcher.onBackPressed()
    }
}
