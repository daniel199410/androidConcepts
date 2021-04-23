package co.com.flypass.flypassNative.data.model

import com.google.gson.annotations.SerializedName

data class Vehicle (
    val licensePlate: String,
    val carConfiguration: CarConfiguration,
    val brand: Brand,
    val brandSeries: BrandSeries,
    val color: Color,
    val titular: String,
    val status: GeneralEnum,
)

data class BrandSeries (
    @SerializedName("serieDescription")
    val seriesDescription: String
)

data class Brand (
    val brandDescription: String
)

data class Color (
    val color: String
)