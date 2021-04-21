package co.com.flypass.flypassNative.data.model

data class Model<T> (
    val code: Int,
    val message: String,
    val body: T
)