package co.com.flypass.flypassNative.core

object Validators {
    fun isNullOrEmpty(value: String?): Boolean {
        return value.isNullOrEmpty()
    }

    fun matches(value: String, regex: Regex): Boolean {
        return regex.matches(value)
    }
}