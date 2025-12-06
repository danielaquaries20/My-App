package com.daniel.myapp.app_tour.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.crocodic.core.base.activity.CoreActivity
import com.crocodic.core.base.adapter.ReactiveListAdapter
import com.crocodic.core.data.CoreSession
import com.crocodic.core.extension.openActivity
import com.crocodic.core.extension.tos
import com.daniel.myapp.R
import com.daniel.myapp.app_tour.model.DataProduct
import com.daniel.myapp.app_tour.ui.home.bottom_sheet.BottomSheetFilterProducts
import com.daniel.myapp.app_tour.ui.home.bottom_sheet.BottomSheetSortingProducts
import com.daniel.myapp.app_tour.ui.login.LoginActivity
import com.daniel.myapp.app_tour.ui.settings.SettingsActivity
import com.daniel.myapp.databinding.ActivityHomeTourBinding
import com.daniel.myapp.databinding.ItemProductBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import com.crocodic.core.base.adapter.PaginationAdapter
import com.crocodic.core.base.adapter.PaginationLoadState
import com.crocodic.core.extension.toJson
import com.daniel.myapp.app_tour.ui.detail.DetailProductActivity
import com.google.gson.Gson
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class HomeTourActivity :
    CoreActivity<ActivityHomeTourBinding, HomeViewModel>(R.layout.activity_home_tour) {

    @Inject
    lateinit var gson: Gson

    var inputKeyword = ""

    /*private val adapterCore by lazy {
        ReactiveListAdapter<ItemProductBinding, DataProduct>(R.layout.item_product).initItem { position, data ->
            openActivity<DetailProductActivity> {
                val dataProduct = data.toJson(gson)
                putExtra(DetailProductActivity.DATA, dataProduct)
            }
        }
    }*/

    private val pagingAdapterCore by lazy {
        PaginationAdapter<ItemProductBinding, DataProduct>(R.layout.item_product).initItem { position, data ->
            openActivity<DetailProductActivity> {
                val dataProduct = data.toJson(gson)
                putExtra(DetailProductActivity.DATA, dataProduct)
            }
        }
    }

    @Inject
    lateinit var session: CoreSession

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.activity = this

//        binding.rvShowData.adapter = adapterCore
//        binding.rvShowData.adapter = pagingAdapterCore
        with(pagingAdapterCore) {
            binding.rvShowData.adapter = withLoadStateFooter(PaginationLoadState.default)
        }

        val desc = "Hallo, " + session.getString(
            LoginActivity.FIRST_NAME
        ) + " " + session.getString(LoginActivity.LAST_NAME)

        binding.tvDetail.text = desc

        binding.btnSettings.setOnClickListener(this)
        binding.ftbFilter.setOnClickListener(this)
        binding.ftbSort.setOnClickListener(this)

        binding.etSearch.editText?.doOnTextChanged { text, start, before, count ->
            val keyword = text.toString().trim()
            viewModel.getProduct(keyword)
        }

        observe()

//        viewModel.getProduct()
        lifecycleScope.launch {
            viewModel.queries.emit(Triple("", "", ""))
        }
    }

    override fun onStart() {
        super.onStart()
        session.setValue("page", 0)
    }

    private fun observe() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                /*launch {
                    viewModel.product.collect { data ->
                        Log.d("API_RESPONSE", "Data: $data")
                        adapterCore.submitList(data)
                    }
                }*/
                launch {
                    viewModel.getPagingProducts().collectLatest { data ->
                        pagingAdapterCore.submitData(data)
                    }
                }
            }
        }
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.btnSettings -> openActivity<SettingsActivity>()
            binding.ftbFilter -> {
                val btmSht = BottomSheetFilterProducts { filter ->
                    viewModel.filterProducts(filter)
                }

                btmSht.show(supportFragmentManager, "BtmShtFilteringProducts")
            }

            binding.ftbSort -> {
                val btmSht = BottomSheetSortingProducts { sortBy, order ->
                    viewModel.sortProducts(sortBy, order)
                }

                btmSht.show(supportFragmentManager, "BtmShtSortingProducts")
            }
        }
    }

}