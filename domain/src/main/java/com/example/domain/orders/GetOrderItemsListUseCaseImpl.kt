package com.example.domain.orders

import com.example.domain.base.BaseFlowUseCase
import com.example.domain.base.ResultWrapper
import com.example.domain.models.DeliveryItemDomain
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory


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
    private val orderRepository: OrderRepository
) : GetOrderItemsListUseCase {
    override suspend fun invoke(
        params: GetOrderItemsListUseCase.Params
    ): Flow<ResultWrapper<List<DeliveryItemDomain>>> {
        return orderRepository.getDeliveryItems(params.loadCombined)
    }
}