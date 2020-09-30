package com.example.recyclerviewfirebasemvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val adapter: MainAdapter by lazy { MainAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        val dummyList = mutableListOf<User>()
        dummyList.add(User(
                "https://static1.abc.es/media/tecnologia/2020/02/20/android-11-k5cF--620x349@abc.jpg",
                "Android",
                "Description")
        )
        dummyList.add(User(
                "https://www.welivesecurity.com/wp-content/uploads/es-la/2012/12/Logo-Android.png",
                "Android",
                "Description")
        )
        dummyList.add(User(
                "https://lh3.googleusercontent.com/qJeC_pp1apa-pn2RA8mJmMtU2bsqlBbAot-Dfx-tdixGCE1CYTykr8ipEFtKkiFWiE-iVG05svq-aisezpENX7f3C0jgFt2XNOD43g=w350-v3-e365",
                "Android",
                "Description")
        )
        adapter.setListData(dummyList)
        adapter.notifyDataSetChanged()
    }
}