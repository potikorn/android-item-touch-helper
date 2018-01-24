package com.example.potikorn.itemtouchhelper

import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.helper.ItemTouchHelper
import com.example.potikorn.itemtouchhelper.PreferenceHelper.get
import com.example.potikorn.itemtouchhelper.PreferenceHelper.set
import com.example.potikorn.itemtouchhelper.itemtouchhelper.CustomItemTouchHelperCallback
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val simpleAdapter: SimpleAdapter by lazy { SimpleAdapter() }
    private var simpleList: MutableList<String> = mutableListOf()
    var prefs: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        prefs = PreferenceHelper.customPrefs(this, "sorted_list")
        rvSimple.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = simpleAdapter
        }
        val callback = CustomItemTouchHelperCallback(simpleAdapter)
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvSimple)
        btnReset.setOnClickListener { setMockItems() }
        btnClear.setOnClickListener { simpleList.clear(); simpleAdapter.items = mutableListOf() }
        btnSave.setOnClickListener { saveToLocal() }
        btnRestore.setOnClickListener { restoreFromLocal() }
        // TODO check if local data exited
        setMockItems()
    }

    private fun setMockItems() {
        simpleList.clear()
        for (i in 1..20) {
            simpleList.add("item $i")
        }
        simpleAdapter.items = simpleList
    }

    private fun saveToLocal() {
        prefs?.set("simple_list", Gson().toJson(simpleList))
    }

    private fun restoreFromLocal() {
        val json = prefs?.get("simple_list", "")
        val type = object : TypeToken<ArrayList<String>>() {}.type
        simpleList = Gson().fromJson<ArrayList<String>>(json, type)
        simpleAdapter.items = simpleList
    }
}
