package com.mizzenmast.agroai.repository

import android.content.Context
import android.net.Uri
import com.mizzenmast.agroai.data.PlantHealthResult
import com.mizzenmast.agroai.network.ApiClient
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream

class PlantHealthRepository {

    suspend fun analyzePlant(context: Context, imageUri: Uri): Result<PlantHealthResult> {
        return try {
            val imageFile = uriToFile(context, imageUri)

            val requestFile = imageFile
                .asRequestBody("image/*".toMediaTypeOrNull())

            // Use the correct field name "file"
            val imagePart = MultipartBody.Part
                .createFormData("file", imageFile.name, requestFile)

            val response = ApiClient.api.predictPlantHealth(imagePart)

            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Prediction failed: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


    private fun uriToFile(context: Context, uri: Uri): File {
        val inputStream = context.contentResolver.openInputStream(uri)
        val file = File(context.cacheDir, "temp_image_${System.currentTimeMillis()}.jpg")

        inputStream?.use { input ->
            FileOutputStream(file).use { output ->
                input.copyTo(output)
            }
        }

        return file
    }
}
