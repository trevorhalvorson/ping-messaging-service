package model

import com.google.gson.annotations.SerializedName

data class PingRequest constructor(
        @SerializedName("phoneNumber") val phoneNumber: String,
        @SerializedName("message") val message: String
)