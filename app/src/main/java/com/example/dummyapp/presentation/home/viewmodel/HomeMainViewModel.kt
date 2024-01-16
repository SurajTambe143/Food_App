package com.example.dummyapp.presentation.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dummyapp.data.remote.dto.HomeMainResponse
import com.example.dummyapp.domain.repository.HomeMainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class HomeMainViewModel(val repository: HomeMainRepository):ViewModel() {

    private  val TAG = "HomeMainViewModel"
    private val liveData = MutableLiveData<HomeMainResponse>()

    val listResponse: LiveData<HomeMainResponse>
        get() = liveData

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            repository.getHomeMainDetails()
                .flowOn(Dispatchers.IO)
                .catch { e ->
                    // handle exception
                    Log.e(TAG, "$e ", )
                }
                .collect {values->
                    // list of users from the network
                    Log.e(TAG, "fetchUsers: $values")
                    liveData.value=values
                }
        }
    }

}