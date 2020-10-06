package com.example.intermediate_food_ordering

class Main {
    companion object {
        val addedItems: LinkedHashMap<MenuItem, Int> = LinkedHashMap<MenuItem, Int>()

        fun getCartSize(): Int {
            var mCounter = 0
            addedItems.forEach { (menuItem, counter) ->
                mCounter += counter
            }
            return mCounter
        }
    }
}