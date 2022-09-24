package com.example.data_remote.models

import com.google.gson.annotations.SerializedName

data class DeliveryItem(
  @SerializedName("id") val id: Long,
  @SerializedName("name") val name: String,
  @SerializedName("imageUrl") val imageUrl: String,
  @SerializedName("count") val count: Int
)
