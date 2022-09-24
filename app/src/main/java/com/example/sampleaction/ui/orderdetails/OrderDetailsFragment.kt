package com.example.sampleaction.ui.orderdetails

import androidx.navigation.fragment.navArgs
import com.example.sampleaction.R
import com.example.sampleaction.baseclasses.BaseFragment
import com.example.sampleaction.databinding.FragmentDeliveryItemDetailsBinding
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

class OrderDetailsFragment : BaseFragment<FragmentDeliveryItemDetailsBinding>(
    layoutId = R.layout.fragment_delivery_item_details
) {
    private val args by navArgs<OrderDetailsFragmentArgs>()

    val viewModel: OrderDetailsViewModel by viewModel()

    override fun onBindingCreated(binding: FragmentDeliveryItemDetailsBinding) {
        super.onBindingCreated(binding)
        binding.viewModel = viewModel.apply {
            loadItem(args.deliveryItem)
        }
    }

}