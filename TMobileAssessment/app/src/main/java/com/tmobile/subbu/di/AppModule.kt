package com.tmobile.subbu.di

import android.content.Context
import androidx.room.Room
import com.tmobile.subbu.model.datasource.local.LocalDataSource
import com.tmobile.subbu.model.datasource.local.db.CardsDB
import com.tmobile.subbu.model.datasource.remote.RemoteDataSource
import com.tmobile.subbu.model.datasource.remote.retrofit.CardsApi
import com.tmobile.subbu.model.datasource.remote.util.NetworkUtil
import com.tmobile.subbu.model.repository.CardsRepository
import com.tmobile.subbu.model.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Hilt App Module
 */
@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    private const val BASE_URL = "https://private-8ce77c-tmobiletest.apiary-mock.com/"
    private const val ROOM_DB = "cards-db"

    @Singleton
    @Provides
    fun provideCardsAPI(): CardsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CardsApi::class.java)
    }

    @Singleton
    @Provides
    fun provideCardsRoomDB(@ApplicationContext applicationContext: Context): CardsDB {
        return Room.databaseBuilder(
            applicationContext,
            CardsDB::class.java, ROOM_DB
        ).build()
    }

    @Singleton
    @Provides
    fun provideCardsNetworkDataSource(moviesAPI: CardsApi): RemoteDataSource {
        return RemoteDataSource(moviesAPI)
    }

    @Singleton
    @Provides
    fun provideCardsLocalDataSource(cardsDB: CardsDB): LocalDataSource {
        return LocalDataSource(cardsDB)
    }

    @Singleton
    @Provides
    fun provideNetworkUtil(@ApplicationContext applicationContext: Context): NetworkUtil {
        return NetworkUtil(applicationContext)
    }

    @Singleton
    @Provides
    fun provideCardsRepository(
        networkDataSource: RemoteDataSource,
        localDataSource: LocalDataSource,
        networkUtil: NetworkUtil
    ): Repository {
        return CardsRepository(networkUtil, localDataSource, networkDataSource)
    }
}