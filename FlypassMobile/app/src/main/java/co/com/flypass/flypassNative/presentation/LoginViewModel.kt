package co.com.flypass.flypassNative.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import co.com.flypass.flypassNative.core.Resource
import co.com.flypass.flypassNative.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class LoginViewModel(private val repository: UserRepository): ViewModel() {
    fun userExists(document: String) = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repository.userExists(document)))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }
}

class LoginViewModelFactory(private val repo: UserRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(UserRepository::class.java).newInstance(repo)
    }
}