package com.example.recyceviewcomposeexample.retrofit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RetrofitViewModel : ViewModel() {

    private val _post = MutableStateFlow<List<Post>>(emptyList())
    var post : StateFlow<List<Post>> = _post
    val retrofit = RetrofitInstance.apiService

    init {
        loadPost()
    }

    fun loadPost(){
        viewModelScope.launch {
            _post.value= retrofit.getAllPost()

        }
    }

}