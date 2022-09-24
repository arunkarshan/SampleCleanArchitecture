package com.example.data_remote.models.mappers

import com.example.data_remote.models.DeliveryItem
import com.example.data_remote.models.DiscountResponse
import com.example.domain.models.DeliveryItemDomain
import com.example.domain.models.DiscountDomain


/*********************************************************
 * Class   :  DataToDomain
 * Author  :  Arun Nair
 * Created :  16/09/2022
 *******************************************************
 * Purpose :
 *******************************************************
 * Rework Details:
 * 1) {Author} :  {Date} : {Details}
 *********************************************************/

fun DeliveryItem.toDomain()= DeliveryItemDomain(
    id = id,
    name = name,
    imageUrl = imageUrl,
    count = count,
    price = "",
    discount = ""
)

fun DiscountResponse.toDomain() = DiscountDomain(
    id = id, price = price, discount = "${discount * 100}%"
)
