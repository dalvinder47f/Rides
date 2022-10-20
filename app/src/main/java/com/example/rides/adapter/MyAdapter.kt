package com.example.rides.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rides.R
import com.example.rides.databinding.AdapterVehicleListBinding
import com.example.rides.model.ResponseItem


internal class MyAdapter(
    private val mList: List<ResponseItem>,
    val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var biding: AdapterVehicleListBinding =
            AdapterVehicleListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(biding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(position, holder.itemView.context)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    internal inner class ViewHolder(itemView: AdapterVehicleListBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        var view: AdapterVehicleListBinding = itemView
        fun bindItems(position: Int, context: Context) {
            view.modelTextview.text = context.getString(R.string.make_and_model).plus(" : "+mList[position].makeAndModel)
            view.vinTextview.text = context.getString(R.string.vin).plus(" : "+mList[position].vin)
            view.root.setOnClickListener {
                itemClickListener.onItemClick(mList[position])
            }
        }
    }

    interface OnItemClickListener {
        //handle click on vehicle list
        fun onItemClick(responseItem: ResponseItem)
    }
}