package com.ishanvohra2.superheroqueryapp.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ishanvohra2.superheroqueryapp.data.Superhero

@Dao
interface SearchDao {

    @Insert
    suspend fun insertSearchData(data: Superhero)

    @Query("SELECT * FROM search_data")
    suspend fun getSearchData(): List<Superhero>?

    @Query("DELETE FROM search_data where id = :id")
    suspend fun deleteSearchData(id: String)

    @Query("SELECT COUNT(*) FROM search_data where id=:id")
    suspend fun getSuperheroById(id: String): Int
}