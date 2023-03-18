package com.ind.demoapp.repository


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ind.demoapp.model.ItemInfoResponse
import com.ind.demoapp.network.APIComponent
import com.ind.demoapp.network.APIModule
import com.ind.demoapp.network.DaggerAPIComponent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class RetrofitRepository {
    var apiComponent: APIComponent
    var infoMutableList: MutableLiveData<List<ItemInfoResponse>> = MutableLiveData()
    @Inject
    lateinit var retrofit: Retrofit
    init {
       apiComponent =   DaggerAPIComponent
            .builder()
            .aPIModule(APIModule(APIURL.BASE_URL))
            .build()
        apiComponent.inject(this)

        var apiComponent :APIComponent =  apiComponent
        apiComponent.inject(this)
    }


    fun fetchInfoList(): LiveData<List<ItemInfoResponse>> {
         var apiService:APIService = retrofit.create(APIService::class.java)
         var listInfo : Call<List<ItemInfoResponse>> =  apiService.makeRequest()
         listInfo.enqueue(object :Callback<List<ItemInfoResponse>>{
            override fun onFailure(call: Call<List<ItemInfoResponse>>, t: Throwable) {
             Log.d("RetroRepository","Failed:::"+t.message)
            }

            override fun onResponse(call: Call<List<ItemInfoResponse>>, response: Response<List<ItemInfoResponse>>) {
                var infoList = response.body()
                infoMutableList.value = infoList

            }
        })

         return  infoMutableList

    }


}