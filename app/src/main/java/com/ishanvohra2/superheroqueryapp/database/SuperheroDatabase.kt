package com.ishanvohra2.superheroqueryapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ishanvohra2.superheroqueryapp.dao.SearchDao
import com.ishanvohra2.superheroqueryapp.data.Superhero

@Database(entities = [Superhero::class], version = 2)
abstract class SuperheroDatabase: RoomDatabase() {
    abstract fun searchDao(): SearchDao
}