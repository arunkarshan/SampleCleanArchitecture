package com.example.data_remote.models

import com.google.gson.annotations.SerializedName

class OrderResponse(
  @SerializedName("items") val items: List<DeliveryItem>
)
