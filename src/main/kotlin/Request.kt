import com.google.gson.annotations.SerializedName

data class Request(
        @SerializedName("title") val title: String,
        @SerializedName("copy") val copy: String,
        @SerializedName("message") val message: String,
        @SerializedName("logo") val logo: String,
        @SerializedName("url") val url: String,
        @SerializedName("email") val email: String
)