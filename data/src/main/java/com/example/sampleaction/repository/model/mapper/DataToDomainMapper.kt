package com.example.sampleaction.repository.model.mapper

import com.example.domain.base.ErrorType
import com.example.domain.models.DeliveryItemDomain
import com.example.domain.models.DiscountDomain
import com.example.sampleaction.repository.model.DataDeliveryItem
import com.example.sampleaction.repository.model.DataDiscountResponse
import com.example.sampleaction.repository.model.DataErrorType


/*********************************************************
 * Class   :  DataToDomainMapper
 * Author  :  Arun Nair
 * Created :  07/10/2022
 *******************************************************
 * Purpose :
 *******************************************************
 * Rework Details:
 * 1) {Author} :  {Date} : {Details}
 *********************************************************/


fun DataDeliveryItem.toDomain()= DeliveryItemDomain(
    id = id,
    name = name,
    imageUrl = imageUrl,
    count = count,
    price = "",
    discount = ""
)

fun DataDiscountResponse.toDomain() = DiscountDomain(
    id = id, price = price, discount = "${discount * 100}%"
)


fun DataErrorType.toDomain(): ErrorType {
    return when(this){
        is DataErrorType.GenericError -> ErrorType.GenericError
        is DataErrorType.NetworkError -> ErrorType.NetworkError
    }
}
