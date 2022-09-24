package com.example.domain.orders

import com.example.domain.base.BaseFlowResultWrapperUseCase
import com.example.domain.base.BaseFlowUseCase
import com.example.domain.base.ResultWrapper
import com.example.domain.models.DeliveryItemDomain
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory


/*********************************************************
 * Class   :  GetOrderDetailsUseCase
 * Author  :  Arun Nair
 * Created :  16/09/2022
 *******************************************************
 * Purpose :
 *******************************************************
 * Rework Details:
 * 1) {Author} :  {Date} : {Details}
 *********************************************************/
interface GetOrderItemsListUseCase : BaseFlowResultWrapperUseCase<GetOrderItemsListUseCase.Params, List<DeliveryItemDomain>> {
    data class Params(
        val loadCombined: Boolean
    )
}