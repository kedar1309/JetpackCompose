package com.example.recyceviewcomposeexample.room

import android.util.Log
import androidx.lifecycle.LiveData

class PersonRepository (private val personDao: PersonDao){

    val readallData: List<Person>   = personDao.readAll()

    suspend fun addPerson(p: Person){
        personDao.insert(p)
    }

    suspend fun addDummyData(){
        var count  =0
        repeat(2){
           // addPerson(Person(count++, "Name $count" , count))
            count = count++
            //addPerson(Person(count++, "Name $count" , count))
            personDao.insert(Person(count++, "Name $count" , count))
            Log.d("RoomTest", "Name $count")
        }
    }


}