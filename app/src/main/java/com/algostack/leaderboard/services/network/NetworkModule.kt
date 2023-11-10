package com.algostack.leaderboard.services.network

import com.algostack.leaderboard.services.api.Leaderboardapi
import com.algostack.leaderboard.utlis.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofitBuilder(): Retrofit.Builder{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)

    }

    @Singleton
    @Provides
    fun provideLeaderboardApi(retrofitBuilder: Retrofit.Builder) : Leaderboardapi {
        return retrofitBuilder.build().create(Leaderboardapi::class.java)
    }
}