package co.com.flypass.flypassNative.repository

import co.com.flypass.flypassNative.core.AppConstants
import co.com.flypass.flypassNative.data.model.Model
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface WebService {
    @GET("loginExists/{document}")
    suspend fun userExists(@Path("document") document: String): Model<Int>
}

object RetroFitClient {
    val webService: WebService by lazy {
        Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(WebService::class.java)
    }
}

