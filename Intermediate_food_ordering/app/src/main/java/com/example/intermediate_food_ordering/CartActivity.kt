package com.example.intermediate_food_ordering

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.core.app.NavUtils
import kotlinx.android.synthetic.main.activity_cart.*

class CartActivity : AppCompatActivity() {
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.cart,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //switch in Kotlin
        when(item.itemId){
            R.id.cart_delete_item -> {
                Main.addedItems.clear()
                finish()
            }
            android.R.id.home -> {
                NavUtils.navigateUpFromSameTask(this)
            }
            else -> {

            }
        }
        if(item.itemId == R.id.cart_delete_item){
            Main.addedItems.clear()
            finish()
        }
        return true
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        val cart: MutableList<CartItem> = ArrayList()

        Main.addedItems.forEach { (food, i) ->
            val currentCartItem =
                CartItem(food, Main.addedItems[food]!!, Main.addedItems[food]!! * food.price)
            cart.add(currentCartItem)
        }





        /*for(addedItem in Main.addedItems){

         }*/


        val adapter = CartArrayAdapter(this, cart)
        cart_listView.adapter = adapter
    }

   /* @RequiresApi(Build.VERSION_CODES.N)
    private fun getOrganizedAddedItems(): MutableList<CartItem> {
        val cartItems: MutableList<CartItem> = ArrayList()

        Main.addedItems2.forEachIndexed { i, currentMenuItem ->
            var j= i+1
            var quantity = 1
            Main.addedItems2.forEachIndexed { j , currentMenuItem2 ->
                if(currentMenuItem.primaryName == currentMenuItem2.primaryName) {
                    quantity++
                    Main.addedItems2.removeAt(j)
                }
            }
            cartItems.add(CartItem(currentMenuItem,quantity,currentMenuItem.price*quantity))
            quantity = 1
        }
        return cartItems
    }*/
}