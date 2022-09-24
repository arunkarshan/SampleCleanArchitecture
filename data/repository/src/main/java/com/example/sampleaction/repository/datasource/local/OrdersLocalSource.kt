package com.example.sampleaction.repository.datasource.local

import com.example.domain.models.DeliveryItemDomain


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

interface OrdersLocalSource {
    suspend fun getDeliveryItems(): List<DeliveryItemDomain>
    suspend fun getDeliveryItemsWithDiscount(): List<DeliveryItemDomain>
    suspend fun saveDeliveryItems(list: List<DeliveryItemDomain>)
    suspend fun saveDeliveryItemsWithDiscount(list: List<DeliveryItemDomain>)
}