package com.appspiriment.kashproxy.maplist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.appspiriment.kashproxy.R

class ResponseMapListActivity : AppCompatActivity() {
    private val adapter by lazy {
        BaseListAdapter<DeliveryItemVO, LayoutDeliveryItemBinding>(
            itemLayoutId = R.layout.layout_delivery_item,
            bindingItemId = BR.item,
            bindingMap = hashMapOf(
                BR.viewModel to orderViewModel
            )
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_response_map_list)
    }
}