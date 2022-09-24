package com.example.sampleaction.models.vo

import android.os.Parcelable
import com.bumptech.glide.load.resource.bitmap.VideoDecoder.parcel
import kotlinx.parcelize.Parcelize

@Parcelize
data class DeliveryItemVO(
  val id: Long,
  val name: String,
  val imageUrl: String,
  val count: Int,
  val price: String,
  val discount: String
): Parcelable
