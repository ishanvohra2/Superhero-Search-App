package com.ishanvohra2.superheroqueryapp.di

import androidx.room.Room
import com.ishanvohra2.superheroqueryapp.dao.SearchDao
import com.ishanvohra2.superheroqueryapp.database.SuperheroDatabase
import com.ishanvohra2.superheroqueryapp.network.NewsRetrofitClient
import com.ishanvohra2.superheroqueryapp.network.RetrofitClient
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single { RetrofitClient(androidContext()) }
    single { NewsRetrofitClient(androidContext()) }
    single {
        Room.databaseBuilder(
                androidApplication(),
                SuperheroDatabase::class.java,
                "Superhero.db"
            )
            .fallbackToDestructiveMigration()
            .build()
    }
    single<SearchDao> {
        val database: SuperheroDatabase = get()
        database.searchDao()
    }
}