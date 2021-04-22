package co.com.flypass.flypassNative.repository

import co.com.flypass.flypassNative.data.model.TokenResponse

class SecurityRepository {
    private val securityWebService: SecurityWebService = SecurityClient.SECURITY_WEB_SERVICE

    suspend fun getToken(document: String, password: String): TokenResponse {
        return securityWebService.getToken(document, password)
    }
}