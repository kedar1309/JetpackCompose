package com.example.recyceviewcomposeexample.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Person::class), version = 1, exportSchema = false)
abstract class PersonDatabase : RoomDatabase() {
    abstract fun PersonDAO() : PersonDao

    companion object {
        private var INSTANCE : PersonDatabase? = null

        fun getDataBase(context : Context) : PersonDatabase{
            if(INSTANCE != null){
                return INSTANCE as PersonDatabase
            }
            synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext, PersonDatabase::class.java, "persondatabase").build()
                INSTANCE = instance
                return INSTANCE as PersonDatabase

            }
        }
    }
}