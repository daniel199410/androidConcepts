package com.example.recyclerviewfirebasemvvm.domain.data.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.recyclerviewfirebasemvvm.User
import com.google.firebase.firestore.FirebaseFirestore

class Repository {
    fun getUsers():LiveData<MutableList<User>> {
        val mutableData = MutableLiveData<MutableList<User>>()
        FirebaseFirestore.getInstance().collection("Users").get().addOnSuccessListener {
            val listData = mutableListOf<User>()
            for(document in it) {
                val imageUrl = document.getString("imageUrl")
                val name = document.getString("name")
                val description = document.getString("description")
                val user = User(imageUrl!!, name!!, description!!)
                listData.add(user)
                mutableData.value = listData
            }
        }
        return mutableData
    }
}