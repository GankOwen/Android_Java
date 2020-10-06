package com.example.intermediate_food_ordering

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.item_main.view.*

class MainArrayAdapter(context: Context, menuItems: List<MenuItem>) : ArrayAdapter<MenuItem>(context, 0, menuItems) { // add private val in front of cartCounterTextView, so that we can access outside of constructor, this will create member variable for me
    private lateinit var cartCounterTextView: TextView
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var itemView = convertView
        val viewHolder: ViewHolder


        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.item_main, parent, false)!!
            viewHolder = ViewHolder(itemView)
            itemView.tag = viewHolder
        } else {
            viewHolder = itemView.tag as ViewHolder
        }

        val currentMenuItem = getItem(position)!! // we put list foods at the beginning, !! means telling the system currentFood must not be null

        viewHolder.primaryNameTextView.text = currentMenuItem.primaryName
        viewHolder.secondaryNameTextView.text = currentMenuItem.secondaryName
        viewHolder.priceTextView.text = getPriceText(currentMenuItem.price) // "$currentFood.price"
        viewHolder.addToCartButton.setOnClickListener {
            cartCounterTextView.visibility = View.VISIBLE
            if (Main.addedItems.containsKey(currentMenuItem)) {
                Main.addedItems[currentMenuItem] = Main.addedItems[currentMenuItem]!! + 1
            } else {
                Main.addedItems[currentMenuItem] = 1
            }

            cartCounterTextView.text = Main.getCartSize().toString()
        }

        return itemView
    }

    fun setCartCounterTextView(textView: TextView){
        cartCounterTextView = textView
    }

    private fun getPriceText(price: Double): String {
        return "$$price"
    }

    class ViewHolder(itemView: View) { // we don't need item view as variable, not private itemView
        val primaryNameTextView = itemView.main_primary_name_textView
        val secondaryNameTextView = itemView.main_secondary_name_textView
        val priceTextView = itemView.main_price_textView
        val addToCartButton = itemView.main_add_to_cart_button

    }

}