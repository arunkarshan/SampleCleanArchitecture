package com.example.domain.models

data class DeliveryItemDomain(
  val id: Long,
  val name: String,
  val imageUrl: String,
  var count: Int,
  var price: String,
  var discount: String
)
