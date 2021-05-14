package co.com.flypass.flypassNative.core

object RegexExpressions {
    val LICENSE_PLATE = Regex("/(^[A-Z]{3}[0-9]{3})|(^[A-Z][0-9]{4}\$)|(^[A-Z]{2}[0-9]{3}\$)|(^[A-Z]{2}[0-9]{4})/")
}