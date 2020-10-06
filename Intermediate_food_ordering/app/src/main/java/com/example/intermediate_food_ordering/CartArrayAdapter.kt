package com.example.intermediate_food_ordering

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.item_cart.view.*

class CartArrayAdapter(context: Context, cartItems: List<CartItem>) : ArrayAdapter<CartItem>(context,0,cartItems){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var itemView = convertView
        var viewHolder : ViewHolder

        if(itemView==null){
            itemView = LayoutInflater.from(context).inflate(R.layout.item_cart,parent,false)
            viewHolder = ViewHolder(itemView)
            itemView.tag = viewHolder
        }else{
            viewHolder = itemView.tag as ViewHolder
        }

        val currentItem = getItem(position)

        viewHolder.primaryName.text = currentItem!!.menuItem.primaryName
        viewHolder.secondaryName.text = currentItem.menuItem.secondaryName
        viewHolder.count.text = currentItem.count.toString()
        viewHolder.totalPrice.text = currentItem.totalPrice.toString()

        return itemView!!
    }

    class ViewHolder (itemView: View){
        val primaryName = itemView.cart_primary_Name_textView
        val secondaryName = itemView.cart_secondary_Name_textView
        val count = itemView.cart_count_textView
        val totalPrice = itemView.cart_total_price_textView
    }
}