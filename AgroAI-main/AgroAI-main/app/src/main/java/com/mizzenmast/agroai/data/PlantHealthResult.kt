package com.mizzenmast.agroai.data

data class PlantHealthResult(
    val className: String,
    val plant: String,
    val status: String,
    val confidence: Float,
    val symptoms: List<String>? = null,
    val recommendations: List<String>? = null
)