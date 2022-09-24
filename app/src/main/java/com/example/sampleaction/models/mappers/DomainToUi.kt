package com.example.data_remote.models.mappers

import com.example.domain.models.DeliveryItemDomain
import com.example.sampleaction.models.vo.DeliveryItemVO


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

fun DeliveryItemDomain.toUi()= DeliveryItemVO(
    id = id,
    name = name,
    imageUrl = imageUrl,
    count = count,
    price = price,
    discount = discount
)