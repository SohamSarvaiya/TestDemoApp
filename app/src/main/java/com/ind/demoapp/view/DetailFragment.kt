package com.ind.demoapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.ind.demoapp.R
import com.ind.demoapp.databinding.FragmentDetailBinding
import com.squareup.picasso.Picasso


class DetailFragment : Fragment(), View.OnClickListener {
private var binding:FragmentDetailBinding?=null
    private var id = ""
    private var title = ""
    private var url = ""
    private var image = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_detail, container, false)

        //pass data
        id = arguments!!.getString("id").toString()
        title = arguments!!.getString("title").toString()
        url = arguments!!.getString("url").toString()
        image = arguments!!.getString("image").toString()

        initControls()
        return binding!!.root
    }


    private fun initControls(){

        binding!!.titleTv.text = title
        binding!!.urlTv.text = url

        Picasso.get().load(image).into(binding!!.urlImage)

        binding!!.backIv.setOnClickListener(this)

    }


    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.backIv ->{
                activity!!.supportFragmentManager.popBackStack()
            }
        }
    }
}