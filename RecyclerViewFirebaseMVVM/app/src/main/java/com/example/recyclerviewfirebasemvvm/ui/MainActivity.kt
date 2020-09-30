package com.example.recyclerviewfirebasemvvm.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewfirebasemvvm.MainAdapter
import com.example.recyclerviewfirebasemvvm.R
import com.example.recyclerviewfirebasemvvm.viewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val adapter: MainAdapter by lazy { MainAdapter(this) }
    private val viewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        observeData()
    }

    private fun observeData() {
        viewModel.fetchUserData().observe(this, {
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
    }
}