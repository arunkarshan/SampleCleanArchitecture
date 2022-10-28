package com.example.sampleaction.repository.data

import com.example.domain.orders.OrderRepository
import com.example.domain.utils.asFlow
import com.example.sampleaction.repository.datasource.OrdersDataSource
import com.example.sampleaction.repository.model.mapper.toDomain
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Named

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

@Factory
internal class OrderRepositoryImpl(
    @Named("Remote") private val ordersRemoteSource: OrdersDataSource,
    @Named("IODispatcher") private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : OrderRepository {
    override suspend fun getOrderItems(id: Long) =
        ordersRemoteSource.getOrderItems(id)
            .map { it.toDomain() }
            .asFlow(dispatcher)

    override suspend fun getDeliveryItemDiscount(id: Long) =
        ordersRemoteSource.getDeliveryItemDiscount(id)
            .toDomain()
            .asFlow(dispatcher)

    override suspend fun getOrders() =
        flow {
            ordersRemoteSource.getOrders().forEach { emit(it) }
        }.flowOn(dispatcher)
}