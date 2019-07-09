package com.guillaume.testtechniquexebia.ui.cart

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.guillaume.testtechniquexebia.R
import com.guillaume.testtechniquexebia.databinding.ActivityCartListBinding
import com.guillaume.testtechniquexebia.injection.ViewModelFactory

class CartListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCartListBinding
    private lateinit var viewModel: CartListViewModel
    private var errorSnackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this, ViewModelFactory(this)).get(CartListViewModel::class.java)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_cart_list)
        binding.bookList.layoutManager = LinearLayoutManager(
            this,
            RecyclerView.VERTICAL,
            false
        )

        viewModel = ViewModelProviders.of(this).get(CartListViewModel::class.java)
        viewModel.errorMessage.observe(this, Observer { errorMessage ->
            if (errorMessage != null) showError(errorMessage) else hideError()
        })
        binding.viewModel = viewModel
    }

    private fun showError(@StringRes errorMessage: Int) {
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.show()
    }

    private fun hideError() {
        errorSnackbar?.dismiss()
    }

}
