package com.ind.demoapp.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ind.demoapp.BR
import com.ind.demoapp.MainActivity
import com.ind.demoapp.R
import com.ind.demoapp.databinding.RowItemlistBinding
import com.ind.demoapp.model.ItemInfoResponse
import com.squareup.picasso.Picasso


class InfoListAdapter(var context: Context) : RecyclerView.Adapter<InfoListAdapter.ViewHolder>() {
    private  var list: List<ItemInfoResponse> = emptyList<ItemInfoResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding: RowItemlistBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.row_itemlist, parent, false)
        return InfoListAdapter.ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("adapter","data:::"+list.get(position).title)

        //load image url
        loadImage(holder.binding.image, list[position].thumbnailUrl)

        //for detail page
        holder.binding.clickInfoLl.setOnClickListener{
            val bundle = Bundle()
            bundle.putString("id", list[position].id)
            bundle.putString("image", list[position].url)
            bundle.putString("url", list[position].thumbnailUrl)
            bundle.putString("title", list[position].title)
            (context as MainActivity).replaceBundleFragment(DetailFragment(),bundle)

        }

        holder.bind(list[position])
    }

    fun setAdapterList(list: List<ItemInfoResponse>){
       this.list = list
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int = list.size

    class ViewHolder(val binding: RowItemlistBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Any) {
            binding.setVariable(BR.infoModel, data)
            binding.executePendingBindings()
        }
    }

    @BindingAdapter("bind:imageUrl")
    fun loadImage(view: ImageView, imageUrl: String?) {
        Picasso.get().load(imageUrl).into(view)
    }
}
