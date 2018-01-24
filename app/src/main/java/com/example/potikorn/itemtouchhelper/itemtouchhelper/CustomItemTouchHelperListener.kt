package com.example.potikorn.itemtouchhelper.itemtouchhelper

interface CustomItemTouchHelperListener {

    fun onItemMove(fromPosition: Int, toPosition: Int) : Boolean
    fun onItemDismiss(position: Int)

}
