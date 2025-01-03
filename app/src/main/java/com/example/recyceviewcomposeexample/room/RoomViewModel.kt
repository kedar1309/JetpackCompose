package com.example.recyceviewcomposeexample.room

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


class RoomViewModel (private val personDao: PersonDao) : ViewModel() {
    private val _person = MutableStateFlow<List<Person>>(emptyList())
    val person: StateFlow<List<Person>> = _person

    init {
        loadPerson()
    }

    private fun loadPerson() {
        viewModelScope.launch(Dispatchers.IO) {
            _person.value = personDao.readAll()
        }
    }

    fun addDummy(context: Context){
        viewModelScope.launch (Dispatchers.IO) {
             val userdo = PersonDatabase.getDataBase(context).PersonDAO()
            val  personRepository = PersonRepository(userdo)

             personRepository.addDummyData()
            Log.d("RoomTest" , "RoomTest addDummy->")

            val list = personRepository.readallData
            Log.d("RoomTest" , "list-> ${list?.size}")
            list?.forEach { p ->
                Log.d("RoomTest" , "Person -> ${p.name}")
            }
        }


    }

    fun readAllTheEntries(context: Context) : List<Person>{
        val userdo = PersonDatabase.getDataBase(context).PersonDAO()
        val  personRepository = PersonRepository(userdo)
       return personRepository.readallData

    }

    fun addPerson(p : Person){
        viewModelScope.launch (Dispatchers.IO) {
            personDao.insert(p)
            loadPerson()
        }
    }


}