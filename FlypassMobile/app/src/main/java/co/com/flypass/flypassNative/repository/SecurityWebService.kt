package co.com.flypass.flypassNative.repository

import co.com.flypass.flypassNative.core.AppConstants
import co.com.flypass.flypassNative.data.model.Model
import co.com.flypass.flypassNative.data.model.TokenResponse
import co.com.flypass.flypassNative.ui.login.Password
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface SecurityWebService {
    @FormUrlEncoded
    @POST("oauth/token")
    @Headers("Authorization: Basic Zmx5cGFzczpSbXg1ZEdWamFDNHlNREUz")
    suspend fun getToken(@Field("username") username: String,
                         @Field("password") password: String,
                         @Field("client_id") clientId: String = AppConstants.CLIENT_ID,
                         @Field("grant_type") grantType: String = AppConstants.GRANT_TYPE,
                         @Field("Tipo MIME") MIMEType: String = AppConstants.MIME_TYPE): TokenResponse
}

object SecurityClient {
    val SECURITY_WEB_SERVICE: SecurityWebService by lazy {
        Retrofit.Builder()
            .baseUrl("${AppConstants.BASE_URL}${AppConstants.SECURITY_SERVICE}")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(SecurityWebService::class.java)
    }
}

