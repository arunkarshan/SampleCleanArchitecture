package com.example.data_remote.datasource

import com.example.data_remote.models.mappers.toData
import com.example.data_remote.network.OrderApi
import com.example.sampleaction.repository.datasource.OrdersDataSource
import com.example.sampleaction.repository.model.DataDeliveryItem
import com.example.sampleaction.repository.model.DataDiscountResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
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
@Named("Remote")
class OrderDataSourceImpl(
    private val orderApi: OrderApi,
    @Named("IODispatcher") private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : OrdersDataSource {
    override suspend fun getOrderItems(id: Long): List<DataDeliveryItem> {
        return orderApi.fetchOrderById(id).items.map { it.toData() } 
    }

    override suspend fun getDeliveryItemDiscount(id: Long): DataDiscountResponse {
        return orderApi.discount(id).toData()
    }

    override suspend fun getOrders(): List<Long> {
        return orderApi.fetchOrders().orders
    }
}