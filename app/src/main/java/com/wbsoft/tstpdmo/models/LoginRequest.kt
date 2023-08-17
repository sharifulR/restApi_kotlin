package com.wbsoft.tstpdmo.models


import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("mobile")
    val mobile: String,
    @SerializedName("password")
    val password: String
)