package co.com.flypass.flypassNative.repository

import co.com.flypass.flypassNative.data.model.Model

class UserRepository {
    private val userWebService: UserWebService = UserClient.USER_WEB_SERVICE

    suspend fun userExists(document: String): Model<Int> {
        return userWebService.userExists(document)
    }
}