package com.example.sampleaction.ui.orderlist

import android.os.Bundle
import android.view.View
import com.example.sampleaction.R
import com.example.sampleaction.BR
import com.example.sampleaction.baseclasses.BaseFragment
import com.example.sampleaction.baseclasses.BaseListAdapter
import com.example.sampleaction.databinding.FragmentOrdersBinding
import com.example.sampleaction.databinding.LayoutDeliveryItemBinding
import com.example.sampleaction.models.vo.DeliveryItemVO
import com.example.sampleaction.utils.extentions.observeData
import org.koin.androidx.viewmodel.ext.android.viewModel


/*********************************************************
 * Class   :  OrderListFragment
 * Author  :  Arun Nair
 * Created :  16/09/2022
 *******************************************************
 * Purpose :
 *******************************************************
 * Rework Details:
 * 1) {Author} :  {Date} : {Details}
 *********************************************************/

class OrderListFragment : BaseFragment<FragmentOrdersBinding>(layoutId = R.layout.fragment_orders) {
    private val orderViewModel: OrderListViewModel by viewModel()
    private val adapter by lazy {
        BaseListAdapter<DeliveryItemVO, LayoutDeliveryItemBinding>(
            itemLayoutId = R.layout.layout_delivery_item,
            bindingItemId = BR.item,
            bindingMap = hashMapOf(
                BR.viewModel to orderViewModel
            )
        )
    }

    /***************************************
     * Declarations
     ***************************************/
    override fun onBindingCreated(binding: FragmentOrdersBinding) {
        super.onBindingCreated(binding)
        binding.run{
            this.viewModel = orderViewModel
            rcyOrderItems.adapter = adapter
        }
    }

    /***************************************
     * Declarations
     ***************************************/
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData(orderViewModel.navigation){ handleNavigation(it) }
        observeData(orderViewModel.orderList){ adapter.submitList(it)}
    }
}