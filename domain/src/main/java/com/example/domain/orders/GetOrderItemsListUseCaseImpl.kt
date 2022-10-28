package com.example.domain.orders

import com.example.domain.base.ResultWrapper
import com.example.domain.models.DeliveryItemDomain
import com.example.domain.utils.flatMap
import com.example.domain.utils.safeCallFlow
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Named


/*********************************************************
 * Class   :  GetOrderDetailsUseCaseImpl
 * Author  :  Arun Nair
 * Created :  16/09/2022
 *******************************************************
 * Purpose :
 *******************************************************
 * Rework Details:
 * 1) {Author} :  {Date} : {Details}
 *********************************************************/
@Factory
internal class GetOrderItemsListUseCaseImpl(
    private val orderRepository: OrderRepository,
    @Named("IODispatcher") private val dispatcher: CoroutineDispatcher
) : GetOrderItemsListUseCase {
    override suspend fun invoke(
        params: GetOrderItemsListUseCase.Params
    ): Flow<ResultWrapper<List<DeliveryItemDomain>>> {
        return getItemsFromServer(params.loadCombined)
    }

    suspend fun getItemsFromServer(loadCombined: Boolean): Flow<ResultWrapper<List<DeliveryItemDomain>>> {
        return safeCallFlow(dispatcher) {
            orderRepository.run {
                getOrders().flatMap {
                    getOrderItems(it)
                }.map {
                    if (loadCombined) combineItems(it) else it
                }.map { items ->
                    items.map {
                        it.apply {
                            getDeliveryItemDiscount(id).map { dscnt ->
                                price = dscnt.price
                                discount = dscnt.discount
                            }
                        }
                    }
                }
            }
        }
    }

    private fun combineItems(list: Iterable<DeliveryItemDomain>?): List<DeliveryItemDomain> {
        return (list ?: emptyList()).apply {
            forEach { item ->
                item.count = filter { item.name == it.name }.sumOf { it.count }
            }
        }.distinctBy {
            it.name
        }
    }
}