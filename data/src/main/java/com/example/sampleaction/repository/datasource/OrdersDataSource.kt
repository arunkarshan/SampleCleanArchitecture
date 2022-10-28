package com.example.sampleaction.repository.datasource

import com.example.sampleaction.repository.model.DataDeliveryItem
import com.example.sampleaction.repository.model.DataDiscountResponse


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
    suspend fun getOrderItems(id: Long): List<DataDeliveryItem>
    suspend fun getDeliveryItemDiscount(id: Long): DataDiscountResponse
    suspend fun getOrders(): List<Long>
}