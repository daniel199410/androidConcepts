package co.com.flypass.flypassNative.data.model

data class PageableResponse<T> (
    val content: MutableList<T> = mutableListOf(),
    val first: Boolean,
    val last: Boolean,
    val numberOfElements: Long,
    val size: Int,
    val totalElements: Long,
    val totalPages: Int,
)