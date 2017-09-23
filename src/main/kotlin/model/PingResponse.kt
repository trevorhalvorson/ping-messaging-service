package model

import com.google.gson.annotations.SerializedName

data class PingResponse constructor(
        @SerializedName("success") val success: Boolean,
        @SerializedName("message") val message: String
)