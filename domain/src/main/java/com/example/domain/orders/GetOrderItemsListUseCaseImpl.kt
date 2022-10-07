package com.example.domain.orders

import com.example.domain.base.BaseFlowUseCase
import com.example.domain.base.ResultWrapper
import com.example.domain.base.flatMap
import com.example.domain.models.DeliveryItemDomain
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMap
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
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
//        orderRepository.run {
//            withContext(dispatcher) {
//                getOrders().flatMap {
//                    getOrderItems(it).getValue()
//                }.let {
//                    if (loadCombined) combineItems(it.getValue()) else it
//                }.applyEach { item ->
//                    item.apply {
//                        getDeliveryItemDiscount(item.id).getValue()?.let {
//                            price = it.price
//                            discount = it.discount
//                        }
//                    }
//                }.toList()
//            }.asFlow(Dispatchers.IO)
//        }
//        return orderRepository.getDeliveryItems(params.loadCombined)
        return flow {  }
    }
}