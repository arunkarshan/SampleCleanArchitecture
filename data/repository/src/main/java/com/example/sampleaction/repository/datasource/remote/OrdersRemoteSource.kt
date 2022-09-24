package com.example.sampleaction.repository.datasource.remote

import com.example.domain.base.ResultWrapper
import com.example.domain.models.DeliveryItemDomain
import com.example.domain.models.DiscountDomain


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

interface OrdersRemoteSource {
    suspend fun getOrderItems(id: Long): ResultWrapper<List<DeliveryItemDomain>>
    suspend fun getDeliveryItemDiscount(id: Long): ResultWrapper<DiscountDomain>
    suspend fun getOrders(): ResultWrapper<List<Long>>
}