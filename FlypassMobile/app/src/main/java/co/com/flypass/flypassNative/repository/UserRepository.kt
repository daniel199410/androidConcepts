package co.com.flypass.flypassNative.repository

import co.com.flypass.flypassNative.data.model.Model

class UserRepository {
    private val webService: WebService = RetroFitClient.webService

    suspend fun userExists(document: String): Model<Int> {
        return webService.userExists(document)
    }
}