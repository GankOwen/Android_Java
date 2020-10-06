package com.example.intermediate_food_ordering

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.cart_counter.*
import kotlinx.android.synthetic.main.item_cart.*

class MainActivity : AppCompatActivity() {
    lateinit var cartCounterTextView: TextView //lateinit means we will initialize it later because we will go to onCreate, then inflate Options Menu.
    lateinit var adapter: MainArrayAdapter
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        cartCounterTextView = menu!!.findItem(R.id.main_shopping_cart_item).actionView.findViewById(R.id.cart_shopping_counter_textView) //we use actionView to get the custom layout
        adapter.setCartCounterTextView(cartCounterTextView)
        menu!!.getItem(0).actionView.setOnClickListener {
            intent = Intent(this,CartActivity::class.java)
            startActivity(intent)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

        val menuItems: MutableList<com.example.intermediate_food_ordering.MenuItem> = ArrayList()

        menuItems.add(MenuItem("Apple","苹果", 3.69))
        menuItems.add(MenuItem("Fish","鱼", 6.99))
        menuItems.add(MenuItem("Beef","牛肉", 7.58))
        menuItems.add(MenuItem("Lamb","羊肉", 8.26))
        menuItems.add(MenuItem("Banana","香蕉", 4.78))
        menuItems.add(MenuItem("Water","水", 0.99))
        menuItems.add(MenuItem("Pear","梨", 2.45))
        menuItems.add(MenuItem("Milk","牛奶", 2.17))
        menuItems.add(MenuItem("Radish","萝卜", 3.78))
        menuItems.add(MenuItem("Cabbage","白菜", 2.57))

        adapter = MainArrayAdapter(this,menuItems)
        main_listView.adapter = adapter

    }

    override fun onStart() {
        super.onStart()
        if(this::cartCounterTextView.isInitialized) {  //check if initialize because we use lateinit
            cartCounterTextView.text = Main.getCartSize().toString()
            if(Main.addedItems.isEmpty()){
                cartCounterTextView.visibility = View.INVISIBLE
            }else{
                cartCounterTextView.visibility = View.VISIBLE
            }
        }
    }
}