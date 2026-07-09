package com.tsvigun.workmatestarwars.di

import androidx.room.Room
import com.tsvigun.workmatestarwars.data.local.StarWarsDatabase
import com.tsvigun.workmatestarwars.data.network.SwapiApi
import com.tsvigun.workmatestarwars.data.repository.CharacterRepositoryImpl
import com.tsvigun.workmatestarwars.domain.CharacterRepository
import com.tsvigun.workmatestarwars.presentation.main.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://swapi.py4e.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SwapiApi::class.java)
    }

    single {
        Room.databaseBuilder(
            androidContext(),
            StarWarsDatabase::class.java,
            "star_wars_db"
        ).build()
    }

    single { get<StarWarsDatabase>().characterDao() }

    single<CharacterRepository> {
        CharacterRepositoryImpl(api = get(), dao = get())
    }

    viewModel { MainViewModel(repository = get()) }

    viewModel {
        MainViewModel(repository = get())
    }

    viewModel {
        com.tsvigun.workmatestarwars.presentation.detail.DetailViewModel(repository = get())
    }
}

