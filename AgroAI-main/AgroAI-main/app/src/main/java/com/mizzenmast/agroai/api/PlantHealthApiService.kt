package com.mizzenmast.agroai.api

// API Service for Plant Health Analysis
import com.mizzenmast.agroai.data.PlantHealthResult
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {
    @Multipart
    @POST("/predict")
    suspend fun predictPlantHealth(
        @Part file: MultipartBody.Part
    ): Response<PlantHealthResult>
}