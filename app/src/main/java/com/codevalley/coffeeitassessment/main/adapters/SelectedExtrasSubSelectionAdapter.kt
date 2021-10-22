package com.codevalley.coffeeitassessment.main.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codevalley.coffeeitassessment.databinding.ItemSubSelectionsBinding
import com.codevalley.coffeeitassessment.models.coffeeMachineModel.Extra
import java.util.ArrayList

class SelectedExtrasSubSelectionAdapter(context: Context) :
    RecyclerView.Adapter<SelectedExtrasSubSelectionAdapter.ViewHolder>() {

    private var itemList: ArrayList<Extra>? = null
    private var layoutInflater: LayoutInflater? = null
    private var first: Boolean = true
    private var extrasPosition: Int = -1


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
        holder.binding.tvName.text = itemList!![extrasPosition].subselections[position].name
        if (itemList!![extrasPosition].selectedExtrasPosition == position) {
            holder.binding.rbSelect.isChecked = true
        }

        if (!first) {
            holder.binding.rbSelect.isChecked =
                position == itemList!![extrasPosition].selectedExtrasPosition
        }
        holder.binding.rbSelect.setOnClickListener {
            itemList!![extrasPosition].selectedExtrasPosition = position
            notifyItemRangeChanged(
                0,
                itemList!![extrasPosition].subselections.size
            )
        }
    }

    override fun getItemCount(): Int {

        return itemList!![extrasPosition].subselections.size

    }

    fun setExtrasPosition(extrasPosition: Int) {
        this.extrasPosition = extrasPosition
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addAll(data: List<Extra>?) {
        itemList!!.clear()
        itemList!!.addAll(data!!)
        notifyDataSetChanged()
    }


    class ViewHolder(var binding: ItemSubSelectionsBinding) : RecyclerView.ViewHolder(binding.root)

}