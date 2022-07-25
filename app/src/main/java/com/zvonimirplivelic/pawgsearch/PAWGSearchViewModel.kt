package com.zvonimirplivelic.pawgsearch

import android.app.Application
import androidx.lifecycle.AndroidViewModel


class PAWGSearchViewModel(
    val app: Application,
    private val repository: PAWGSearchRepository
) : AndroidViewModel(app) {

}