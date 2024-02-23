package com.ishanvohra2.superheroqueryapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "search_data")
data class Superhero(
    @PrimaryKey
    val id: String = "",
    val name: String = "",
    val imageUrl: String = "",
    val gender: String = "",
    val race: String = "",
    val weight: String = "",
    val aliases: String = "",
    val alter_egos: String = "",
    val fullName: String = "",
    val placeOfBirth: String = "",
    val publisher: String = "",
    val combat: String = "",
    val durability: String = "",
    val intelligence: String = "",
    val power: String = "",
    val speed: String = "",
    val strength: String = "",
    val workBase: String = "",
    val occupation: String = ""
)