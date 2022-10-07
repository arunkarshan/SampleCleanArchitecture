package com.example.data_remote.models

import com.google.gson.annotations.SerializedName

class OrdersResponse(
  @SerializedName("orders") val orders: List<Long>
)
