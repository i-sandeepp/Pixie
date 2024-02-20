package com.sandeep.pixie.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sandeep.pixie.model.PixieResponse
import com.sandeep.pixie.network.ApiResult
import com.sandeep.pixie.repository.PixieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Sandeep Pramanik on 20 February,2024.
 */
@HiltViewModel
class MainViewModel @Inject constructor(private val repository: PixieRepository): ViewModel() {

    private var _pixieResponse = MutableLiveData<ApiResult<PixieResponse>>()
    val pixieResponse: LiveData<ApiResult<PixieResponse>>
        get() = _pixieResponse

    fun getList(page: Int, limit: Int) {
        viewModelScope.launch {
            repository.getList(page, limit).collect {
                _pixieResponse.postValue(it)
            }
        }
    }
}