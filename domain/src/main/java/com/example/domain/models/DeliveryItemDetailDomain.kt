package com.example.domain.models

data class DeliveryItemDetailDomain(
  val id: Long,
  val name: String,
  val imageUrl: String,
  var count: Int
)
