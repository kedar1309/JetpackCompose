package com.example.recyceviewcomposeexample.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import java.util.concurrent.Flow

@Dao
interface PersonDao {

    @Query("SELECT * FROM person")
    fun readAll() : List<Person>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insert(p: Person) : Long

}