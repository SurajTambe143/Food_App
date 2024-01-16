package com.example.dummyapp.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dummyapp.domain.repository.HomeMainRepository

class HomeMainViewModelFactory(private val repository: HomeMainRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeMainViewModel(repository) as T
    }
}