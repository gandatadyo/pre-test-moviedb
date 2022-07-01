package com.app.movieapps.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.movieapps.data.source.entity.ItemMovieEntity

class ViewModelMovie:ViewModel() {
    val listMovie: MutableLiveData<ArrayList<ItemMovieEntity>> by lazy {
        MutableLiveData<ArrayList<ItemMovieEntity>>().also {
//            loadUsers()
        }
    }
}