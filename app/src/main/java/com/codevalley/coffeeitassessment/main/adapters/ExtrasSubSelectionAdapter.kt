package com.codevalley.coffeeitassessment.main.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codevalley.coffeeitassessment.databinding.ItemSubSelectionsBinding
import com.codevalley.coffeeitassessment.models.coffeeMachineModel.CoffeeMachineModel
import java.util.ArrayList

class ExtrasSubSelectionAdapter(context: Context) :
    RecyclerView.Adapter<ExtrasSubSelectionAdapter.ViewHolder>() {

    private var itemList: ArrayList<CoffeeMachineModel>? = null
    private var layoutInflater: LayoutInflater? = null

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
        holder.binding.tvName.text =
            itemList!![0].extras[extrasPosition].subselections[position].name

        holder.binding.rbSelect.isChecked =
            position == itemList!![0].extras[extrasPosition].selectedExtrasPosition



        holder.binding.rbSelect.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                itemList!![0].extras[extrasPosition].selected = true
                itemList!![0].extras[extrasPosition].subselections[position].selected = true
            } else if (!isChecked) {
                itemList!![0].extras[extrasPosition].selected = false
                itemList!![0].extras[extrasPosition].subselections[position].selected = false
            }

        }
        holder.binding.rbSelect.setOnClickListener {
            itemList!![0].extras[extrasPosition].selectedExtrasPosition = position
            notifyItemRangeChanged(
                0,
                itemList!![0].extras[extrasPosition].subselections.size
            )
        }

    }




    override fun getItemCount(): Int {
        return itemList!![0].extras[extrasPosition].subselections.size
    }

    fun setExtrasPosition(extrasPosition: Int) {
        this.extrasPosition = extrasPosition
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addAll(coffeeMachineModel: CoffeeMachineModel) {
        itemList!!.clear()
        itemList!!.add(coffeeMachineModel)
        notifyDataSetChanged()
    }

    class ViewHolder(var binding: ItemSubSelectionsBinding) : RecyclerView.ViewHolder(binding.root)


}