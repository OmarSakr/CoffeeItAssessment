package com.codevalley.coffeeitassessment.main.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codevalley.coffeeitassessment.databinding.ItemSubSelectionsBinding
import com.codevalley.coffeeitassessment.models.coffeeMachineModel.Size
import java.util.ArrayList

class SelectedCoffeeSizeAdapter(context: Context) :
    RecyclerView.Adapter<SelectedCoffeeSizeAdapter.ViewHolder>() {

    private var itemList: ArrayList<Size>? = null
    private var layoutInflater: LayoutInflater? = null
    private var selectedPosition: Int = -1
    private var first: Boolean = true


    init {
        itemList = ArrayList()
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemSubSelectionsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.binding.tvName.text = itemList!![position].name
        if (itemList!![position].selected) {
            holder.binding.rbSelect.isChecked = true
        }

        if (!first) {
            holder.binding.rbSelect.isChecked =
                position == selectedPosition
        }
        holder.binding.rbSelect.setOnClickListener {
            first = false
            selectedPosition = position
            notifyItemRangeChanged(
                0,
                itemList!!.size
            )
        }
    }

    override fun getItemCount(): Int {
        return itemList!!.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addAll(data: List<Size>?) {
        itemList!!.clear()
        itemList!!.addAll(data!!)
        notifyDataSetChanged()
    }

    class ViewHolder(var binding: ItemSubSelectionsBinding) : RecyclerView.ViewHolder(binding.root)


}