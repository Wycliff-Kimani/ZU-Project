package com.mizzenmast.agroai.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.State
import com.mizzenmast.agroai.data.PlantHealthResult

class PlantHealthViewModel : ViewModel() {

    private val _result = mutableStateOf<PlantHealthResult?>(null)
    val result: State<PlantHealthResult?> = _result

    fun setResult(data: PlantHealthResult) {
        _result.value = data
    }

    fun clearResult() {
        _result.value = null
    }
}
