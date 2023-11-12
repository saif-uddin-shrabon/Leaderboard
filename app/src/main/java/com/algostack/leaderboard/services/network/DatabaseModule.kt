package com.algostack.leaderboard.services.network

import android.content.Context
import androidx.room.Room
import com.algostack.leaderboard.services.db.LeaderboardDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,LeaderboardDatabase::class.java, "LeaderBoard_DB"
    ).build()



}