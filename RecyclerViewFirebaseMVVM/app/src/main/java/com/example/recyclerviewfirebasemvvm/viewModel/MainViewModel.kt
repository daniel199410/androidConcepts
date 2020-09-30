package com.example.recyclerviewfirebasemvvm.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recyclerviewfirebasemvvm.User
import com.example.recyclerviewfirebasemvvm.domain.data.network.Repository

class MainViewModel: ViewModel() {
    private val repository = Repository()

    fun fetchUserData(): LiveData<MutableList<User>> {
        val mutableData = MutableLiveData<MutableList<User>>()
        repository.getUsers().observeForever{
            mutableData.value = it
        }
        return mutableData
    }
}