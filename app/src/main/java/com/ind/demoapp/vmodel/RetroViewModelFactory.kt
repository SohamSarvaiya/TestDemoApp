package com.ind.demoapp.vmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ind.demoapp.network.APIComponent
import com.ind.demoapp.network.APIModule
import com.ind.demoapp.network.DaggerAPIComponent
import com.ind.demoapp.repository.APIURL
import com.ind.demoapp.repository.RetrofitRepository
import dagger.Module
import dagger.android.DaggerApplication
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject


class RetroViewModelFactory : ViewModelProvider.Factory {
    lateinit var apiComponent: APIComponent
    @Inject
    lateinit var retrofitRepository: RetrofitRepository

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        initDaggerComponent()
       var apiComponent :APIComponent =  apiComponent
        apiComponent.inject(this)
        if (modelClass.isAssignableFrom(RetroViewModel::class.java)) {
            return RetroViewModel(retrofitRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

    fun initDaggerComponent(){
        apiComponent = DaggerAPIComponent
            .builder()
            .aPIModule(APIModule(APIURL.BASE_URL))
            .build()

        apiComponent.inject(this)
    }
}