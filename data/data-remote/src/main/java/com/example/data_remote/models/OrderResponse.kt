package com.example.data_remote.models

import com.example.data_remote.models.DeliveryItem
import com.google.gson.annotations.SerializedName

class OrderResponse(
  @SerializedName("items") val items: List<DeliveryItem>
)
