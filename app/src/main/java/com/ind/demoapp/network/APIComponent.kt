package com.ind.demoapp.network

import com.ind.demoapp.AppModule
import com.ind.demoapp.vmodel.RetroViewModel
import com.ind.demoapp.vmodel.RetroViewModelFactory
import com.ind.demoapp.repository.RetrofitRepository
import com.ind.demoapp.view.MainFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,APIModule::class])
interface APIComponent {
    fun inject(retrofitRepository: RetrofitRepository)
    fun inject(retroViewModel: RetroViewModel)
    fun inject(retroFragment: MainFragment)
    fun inject(retroViewModelFactory: RetroViewModelFactory)
}