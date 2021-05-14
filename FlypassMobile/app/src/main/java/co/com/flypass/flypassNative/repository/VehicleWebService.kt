package co.com.flypass.flypassNative.repository

import android.content.Context
import co.com.flypass.flypassNative.core.AppConstants
import co.com.flypass.flypassNative.core.AuthInterceptor
import co.com.flypass.flypassNative.data.model.Model
import co.com.flypass.flypassNative.data.model.PageableResponse
import co.com.flypass.flypassNative.data.model.Vehicle
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface VehicleWebService {
    @GET("list")
    suspend fun getVehicles(
            @Query("licensePlate") licensePlate: String = "",
            @Query("page") page: Int = 1,
            @Query("vehicleType") vehicleType: Int? = null
    ): Model<PageableResponse<Vehicle>>

    @GET("getVehicleStepOneMessage")
    suspend fun getVehicleMessages(): Model<String>
}

class ApiClient {
    private lateinit var apiService: VehicleWebService

    fun getApiService(context: Context): VehicleWebService {
        if (!::apiService.isInitialized) {
            val retrofit = Retrofit.Builder()
                    .baseUrl("${AppConstants.BASE_URL}${AppConstants.VEHICLE_SERVICE}")
                    .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                    .client(okHttpClient(context))
                    .build()
            apiService = retrofit.create(VehicleWebService::class.java)
        }
        return apiService
    }

    private fun okHttpClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(AuthInterceptor(context))
                .build()
    }
}