package com.example.data_remote.network

import com.example.data_remote.models.DiscountResponse
import com.example.data_remote.models.OrderResponse
import com.example.data_remote.models.OrdersResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface OrderApi {
    @GET("https://boiling-dusk-12902.herokuapp.com/orders")
    suspend fun fetchOrders(): OrdersResponse

    @GET("https://boiling-dusk-12902.herokuapp.com/order/{order_id}")
    suspend fun fetchOrderById(@Path("order_id") id: Long): OrderResponse

    @GET("https://boiling-dusk-12902.herokuapp.com/discount/{item_id}")
    suspend fun discount(@Path("item_id") id: Long): DiscountResponse
}