package com.example.data_local.datasource

import com.example.sampleaction.repository.datasource.local.OrdersLocalSource
import com.example.domain.models.DeliveryItemDomain
import org.koin.core.annotation.Factory


/*********************************************************
 * Class   :  LoginDataSourceImpl
 * Author  :  Arun Nair
 * Created :  16/09/2022
 *******************************************************
 * Purpose :
 *******************************************************
 * Rework Details:
 * 1) {Author} :  {Date} : {Details}
 *********************************************************/
@Factory
class OrderDataSourceImpl() : OrdersLocalSource {
    override suspend fun getDeliveryItems(): List<DeliveryItemDomain> {
        return emptyList()
    }

    override suspend fun getDeliveryItemsWithDiscount(): List<DeliveryItemDomain> {
        return emptyList()
    }

    override suspend fun saveDeliveryItems(list: List<DeliveryItemDomain>) {

    }

    override suspend fun saveDeliveryItemsWithDiscount(list: List<DeliveryItemDomain>) {
    }

}