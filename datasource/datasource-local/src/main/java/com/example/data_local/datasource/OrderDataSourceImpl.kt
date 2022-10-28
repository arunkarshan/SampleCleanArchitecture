package com.example.data_local.datasource

import com.example.sampleaction.repository.datasource.OrdersDataSource
import com.example.sampleaction.repository.model.DataDeliveryItem
import com.example.sampleaction.repository.model.DataDiscountResponse
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Named


/*********************************************************
 * Class   :  LoginDataSourceImpl
 * Author  :  Arun Nair
 * Created :  16/09/2022
 *******************************************************
 * Purpose :
 *******************************************************
 * Rework Details:
 * 1) {Author} :  {Date} : {Details}
 *********************************************************/
@Factory
@Named("Local")
class OrderDataSourceImpl() : OrdersDataSource {
    override suspend fun getOrderItems(id: Long): List<DataDeliveryItem> {
        throw Exception("This should not be used as Login don't have local impl")
    }

    override suspend fun getDeliveryItemDiscount(id: Long): DataDiscountResponse {
        throw Exception("This should not be used as Login don't have local impl")
    }

    override suspend fun getOrders(): List<Long> {
        throw Exception("This should not be used as Login don't have local impl")
    }


}