package com.ind.demoapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.ind.demoapp.databinding.ActivityMainBinding
import com.ind.demoapp.view.MainFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    var fragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        initControls()
    }


    private fun initControls() {

        replaceFragment()
    }

    fun replaceFragment(){
        supportFragmentManager.beginTransaction().replace(R.id.container, MainFragment()).commit()
    }


    fun replaceBundleFragment(fragment: Fragment, bundle: Bundle?) {
        this.fragment = fragment
        val ft = supportFragmentManager.beginTransaction()
        fragment.arguments = bundle
        ft.setCustomAnimations(R.anim.trans_left_in, R.anim.trans_left_out)
        ft.replace(R.id.container, fragment)
        ft.addToBackStack(null)
        ft.commit()
    }


}