package com.zvonimirplivelic.pawgsearch.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PAWGSearchViewModelFactory(val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PAWGSearchViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PAWGSearchViewModel(app) as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }
}