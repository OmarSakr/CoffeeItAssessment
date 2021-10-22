package com.codevalley.coffeeitassessment.main.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codevalley.coffeeitassessment.databinding.ItemExtrasBinding
import com.codevalley.coffeeitassessment.models.coffeeMachineModel.CoffeeMachineModel
import java.util.ArrayList

class CoffeeExtrasAdapter(private val context: Context) :
    RecyclerView.Adapter<CoffeeExtrasAdapter.ViewHolder>() {

    private var itemList: ArrayList<CoffeeMachineModel>? = null
    private var layoutInflater: LayoutInflater? = null
    private var selectedPosition = -1


    private lateinit var extrasSubSelectionAdapter: ExtrasSubSelectionAdapter

    init {
        itemList = ArrayList()
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemExtrasBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.binding.tvName.text = itemList!![0].extras[position].name
        defineExtrasSubSelection(holder)

        holder.itemView.setOnClickListener {
            selectedPosition = position
            notifyDataSetChanged()
        }
        if (selectedPosition != position) {
            holder.binding.view.visibility = GONE
            holder.binding.rvSubSelections.visibility = GONE

        } else if (selectedPosition == position) {
            holder.binding.view.visibility = VISIBLE
            holder.binding.rvSubSelections.visibility = VISIBLE
            extrasSubSelectionAdapter.setExtrasPosition(position)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun defineExtrasSubSelection(holder: ViewHolder) {
        val linearLayoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        extrasSubSelectionAdapter = ExtrasSubSelectionAdapter(context)
        extrasSubSelectionAdapter.addAll(itemList!![0])
        extrasSubSelectionAdapter.notifyDataSetChanged()
        holder.binding.rvSubSelections.layoutManager = linearLayoutManager
        holder.binding.rvSubSelections.adapter = extrasSubSelectionAdapter

    }

    override fun getItemCount(): Int {
        return itemList!![0].extras.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addAll(coffeeMachineModel: CoffeeMachineModel) {
        itemList!!.clear()
        itemList!!.add(coffeeMachineModel)
        notifyDataSetChanged()
    }

    class ViewHolder(var binding: ItemExtrasBinding) : RecyclerView.ViewHolder(binding.root)

}