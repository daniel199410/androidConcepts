package co.com.flypass.flypassNative.presentation

import android.content.Context
import android.provider.Settings.Global.getString
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import co.com.flypass.flypassNative.core.AppConstants
import co.com.flypass.flypassNative.core.Resource
import co.com.flypass.flypassNative.core.SessionManager
import co.com.flypass.flypassNative.data.model.TokenResponse
import co.com.flypass.flypassNative.repository.SecurityRepository
import co.com.flypass.flypassNative.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class LoginViewModel(
        private val repository: UserRepository,
        private val securityRepository: SecurityRepository): ViewModel() {
    fun userExists(document: String) = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repository.userExists(document)))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

    fun getToken(document: String, password: String) = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(securityRepository.getToken(document, password)))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

    fun saveSessionState(data: TokenResponse, activity: FragmentActivity?) {
        val sharedPref = activity?.getSharedPreferences(AppConstants.REFERENCE_FILE_KEY, Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putString(AppConstants.ACCESS_TOKEN, data.accessToken)
            putLong(AppConstants.SECURE_USER_ID, data.principal.secUserId)
            commit()
        }
    }
}

class LoginViewModelFactory(private val repo: UserRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(UserRepository::class.java, SecurityRepository::class.java).newInstance(repo, SecurityRepository())
    }
}

class PasswordViewModelFactory(private val repo: SecurityRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(UserRepository::class.java, SecurityRepository::class.java).newInstance(UserRepository(), repo)
    }
}