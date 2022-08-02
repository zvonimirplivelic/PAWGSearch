package com.zvonimirplivelic.rawgsearch.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class RAWGSearchViewModelFactory(val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RAWGSearchViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RAWGSearchViewModel(app) as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }
}