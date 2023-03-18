package com.ind.demoapp.vmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ind.demoapp.model.ItemInfoResponse
import com.ind.demoapp.repository.RetrofitRepository

class RetroViewModel(retrofitRepository: RetrofitRepository): ViewModel() {

    lateinit var retrofitRepository:RetrofitRepository
    var infoLiveData: LiveData<List<ItemInfoResponse>> = MutableLiveData()

    init {
        this.retrofitRepository  = retrofitRepository
        fetchInfoFromRepository()
        }

    fun fetchInfoFromRepository(){
        infoLiveData =  retrofitRepository.fetchInfoList()
    }


}