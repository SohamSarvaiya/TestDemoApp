package com.ind.demoapp

import android.app.Application
import android.content.Context
import com.ind.demoapp.network.APIComponent
import com.ind.demoapp.network.APIModule
import com.ind.demoapp.network.DaggerAPIComponent
import com.ind.demoapp.repository.APIURL

class MyApplication : Application() {


    companion object {
        var ctx: Context? = null
        lateinit var apiComponent: APIComponent
    }
    override fun onCreate() {
        super.onCreate()
        ctx = applicationContext
      //  apiComponent = initDaggerComponent()

    }

    fun getMyComponent(): APIComponent {
        return apiComponent
    }

    fun initDaggerComponent():APIComponent{
        apiComponent =   DaggerAPIComponent
            .builder()
            .aPIModule(APIModule(APIURL.BASE_URL))
            .build()
        return  apiComponent

    }
}