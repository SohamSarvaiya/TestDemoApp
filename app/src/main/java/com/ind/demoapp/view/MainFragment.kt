package com.ind.demoapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ind.demoapp.R
import com.ind.demoapp.databinding.FragmentMainBinding
import com.ind.demoapp.vmodel.RetroViewModel
import com.ind.demoapp.vmodel.RetroViewModelFactory


class MainFragment : Fragment() {
    private var  binding: FragmentMainBinding?=null
    lateinit var retroViewModel: RetroViewModel
    private  var listAdapter:InfoListAdapter?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_main, container, false)
        binding!!.progressCircular.visibility = View.VISIBLE
        initViewModel()

        initAdapter()
        setAdapter()
        fetchRetroInfo()
        return binding!!.root
    }

    fun  initViewModel(){
        val retroViewModelFactory = RetroViewModelFactory()
        retroViewModel = ViewModelProvider(this,retroViewModelFactory).get(RetroViewModel::class.java)

    }

    fun fetchRetroInfo(){
        retroViewModel.infoLiveData.observe(viewLifecycleOwner) { t ->
            t?.apply {
                listAdapter?.setAdapterList(t)
                binding!!.progressCircular.visibility = View.GONE

            }
        }
    }

    private fun setAdapter(){
        binding!!.itemListRv.apply {
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
            adapter = listAdapter

        }
    }
    private fun initAdapter(){
        listAdapter = InfoListAdapter(this@MainFragment.requireActivity())
    }

}