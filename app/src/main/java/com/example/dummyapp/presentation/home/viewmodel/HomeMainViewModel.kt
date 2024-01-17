package com.example.dummyapp.presentation.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.dummyapp.data.remote.dto.HomeMainResponse
import com.example.dummyapp.data.remote.dto.HomeScrollResponse
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

    private val homeScrollData = MutableLiveData<HomeScrollResponse>()
    val homeScrollListData: LiveData<HomeScrollResponse>
        get() = homeScrollData

    val scrollData=repository.getHomeScrollDetails().cachedIn(viewModelScope)

    init {
        fetchUsers()
//        fetchScrollUsers()
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

//    private fun fetchScrollUsers() {
//        viewModelScope.launch {
//            repository.getHomeScrollDetails()
//                .flowOn(Dispatchers.IO)
//                .catch { e ->
//                    // handle exception
//                    Log.e(TAG, "$e ", )
//                }
//                .collect {values->
//                    // list of users from the network
//                    Log.e(TAG, "fetchScrollUsers: $values")
//                    homeScrollData.value=values
//                }
//        }
//    }

}