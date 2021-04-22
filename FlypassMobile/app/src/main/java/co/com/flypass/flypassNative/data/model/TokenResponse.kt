package co.com.flypass.flypassNative.data.model

import com.google.gson.annotations.SerializedName

data class TokenResponse(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("refresh_token")
    val refreshToken: String,
    @SerializedName("expires_in")
    val expiresIn: Long,
    val scope: String,
    val jti: String,
    val principal: Principal
)
