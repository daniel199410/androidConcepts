package co.com.flypass.flypassNative.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import co.com.flypass.flypassNative.core.Resource
import co.com.flypass.flypassNative.repository.VehicleRepository
import kotlinx.coroutines.Dispatchers

class VehicleViewModel(private val vehicleRepository: VehicleRepository) : ViewModel() {
    private var page = 0
    private val totalPages = 0

    fun getPage(): Int {
        return page
    }

    fun increasePage() {
        page += 1
    }

    fun getVehicles(licensePlate: String, page: Int) = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(vehicleRepository.getVehicles(licensePlate, page)))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }
}

class VehicleModelFactory(private val repo: VehicleRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(VehicleRepository::class.java).newInstance(repo)
    }
}