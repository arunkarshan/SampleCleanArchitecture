package com.example.domain.orders

import com.example.domain.base.ResultWrapper
import com.example.domain.models.DeliveryItemDomain
import kotlinx.coroutines.flow.Flow

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

interface OrderRepository {
    suspend fun getDeliveryItems(loadCombined: Boolean): Flow<ResultWrapper<List<DeliveryItemDomain>>>
}