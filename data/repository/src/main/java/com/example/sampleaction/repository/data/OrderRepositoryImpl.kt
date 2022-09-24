package com.example.sampleaction.repository.data

import com.example.domain.base.*
import com.example.domain.models.DeliveryItemDomain
import com.example.domain.orders.OrderRepository
import com.example.domain.utils.*
import com.example.sampleaction.repository.datasource.local.OrdersLocalSource
import com.example.sampleaction.repository.datasource.remote.OrdersRemoteSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Factory
import java.util.stream.Collectors.toList

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
    private val ordersRemoteSource: OrdersRemoteSource,
    private val ordersLocalSource: OrdersLocalSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : OrderRepository {
    override suspend fun getDeliveryItems(loadCombined: Boolean): Flow<ResultWrapper<List<DeliveryItemDomain>>> {
        return ordersRemoteSource.run {
            withContext(dispatcher) {
                getOrders().flatMap {
                    getOrderItems(it).getValue()
                }.let {
                    if (loadCombined) combineItems(it.getValue()) else it
                }.applyEach { item ->
                    item.apply {
                        getDeliveryItemDiscount(item.id).getValue()?.let {
                            price = it.price
                            discount = it.discount
                        }
                    }
                }.toList()
            }.asFlow(Dispatchers.IO)
        }
    }

    private fun combineItems(list: Iterable<DeliveryItemDomain>?): ResultWrapper.Success<List<DeliveryItemDomain>> {
        return (list ?: emptyList()).apply {
            forEach { item ->
                item.count = filter { item.name == it.name }.sumOf { it.count }
            }
        }.distinctBy {
            it.name
        }.let {
            ResultWrapper.Success(it)
        }
    }
}