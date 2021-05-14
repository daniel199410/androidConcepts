package co.com.flypass.flypassNative.repository

import android.content.Context
import co.com.flypass.flypassNative.data.model.Model
import co.com.flypass.flypassNative.data.model.PageableResponse
import co.com.flypass.flypassNative.data.model.Vehicle

class VehicleRepository(context: Context) {
    private val apiClient = ApiClient()
    private val vehicleWebService: VehicleWebService = apiClient.getApiService(context)

    suspend fun getVehicles(licensePlate: String, page: Int): Model<PageableResponse<Vehicle>> {
        return vehicleWebService.getVehicles(licensePlate, page)
    }

    suspend fun getVehicleMessages(): Model<String> {
        return vehicleWebService.getVehicleMessages()
    }
}