package co.com.flypass.flypassNative.data.model

import com.google.gson.annotations.SerializedName

data class Principal(
    val userId: Long,
    val secUserId: Long,
    val personId: Long,
    val sessionId: Long,
    val name: String,
    val device: String,
    val ipAddress: String,
    @SerializedName("sesionStart")
    val sessionStart: Long,
    val secUserSonId: Long
)
