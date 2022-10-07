package com.example.sampleaction.repository.datasource

import com.example.domain.base.ResultWrapper
import com.example.domain.models.DeliveryItemDomain
import com.example.domain.models.DiscountDomain
import com.example.sampleaction.repository.model.DataDeliveryItem
import com.example.sampleaction.repository.model.DataDiscountResponse
import com.example.sampleaction.repository.model.DataResultWrapper


/*********************************************************
 * Class   :  LoginLoacalSource
 * Author  :  Arun Nair
 * Created :  15/09/2022
 *******************************************************
 * Purpose :
 *******************************************************
 * Rework Details:
 * 1) {Author} :  {Date} : {Details}
 *********************************************************/

interface OrdersDataSource {
    suspend fun getOrderItems(id: Long): DataResultWrapper<List<DataDeliveryItem>>
    suspend fun getDeliveryItemDiscount(id: Long): DataResultWrapper<DataDiscountResponse>
    suspend fun getOrders(): DataResultWrapper<List<Long>>
}