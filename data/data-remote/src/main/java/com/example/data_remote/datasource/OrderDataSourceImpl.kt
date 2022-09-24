package com.example.data_remote.datasource

import com.example.data_remote.models.mappers.toDomain
import com.example.data_remote.network.OrderApi
import com.example.data_remote.utils.callApi
import com.example.domain.base.ResultWrapper
import com.example.domain.base.map
import com.example.domain.models.DeliveryItemDomain
import com.example.domain.models.DiscountDomain
import com.example.sampleaction.repository.datasource.remote.OrdersRemoteSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.annotation.Factory
import org.koin.core.annotation.InjectedParam
import org.koin.core.annotation.Named
import org.koin.core.qualifier.named


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
class OrderDataSourceImpl(
    private val orderApi: OrderApi,
    @Named("IODispatcher") private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : OrdersRemoteSource {
    override suspend fun getOrderItems(id: Long): ResultWrapper<List<DeliveryItemDomain>> {
        return callApi(dispatcher) { orderApi.fetchOrderById(id) }.map { it.items.map { item -> item.toDomain() } }
    }

    override suspend fun getDeliveryItemDiscount(id: Long): ResultWrapper<DiscountDomain> {
        return callApi(dispatcher) { orderApi.discount(id) }.map { it.toDomain() }
    }

    override suspend fun getOrders(): ResultWrapper<List<Long>> {
        return callApi(dispatcher) { orderApi.fetchOrders() }.map { it.orders }
    }
}