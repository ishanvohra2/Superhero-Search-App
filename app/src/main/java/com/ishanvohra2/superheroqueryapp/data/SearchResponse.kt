package com.ishanvohra2.superheroqueryapp.data

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    val response: String,
    val results: List<Result>?
) {
    data class Result(
        val appearance: Appearance,
        val biography: Biography,
        val connections: Connections,
        val id: String,
        val image: Image,
        val name: String,
        val powerstats: Powerstats,
        val work: Work
    )

    data class Appearance(
        val gender: String,
        val height: List<String>,
        val race: String,
        val weight: List<String>
    )

    data class Biography(
        val aliases: List<String>,
        val alignment: String,
        @SerializedName("alter-egos")
        val alter_egos: String,
        @SerializedName("first-appearance")
        val first_appearance: String,
        @SerializedName("full-name")
        val full_name: String,
        @SerializedName("place-of-birth")
        val place_of_birth: String,
        val publisher: String
    )

    data class Connections(
        @SerializedName("group-affiliation")
        val group_affiliation: String,
        val relatives: String
    )

    data class Image(
        val url: String
    )

    data class Powerstats(
        val combat: String,
        val durability: String,
        val intelligence: String,
        val power: String,
        val speed: String,
        val strength: String
    )

    data class Work(
        val base: String,
        val occupation: String
    )

    fun toSuperhero(): List<Superhero> {
        val list = mutableListOf<Superhero>()
        results?.forEach {
            list.add(
                Superhero(
                    id = it.id,
                    name = it.name,
                    imageUrl = it.image.url,
                    gender = it.appearance.gender,
                    race = it.appearance.race,
                    weight = it.appearance.weight[0],
                    aliases = it.biography.aliases[0],
                    alter_egos = it.biography.alter_egos,
                    fullName = it.biography.full_name,
                    placeOfBirth = it.biography.place_of_birth,
                    publisher = it.biography.publisher,
                    combat = it.powerstats.combat,
                    durability = it.powerstats.durability,
                    intelligence = it.powerstats.intelligence,
                    power = it.powerstats.power,
                    speed = it.powerstats.speed,
                    strength = it.powerstats.strength,
                    workBase = it.work.base,
                    occupation = it.work.occupation
                )
            )
        }
        return list
    }
}