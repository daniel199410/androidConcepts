package co.com.flypass.flypassNative.repository

import co.com.flypass.flypassNative.core.AppConstants
import co.com.flypass.flypassNative.data.model.Model
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface UserWebService {
    @GET("loginExists/{document}")
    suspend fun userExists(@Path("document") document: String): Model<Int>
}

object UserClient {
    val USER_WEB_SERVICE: UserWebService by lazy {
        Retrofit.Builder()
            .baseUrl("${AppConstants.BASE_URL}${AppConstants.USER_SERVICE}")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(UserWebService::class.java)
    }
}

