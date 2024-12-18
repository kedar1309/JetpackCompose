package com.example.recyceviewcomposeexample.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CalculatorViewModel : ViewModel() {

    var total = MutableLiveData<Int>(0)

    val text: LiveData<Int> = total
    var count =0

     var onStartCounter = 0
    private val _onStartMutableLiveData  : MutableLiveData<Int> = MutableLiveData(onStartCounter)
    val onStartLiveData : LiveData<Int> get() = _onStartMutableLiveData
    val text2: LiveData<Int> = _onStartMutableLiveData

    private val _count = MutableLiveData<Int>()
    val countdata: LiveData<Int> = _count


    private val _counter = MutableStateFlow(0)
    val counter: StateFlow<Int> = _counter

    fun addValue(){
        viewModelScope.launch {
            _counter.value += 1
        }
        Log.d("VIEWMODEL", "addValue called ${onStartCounter} ")
       // total.value = count +1
        //count = total.value!!
        _count.value = (_count.value ?: 0) + 1

         total.value = onStartCounter.plus(1)
        onStartCounter = onStartCounter.plus(1)

    }
    fun  minusValue(){
        Log.d("VIEWMODEL", "Minus called $count")

        if(total.value!! > 0){
            total.value = count-1
            count = total.value!!
        }
    }


    val _name = MutableLiveData<String>("0")
    var name : LiveData<String> = _name

    fun updateName(str: String){
        val count = _name.value?.toInt()
        _name.value = (count?.plus(1)).toString()
    }

}