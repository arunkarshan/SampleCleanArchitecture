package com.example.data_remote.models.mappers

import com.example.data_remote.models.DeliveryItem
import com.example.data_remote.models.DiscountResponse
import com.example.data_remote.models.LoginModel
import com.example.sampleaction.repository.model.*


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


fun DeliveryItem.toData()= DataDeliveryItem(
    id = id,
    name = name,
    imageUrl = imageUrl,
    count = count
)

fun DiscountResponse.toData() = DataDiscountResponse(
    id = id, price = price, discount = discount
)

fun LoginModel.toData() = DataLoginModel(
    email = email, password = password
)
