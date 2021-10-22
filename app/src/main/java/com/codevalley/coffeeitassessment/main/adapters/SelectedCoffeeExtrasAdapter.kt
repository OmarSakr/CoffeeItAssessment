package com.codevalley.coffeeitassessment.main.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codevalley.coffeeitassessment.databinding.ItemSelectedExtrasBinding
import com.codevalley.coffeeitassessment.models.coffeeMachineModel.Extra
import java.util.ArrayList

class SelectedCoffeeExtrasAdapter(private val context: Context) :
    RecyclerView.Adapter<SelectedCoffeeExtrasAdapter.ViewHolder>() {

    private var itemList: ArrayList<Extra>? = null
    private var layoutInflater: LayoutInflater? = null
    private var selectedPosition = -1


    private lateinit var selectedExtrasSubSelectionAdapter: SelectedExtrasSubSelectionAdapter

    init {
        itemList = ArrayList()
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemSelectedExtrasBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.binding.tvName.text = itemList!![position].name

        defineSelectedExtrasSubSelection(holder)

        holder.binding.tvEdit.setOnClickListener {
            selectedPosition = position
            notifyDataSetChanged()
        }


        if (selectedPosition != position) {
            holder.binding.view.visibility = View.GONE
            holder.binding.rvCoffeeTypes.visibility = View.GONE

        } else if (selectedPosition == position) {
            holder.binding.view.visibility = View.VISIBLE
            holder.binding.rvCoffeeTypes.visibility = View.VISIBLE
            selectedExtrasSubSelectionAdapter.setExtrasPosition(position)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun defineSelectedExtrasSubSelection(holder: ViewHolder) {
        val linearLayoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        selectedExtrasSubSelectionAdapter = SelectedExtrasSubSelectionAdapter(context)
        selectedExtrasSubSelectionAdapter.addAll(itemList)
        selectedExtrasSubSelectionAdapter.notifyDataSetChanged()
        holder.binding.rvCoffeeTypes.layoutManager = linearLayoutManager
        holder.binding.rvCoffeeTypes.adapter = selectedExtrasSubSelectionAdapter

    }

    override fun getItemCount(): Int {
        return itemList!!.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addAll(data: List<Extra>?) {
        itemList!!.clear()
        itemList!!.addAll(data!!)
        notifyDataSetChanged()
    }

    class ViewHolder(var binding: ItemSelectedExtrasBinding) : RecyclerView.ViewHolder(binding.root)

}