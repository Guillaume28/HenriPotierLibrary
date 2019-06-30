package com.guillaume.testtechniquexebia.ui.store

import android.content.Intent
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
import com.guillaume.testtechniquexebia.databinding.ActivityStoreListBinding
import com.guillaume.testtechniquexebia.injection.ViewModelFactory
import com.guillaume.testtechniquexebia.ui.cart.CartListActivity
import kotlinx.android.synthetic.main.activity_store_list.*

class StoreListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStoreListBinding
    private lateinit var viewModel: StoreListViewModel
    private var errorSnackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this, ViewModelFactory(this)).get(StoreListViewModel::class.java)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_store_list)
        binding.bookList.layoutManager = LinearLayoutManager(
            this,
            RecyclerView.VERTICAL,
            false
        )

        viewModel = ViewModelProviders.of(this).get(StoreListViewModel::class.java)
        viewModel.errorMessage.observe(this, Observer { errorMessage ->
            if (errorMessage != null) showError(errorMessage) else hideError()
        })

        basketButton.setOnClickListener {
            startActivity(Intent(this, CartListActivity::class.java))
        }

        binding.viewModel = viewModel
    }

    private fun showError(@StringRes errorMessage: Int) {
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, viewModel.errorClickListener)
        errorSnackbar?.show()
    }

    private fun hideError() {
        errorSnackbar?.dismiss()
    }
}
