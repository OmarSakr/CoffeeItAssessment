package com.codevalley.coffeeitassessment.main.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codevalley.coffeeitassessment.databinding.ItemHomeBinding
import com.codevalley.coffeeitassessment.main.activities.coffeeSize.view.CoffeeSizeActivity
import com.codevalley.coffeeitassessment.models.coffeeMachineModel.CoffeeMachineModel
import java.util.ArrayList

class HomeAdapter(private val context: Context) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    private var itemList: ArrayList<CoffeeMachineModel>? = null
    private var layoutInflater: LayoutInflater? = null

    init {
        itemList = ArrayList()
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemHomeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvName.text = itemList!![0].types[position].name

        holder.itemView.setOnClickListener {
            itemList!![0].types[position].selected = true
            itemList!![0].selectedCoffeeType = itemList!![0].types[position].name
            val intent = Intent(context, CoffeeSizeActivity::class.java)
            intent.putExtra("userData", itemList!![0])
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return itemList!![0].types.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addAll(coffeeMachineModel: CoffeeMachineModel) {
        itemList!!.clear()
        itemList!!.add(coffeeMachineModel)
        notifyDataSetChanged()
    }

    class ViewHolder(var binding: ItemHomeBinding) : RecyclerView.ViewHolder(binding.root)

}