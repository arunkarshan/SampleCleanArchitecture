package com.example.sampleaction.ui.orderdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.sampleaction.baseclasses.BaseViewModel
import com.example.sampleaction.models.vo.DeliveryItemVO
import com.example.utils.di.getLogger
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
class OrderDetailsViewModel : BaseViewModel() {

    private val _deliveryItem = MutableLiveData<DeliveryItemVO>()
    val deliveryItem: LiveData<DeliveryItemVO> = _deliveryItem

    fun loadItem(item: DeliveryItemVO) {
        getLogger().printLog(item.name)
        _deliveryItem.value = item
    }

}