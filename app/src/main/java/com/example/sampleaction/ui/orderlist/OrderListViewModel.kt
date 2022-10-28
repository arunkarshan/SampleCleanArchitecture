package com.example.sampleaction.ui.orderlist

import androidx.lifecycle.*
import com.example.data_remote.models.mappers.toUi
import com.example.domain.orders.GetOrderItemsListUseCase
import com.example.sampleaction.baseclasses.BaseViewModel
import com.example.sampleaction.models.events.SingleLiveEvent
import com.example.sampleaction.models.vo.DeliveryItemVO
import com.example.sampleaction.utils.extentions.collectWrapper
import com.example.sampleaction.utils.extentions.transformCollect
import org.koin.android.annotation.KoinViewModel


/*********************************************************
 * Class   :  OrderListViewModel
 * Author  :  Arun Nair
 * Created :  16/09/2022
 *******************************************************
 * Purpose :
 *******************************************************
 * Rework Details:
 * 1) {Author} :  {Date} : {Details}
 *********************************************************/
@KoinViewModel
class OrderListViewModel(
    private val getOrderItemsListUseCase: GetOrderItemsListUseCase
) : BaseViewModel() {
    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _orderList = MutableLiveData<List<DeliveryItemVO>>()
    val orderList : LiveData<List<DeliveryItemVO>> = _orderList

    private val orderError = SingleLiveEvent<String?>()

    fun loadOrders(combined: Boolean) {
        getOrderItemsListUseCase.collectWrapper(
            scope = viewModelScope,
            param = GetOrderItemsListUseCase.Params(loadCombined = combined),
            data = _orderList,
            dataTransformer = { it.map { item -> item.toUi() } },
            error = orderError,
            loading = _isLoading
        )
    }

    fun showItemDetail(item: DeliveryItemVO) {
        navigate(OrderListFragmentDirections.showOrderDeliveryItem(item))
    }
}