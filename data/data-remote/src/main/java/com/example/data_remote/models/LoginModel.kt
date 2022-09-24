package com.example.data_remote.models

import com.google.gson.annotations.SerializedName

data class LoginModel(
  @SerializedName("email") val email: String?,
  @SerializedName("pass") val password: String? = ""
)
